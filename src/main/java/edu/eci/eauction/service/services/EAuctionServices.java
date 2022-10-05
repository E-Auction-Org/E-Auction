/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.services;


import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.persistence.AuctionNotFoundException;
import edu.eci.eauction.service.persistence.AuctionPersistenceException;
import edu.eci.eauction.service.persistence.EAuctionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class EAuctionServices {
   
    @Autowired
    EAuctionPersistence bpp=null;

    /**
     * Method that search all the auctions
     * @return Set of auctions
     * @throws AuctionNotFoundException if there is no auctions
     */
    public void getAuctions() throws AuctionNotFoundException {
        bpp.getAuctions();
    }
    
    /**
     * Method that search an auction for one user and id
     * @param user auction's author
     * @param id auction's name
     * @return the auction of the given user by the given id
     * @throws AuctionNotFoundException if there is no such auction
     */
    public void getAuction(String user,String id) throws AuctionNotFoundException {
        bpp.getAuction(user, id);
    }

    /**
     * Method that search all the auctions for one user
     * @param user the user's name of the owner of auctions
     * @return Set of Auctions
     * @throws AuctionNotFoundException if there is no auctions
     */
    public void getUserAuctions(String user) throws AuctionNotFoundException {
        bpp.getUserAuction(user);
    }

    /**
     * Method that save a given auction
     * @param auction the auction to be saved
     * @throws AuctionPersistenceException if the auction already exists
     */
    public void postAuction(Auction auction) throws AuctionPersistenceException {
        bpp.postAuction(auction);
    }

    /**
     * Method that modify a given auction
     * @param user auction's user
     * @param id auction's id
     * @param auction auction
     * @throws AuctionPersistenceException if the auction doesn't exist
     */
    public void putAuction(String user, String id , Auction auction) throws AuctionPersistenceException {
        bpp.putAuction(user, id, auction);
    }

    /**
     * Method that delete a given auction
     * @param user auction's user
     * @param id auction's id
     * @throws AuctionPersistenceException if the auction doesn't exist
     */
    public void deleteAuction(String user, String id) throws AuctionPersistenceException {
        bpp.deleteAuction(user, id);
    }
}
