package edu.eci.eauction.repositories;

import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneByUserName(String userName);

}
