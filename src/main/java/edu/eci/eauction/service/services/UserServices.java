package edu.eci.eauction.service.services;

import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.persistence.IUserPersistence;
import edu.eci.eauction.service.persistence.UserNotFoundException;
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

    /**
     * Method that update an user
     * @param user new rate
     * @return updated user
     */
    public GenericResponse<User> putUser(User user){
        try {
            List<User> updatedUser = new ArrayList<>();
            updatedUser.add(usp.putUser(user));
            return new GenericResponse<>(HttpStatus.OK, "successful", true, updatedUser);
        } catch (UserPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }

    /**
     * Method add credits to an user
     * @param id of the user
     * @param credits to add
     * @return total of user's credits
     */
    public GenericResponse<Integer> addCredits(String id, int credits){
        try {
            List<Integer> newRating = new ArrayList<>();
            newRating.add(usp.addCredits(id, credits));
            return new GenericResponse<>(HttpStatus.OK, "successful", true, newRating);
        } catch (UserPersistenceException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }

    /**
     * Method to get a user
     * @param id of the user
     * @return the user
     */
    public GenericResponse<User> getUser(String id){
        try {
            List<User> userList = new ArrayList<>();
            userList.add(usp.getUser(id));
            return new GenericResponse<>(HttpStatus.OK, "successful", true, userList);
        } catch (UserNotFoundException e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }
}
