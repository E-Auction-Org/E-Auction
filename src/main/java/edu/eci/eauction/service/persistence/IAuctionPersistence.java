/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.persistence;

import edu.eci.eauction.service.model.Auction;

import java.util.List;

/**
 *
 * @author JuanRojasCastr & javier32rojas040506
 */
public interface IAuctionPersistence {
    /**
     * Method that search all the auctions
     * @return Set of auctions
     * @throws AuctionNotFoundException if there is no auctions
     */
    public List<Auction> getAuctions() throws AuctionNotFoundException;

    /**
     * Method that search an auction for one user and id
     * @param user auction's author
     * @param id auction's name
     * @return the auction of the given user by the given id
     * @throws AuctionNotFoundException if there is no such auction
     */
    public Auction getAuction(String user, String id) throws AuctionNotFoundException;

    /**
     * Method that search all the auctions for one user
     * @param user the user's name of the owner of auctions
     * @return Set of Auctions
     * @throws AuctionNotFoundException if there is no auctions
     */
    public List<Auction> getUserAuction(String user) throws AuctionNotFoundException;

    /**
     * Method that save a given auction
     * @param auction the auction to be saved
     * @throws AuctionPersistenceException if the auction already exists
     */
    public String postAuction(Auction auction) throws AuctionPersistenceException;

    /**
     * Method that modify a given auction
     * @param user auction's user
     * @param id auction's id
     * @param auction auction
     * @throws AuctionPersistenceException if the auction doesn't exist
     */
    public Auction putAuction(String user, String id , Auction auction) throws AuctionPersistenceException;

    /**
     * Method that delete a given auction
     * @param user auction's user
     * @param id auction's id
     * @throws AuctionPersistenceException if the auction doesn't exist
     */
    public void deleteAuction(String user, String id) throws AuctionPersistenceException;
}
