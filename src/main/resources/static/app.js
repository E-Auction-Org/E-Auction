var app = (function () {

    class Point{
        constructor(x,y){
            this.x=x;
            this.y=y;
        }        
    }

    let connection = null;
    let auctions = null;
    var stompClient = null;

    var addPointToCanvas = function (point) {        
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.strokeStyle = "black";
        ctx.beginPath();
        ctx.arc(point.x, point.y, 3, 0, 2 * Math.PI);
        ctx.stroke();
    };

    const addLineToCanvas = (x0, y0, x1, y1) => {        
          var canvas = document.getElementById("canvas");
          var ctx = canvas.getContext("2d");
          ctx.beginPath();
          ctx.strokeStyle = "blue";
          ctx.moveTo(x0, y0);
          ctx.lineTo(x1, y1);
          ctx.stroke();
  }

  const drawPolygon = (points, flag) => {
          for (let i = 0; i < points.length; i++) {
                    if (flag) addPointToCanvas(new Point(points[i].x, points[i].y));
                    if(i < points.length - 1) {
                              addLineToCanvas(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
                    }
                    else addLineToCanvas(points[i].x, points[i].y, points[0].x, points[0].y);
          }
  };
    
    
    var getMousePosition = function (evt) {
        canvas = document.getElementById("canvas");
        var rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    };

    let mouseEventListner = function () {
          let elements = [];
          let canvas = document.getElementById("canvas");
          elemLeft = canvas.offsetLeft + canvas.clientLeft,
          elemTop = canvas.offsetTop + canvas.clientTop,
          canvas.addEventListener('click', function(event) {
                    var x = event.pageX - elemLeft,
                        y = event.pageY - elemTop;
                
                    // Collision detection between clicked offset and element.
                    elements.forEach(function(element) {
                              if (y > element.top && y < element.top + element.height 
                                        && x > element.left && x < element.left + element.width) {
                                        alert('clicked an element');
                              }
                    });
                    app.publishPoint(x);
          });
    };

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