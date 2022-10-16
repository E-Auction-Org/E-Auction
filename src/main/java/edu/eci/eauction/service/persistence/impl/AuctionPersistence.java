/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.persistence.impl;

import edu.eci.eauction.repositories.EAuctionArtefactory;
import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.persistence.AuctionNotFoundException;
import edu.eci.eauction.service.persistence.IAuctionPersistence;
import edu.eci.eauction.service.persistence.AuctionPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author JuanRojasCastr & javier32rojas040506
 */
@Service
public class AuctionPersistence implements IAuctionPersistence {

    @Autowired
    EAuctionArtefactory atf = null;

    public AuctionPersistence() {
    }

    @Override
    public List<Auction> getAuctions() throws AuctionNotFoundException {
        List<Auction> auctions = atf.getAuctions();
        if (auctions.isEmpty()) throw  new AuctionNotFoundException("Auctions not founded");
        return auctions;
    }

    @Override
    public Auction getAuction(String user, String id) throws AuctionNotFoundException {
        Optional<Auction> auction = atf.getAuction(id);
        if (!auction.isPresent()) throw new AuctionNotFoundException("Auction not founded");
        return auction.get();
    }

    @Override
    public List<Auction> getUserAuction(String user) throws AuctionNotFoundException {
        List<Auction> auctions = atf.getUserAuctions(user);
        if (auctions.isEmpty()) throw  new AuctionNotFoundException("This user has no auctions");
        return auctions;

    }

    @Override
    public String postAuction(Auction auction) throws AuctionPersistenceException {
        Optional<Auction> ac = atf.getAuction(auction.getId());
        if (ac.isPresent()) throw new AuctionPersistenceException("Auction already exists");
        return atf.postAuction(auction);
    }

    @Override
    public Auction putAuction(String user, String id , Auction ac) throws AuctionPersistenceException {
        Auction auction = atf.getAuction(id).get();
        ac.setId(id);
        if (ac.equals(auction)) throw new AuctionPersistenceException("Auction have no changes");
        return atf.putAuction(id, ac);
    }

    @Override
    public void deleteAuction(String user, String id) throws AuctionPersistenceException {
        Optional<Auction> auction = atf.getAuction(id);
        if (!auction.isPresent()) throw new AuctionPersistenceException("Auction doesn't exists");
        atf.deleteAuction(id);
    }


}
