package edu.eci.eauction.service.persistence;

import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.model.User;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public interface IUserPersistence {
    User register(User user) throws UserPersistenceException;

    /**
     * Method that update user rating
     * @param id of the user
     * @param rate new rate
     * @return updated rating
     * @throws UserPersistenceException if there is no auctions
     */
    public Float putRating(String id, Float rate) throws UserPersistenceException;

    /**
     * Method that update an user
     * @param user new rate
     * @return updated rating
     */
    public User putUser(User user) throws UserPersistenceException;

    /**
     * Method add credits to an user
     * @param id of the user
     * @param credits to add
     * @return total of user's credits
     */
    public Integer addCredits(String id, int credits) throws UserPersistenceException;

    /**
     * Method to get a user
     * @param id of the user
     * @return the user
     */
    public User getUser(String id) throws UserNotFoundException;
}
