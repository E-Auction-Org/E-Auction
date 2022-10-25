package edu.eci.eauction.repositories;


import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.services.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EAuctionArtefactory {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public List<Auction> getAuctions() {
        return auctionRepository.findAll();
    }

    public Optional<Auction> getAuction(String id) {
        return auctionRepository.findById(id);
    }

    public List<Auction> getUserAuctions(String creator) {
        return auctionRepository.findAuctionsByCreator(creator);

    }

    public Boolean auctionExists(Auction ac){
        return auctionRepository.exists(Example.of(ac));
    }

    public String postAuction(Auction auction) {
        String id = String.valueOf(nextSequenceService.getNextSequence(Auction.SEQUENCE_NAME));
        auction.setId(id);
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
