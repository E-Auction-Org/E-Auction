package edu.eci.eauction.service.persistence.impl;

import edu.eci.eauction.repositories.EAuctionUserArtefactory;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.persistence.IUserPersistence;
import edu.eci.eauction.service.persistence.UserPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPersistence implements IUserPersistence {
    @Autowired
    EAuctionUserArtefactory atf = null;
    @Override
    public User register(User user) throws UserPersistenceException {
        if (atf.userExists(user)) {
            throw new UserPersistenceException("User already exists");
        }
        return atf.registerUser(user);
    }
}
