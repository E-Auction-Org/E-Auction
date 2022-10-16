package edu.eci.eauction.repositories;


import edu.eci.eauction.service.model.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EAuctionArtefactory {

    @Autowired
    private AuctionRepository auctionRepository;

    public List<Auction> getAuctions() {
        return auctionRepository.findAll();
    }

    public Optional<Auction> getAuction(String id) {
        return auctionRepository.findById(id);
    }

    public List<Auction> getUserAuctions(String creator) {
        return auctionRepository.findAuctionsByCreator(creator);

    }

    public String postAuction(Auction auction) {
        auctionRepository.save(auction);
        return auction.getId();
    }

    public Auction putAuction(String id, Auction auction) {
        Auction ac = auction;
        ac.setId(id);
        auctionRepository.save(auction);
        return auction;
    }

    public void deleteAuction(String id) {
        auctionRepository.deleteById(id);
    }

}
