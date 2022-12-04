package edu.eci.eauction.service.persistence;

import edu.eci.eauction.service.model.User;

public interface IUserPersistence {
    User register(User user) throws UserPersistenceException;
}
