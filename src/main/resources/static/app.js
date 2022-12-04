var app = (function () {

    let connection = null;
    let auctions = null;
    var stompClient = null;


    let cleanCanvas = function () {
          var canvas = document.getElementById("canvas");
          let context = canvas.getContext('2d');
          context.clearRect(0, 0, canvas.width, canvas.height);
   };

    var connectAndSubscribe = function (draw) {
          connection = draw;
          console.info('Connecting to WS...');
          var socket = new SockJS('/stompendpoint');
          stompClient = Stomp.over(socket);
          
          //subscribe to /topic/TOPICXX when connections succeed
          stompClient.connect({}, function (frame) {
                    console.log('Connected: ' + frame);
                    stompClient.subscribe(`/topic/auction.${connection}`, (eventbody) => {
                              let point = JSON.parse(eventbody.body);
                              console.log(point);
                    });
          });
    };

return {

          init: function () {
                    var can = document.getElementById("canvas");
                    console.log("connect");

                    fetch("http://localhost:8080/v1/auctions")
                    .then(resp => resp.json())
                    .then(data => {
                              auctions = data.data;
                              let aucitonsList = document.getElementById("auctionsList");
                              auctions.forEach(element => {
                                        let newLabel = document.createElement('label');
                                        let newButton = document.createElement('button');
                                        newLabel.innerText = "Auction: " + element.id;
                                        newButton.innerText = "Connect";
                                        newButton.setAttribute('id', element.id);
                                        newButton.addEventListener('click', function(event) {
                                                  console.log(event.path[0].id);
                                                  connectAndSubscribe(event.path[0].id);
                                        } );
                                        newLabel.appendChild(newButton);
                                        aucitonsList.appendChild(newLabel);

                              }) 
                    });
                    //websocket connection
                    // mouseEventListner();
          },

          subscribe: function () {
                    let draw = document.getElementById("draw").value;
                    cleanCanvas();
                    connectAndSubscribe(draw);
          },

          publishPoint: function (x){
                    let data;
                    auctions.forEach(element => {
                              if (element.id == connection) {
                                        console.log('founded');
                                        element.price = x;
                                        data = element;
                              }
                    });
                    stompClient.send(`/app/auction.${data.id}`, {}, JSON.stringify(data)); 
          },

          disconnect: function () {
                    if (stompClient !== null) {
                    stompClient.disconnect();
                    }
                    setConnected(false);
                    console.log("Disconnected");
          }         
    };

})();