package edu.eci.eauction.service.persistence.impl;

import edu.eci.eauction.repositories.EAuctionUserArtefactory;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.persistence.IUserPersistence;
import edu.eci.eauction.service.persistence.UserPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Float putRating(String id, Float rate) throws UserPersistenceException {
        Float newRating = atf.putRating(id, rate);
        if (newRating == -1F) throw new UserPersistenceException("Something went wrong");
        return newRating;
    }

    @Override
    public User putUser(User user) throws UserPersistenceException {
        Optional<User> optionalUser = atf.getUser(user.getId());
        if (optionalUser.isPresent()) {
            User oldUser = optionalUser.get();
            String newUserName = user.getUserName();
            String newMail = user.getMail();
            if (!newUserName.equals(oldUser.getUserName())) oldUser.setUserName(newUserName);
            if (!newMail.equals(oldUser.getMail())) oldUser.setMail(newMail);
            return atf.putUser(oldUser);
        }
        else throw new UserPersistenceException("Something went wrong");
    }
}
