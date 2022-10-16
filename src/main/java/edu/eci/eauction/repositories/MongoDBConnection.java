package edu.eci.eauction.repositories;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoClient;
import org.springframework.stereotype.Service;

@Service
public class MongoDBConnection {

//    String username = URLEncoder.encode("admin", "UTF-8");
//    String password = URLEncoder.encode("mongodb", "UTF-8");
//    private String uri = "mongodb+srv://" + username + ":" + password + "@eauctiondb.ngqqzdn.mongodb.net/?retryWrites=true&w=majority";
//    private MongoClient mongoClient = null;
//    private MongoDatabase mongoDatabase = null;
//    private MongoCollection<Document> mongoCollection;
//
//    public MongoDBConnection() throws UnsupportedEncodingException {
//        this.mongoClient = MongoClients.create(uri);
//    }
//
//
//    public void createConnectionUsers() {
//        try {
//            this.mongoDatabase = this.mongoClient.getDatabase("eauction");
//            this.mongoCollection = this.mongoDatabase.getCollection("users");
//        } catch (MongoException ex){
//            System.out.println(ex.toString());
//        }
//    }
//
//    public void createConnectionAuctions() {
//        try {
//            this.mongoDatabase = this.mongoClient.getDatabase("eauction");
//            this.mongoCollection = this.mongoDatabase.getCollection("auctions");
//        } catch (MongoException ex){
//            System.out.println(ex.toString());
//        }
//    }
//
//    // Auctions
//
//    public Set<Auction> getAuctions() throws AuctionNotFoundException {
//        Set<Auction> auctions = new HashSet<>();
//
//        FindIterable<Document> result = this.mongoCollection.find();
//
//        result.forEach((Consumer<? super Document>) document -> auctions.add(new Gson().fromJson(document.toJson(), Auction.class)));
//
//        return auctions;
//    }
//
//
//    // Users
//
//    public void closeConnection() {
//        this.mongoClient.close();
//    }
//
//    public Set<String> addUser(String item){
//        Set<String> documents = new HashSet<>();
//
//        Document myDocument = new Document();
//        myDocument.put("user", item);
//        LocalDate dateObj = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String date = dateObj.format(formatter);
//
//        String password = Hashing.sha256()
//                .hashString(date, StandardCharsets.UTF_8)
//                .toString();
//
//        myDocument.put("password", password);
//
//        this.mongoCollection.insertOne(myDocument);
//        documents.add(myDocument.toJson());
//
//        return documents;
//    }
//
//    public ArrayList<String> getAllItems() {
//        ArrayList<String> messages = new ArrayList<>();
//
//        FindIterable<Document> result = this.mongoCollection.find();
//
//        result.forEach((Consumer<? super Document>) document -> messages.add(document.toJson()));
//        return messages;
//    }




}
