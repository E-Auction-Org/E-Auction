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
            return new GenericResponse<>(HttpStatus.CREATED, "sucessful", true, responseList);
        }catch (UserPersistenceException e){
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }
}
