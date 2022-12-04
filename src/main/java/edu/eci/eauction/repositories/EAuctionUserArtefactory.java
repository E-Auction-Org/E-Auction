package edu.eci.eauction.repositories;


import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.services.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EAuctionUserArtefactory {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public Boolean userExists(User user){
        return userRepository.exists(Example.of(user));
    }

    public User registerUser(User user) {
        String id = String.valueOf(nextSequenceService.getNextSequence(User.SEQUENCE_NAME));
        user.setId(id);
        userRepository.save(user);
        return user;
    }



}
