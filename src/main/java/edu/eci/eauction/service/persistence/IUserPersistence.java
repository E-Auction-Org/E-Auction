package edu.eci.eauction.service.persistence;

import edu.eci.eauction.service.model.User;

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
}
