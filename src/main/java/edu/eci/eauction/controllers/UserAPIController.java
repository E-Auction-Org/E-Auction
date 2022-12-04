/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.controllers;

import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.GenericResponse;
import edu.eci.eauction.service.services.AuctionServices;
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
    //public LoginUser
}
