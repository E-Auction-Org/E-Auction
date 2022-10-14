package edu.eci.eauction.service.dbconnection;
import com.google.common.hash.Hashing;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;


public class MongoDBConnection {
    String username = URLEncoder.encode("admin", "UTF-8");
    String password = URLEncoder.encode("mongodb", "UTF-8");
    private String uri = "mongodb+srv://" + username + ":" + password + "@eauctiondb.ngqqzdn.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;
    private MongoCollection<Document> mongoCollection;

    public MongoDBConnection() throws UnsupportedEncodingException {
    }


    public void createConnection() {
        try {
            this.mongoClient = MongoClients.create(uri);
            this.mongoDatabase = this.mongoClient.getDatabase("eauction");
            this.mongoCollection = this.mongoDatabase.getCollection("users");
        } catch (MongoException ex){
            System.out.println(ex.toString());
        }
    }

    public void closeConnection() {
        this.mongoClient.close();
    }

    public ArrayList<String> addUser(String item){
        ArrayList<String> documents = new ArrayList<>();

        Document myDocument = new Document();
        myDocument.put("user", item);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        String password = Hashing.sha256()
                .hashString(date, StandardCharsets.UTF_8)
                .toString();

        myDocument.put("password", password);

        this.mongoCollection.insertOne(myDocument);
        documents.add(myDocument.toJson());

        return documents;
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> messages = new ArrayList<>();

        FindIterable<Document> result = this.mongoCollection.find();

        result.forEach((Consumer<? super Document>) document -> messages.add(document.toJson()));
        return messages;
    }




}
