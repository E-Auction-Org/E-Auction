/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.controllers;

import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.model.Rating;
import edu.eci.eauction.service.model.User;
import edu.eci.eauction.service.services.AuctionServices;
import edu.eci.eauction.service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author JuanRojasCastr & javier32rojas040506
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "v1/user")
public class UserAPIController {

    @Autowired
    UserServices us;

    @RequestMapping(path = "/register" ,method = RequestMethod.POST)
    public GenericResponse<User> postUser(@RequestBody User user){;
        return us.register(user);
    }

    @RequestMapping(path = "/rate/{userId}", method = RequestMethod.PUT)
    public GenericResponse<Float> rateUser(@PathVariable String userId, @RequestBody Rating rate) {
        return us.rateUser(userId, rate.getRate());
    }

    @PutMapping
    public GenericResponse<User> putUser(@RequestBody User user) {
        return us.putUser(user);
    }
}
