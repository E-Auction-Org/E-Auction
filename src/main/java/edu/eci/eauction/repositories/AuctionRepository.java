package edu.eci.eauction.repositories;

import edu.eci.eauction.service.model.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {
    @Query("{creator:?0}")
    List<Auction> findAuctionsByCreator(String creator);

    @Override
    long count();
}
