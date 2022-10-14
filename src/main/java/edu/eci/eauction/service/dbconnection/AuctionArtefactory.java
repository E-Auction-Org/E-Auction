package edu.eci.eauction.service.dbconnection;


import java.io.UnsupportedEncodingException;

public class AuctionArtefactory {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setProperty("java.net.preferIPv4Stack" , "true");

        MongoDBConnection mongoConnection = new MongoDBConnection();

        mongoConnection.createConnection();
        mongoConnection.addUser("juan2");
        mongoConnection.closeConnection();

    }
}
