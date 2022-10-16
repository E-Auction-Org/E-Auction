/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.services;


import com.google.gson.Gson;
import edu.eci.eauction.controllers.ResourceNotFoundException;
import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.persistence.AuctionNotFoundException;
import edu.eci.eauction.service.persistence.AuctionPersistenceException;
import edu.eci.eauction.service.persistence.IAuctionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author JuanRojasCastr & javier32rojas040506
 */
@Service
public class AuctionServices {
   
    @Autowired
    IAuctionPersistence bpp=null;

    /**
     * Method that search all the auctions
     * @return Set of auctions
     */
    public GenericResponse<Auction> getAuctions() {
        try {
            List<Auction> auctions = bpp.getAuctions();
            return new GenericResponse<>(HttpStatus.OK, "successful", true, auctions);
        }
        catch (AuctionNotFoundException e) {
            return new GenericResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), false, null);
        }
    }
    
    /**
     * Method that search an auction for one user and id
     * @param user auction's author
     * @param id auction's name
     * @return the auction of the given user by the given id
     */
    public GenericResponse<Auction> getAuction(String user,String id) {
        List<Auction> auction = new ArrayList<>();
        try {
            auction.add(bpp.getAuction(user, id));
            return new GenericResponse<>(HttpStatus.OK, "successful", true, auction);
        }
        catch (AuctionNotFoundException e) {
            return new GenericResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), false, null);
        }
    }

    /**
     * Method that search all the auctions for one user
     * @param user the user's name of the owner of auctions
     * @return Set of Auctions
     */
    public GenericResponse<Auction> getUserAuctions(String user) {
        try {
            List<Auction> auctions = bpp.getUserAuction(user);
            return new GenericResponse<>(HttpStatus.OK, "successful", true, auctions);
        }
        catch (AuctionNotFoundException e) {
            return new GenericResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), false, null);
        }
    }

    /**
     * Method that save a given auction
     * @param auction the auction to be saved
     */
    public GenericResponse<Auction> postAuction(Auction auction) {
        List<String> ids = new ArrayList<>();
        try {
            String id = bpp.postAuction(auction);
            ids.add(id);
            return new GenericResponse<>(HttpStatus.OK, "successful", true, ids);
        }
        catch (AuctionPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }

    /**
     * Method that modify a given auction
     * @param user auction's user
     * @param id auction's id
     * @param auction auction
     */
    public GenericResponse<Auction> putAuction(String user, String id , Auction auction) {
        List<Auction> acs = new ArrayList<>();
        try {
            Auction ac = bpp.putAuction(user, id, auction);
            acs.add(ac);
            return new GenericResponse<>(HttpStatus.OK, "successful", true, acs);
        }
        catch (AuctionPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }

    /**
     * Method that delete a given auction
     * @param user auction's user
     * @param id auction's id
     */
    public GenericResponse<Auction> deleteAuction(String user, String id) {
        try {
            bpp.deleteAuction(user, id);
            return new GenericResponse<>(HttpStatus.OK, "successful", true, null);
        }
        catch (AuctionPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }
}
