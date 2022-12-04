package edu.eci.eauction.repositories;


import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.services.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EAuctionUserArtefactory {

    private static final DecimalFormat df = new DecimalFormat("###.##");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();

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

    public Float putRating(String id, Float rate) {
        symbols.setDecimalSeparator('.');
        Optional<User> user = getUser(id);
        if (user.isPresent()) {
            User myUser = user.get();
            ArrayList<Float> rating = myUser.getRating();
            rating.add(rate);
            Float sum = 0.0F;
            for (Float num: rating) {
                sum += num;
            }
            myUser.setRating(rating);
            userRepository.save(myUser);
            df.setDecimalFormatSymbols(symbols);
            String val = df.format(sum/rating.size());
            return Float.parseFloat(val);
        }
        return -1F;
    }

    public User putUser(User user) {
        userRepository.save(user);
        return user;
    }
}
