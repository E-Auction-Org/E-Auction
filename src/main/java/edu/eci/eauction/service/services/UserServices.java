package edu.eci.eauction.service.services;

import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.persistence.IUserPersistence;
import edu.eci.eauction.service.persistence.UserPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    IUserPersistence usp;

    public GenericResponse<User> register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        List<String> responseList = new ArrayList<String>();
        try {
            User userRegistered = usp.register(user);
            responseList.add(userRegistered.toString());
            return new GenericResponse<>(HttpStatus.CREATED, "successful", true, responseList);
        }catch (UserPersistenceException e){
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }

    /**
     * Method that update user rating
     * @param id of the user
     * @param rate new rate
     * @return updated rating
     * @throws UserPersistenceException if there is no auctions
     */
    public GenericResponse<Float> rateUser(String id, Float rate){
         try {
            List<Float> newRating = new ArrayList<>();
            newRating.add(usp.putRating(id, rate));
            return new GenericResponse<>(HttpStatus.OK, "successful", true, newRating);
         } catch (UserPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
         }
    }
}
