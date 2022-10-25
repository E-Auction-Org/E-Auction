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
@RequestMapping(value = "v1/auctions")
public class AuctionAPIController {
    @Autowired
    AuctionServices acs = null;

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse<Auction> getAuctions(){
        return acs.getAuctions();
    }

    @GetMapping(path = "/{user}/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse<Auction> getAuction(@PathVariable String user, @PathVariable String id){
        return acs.getAuction(user, id);
    }

    @GetMapping(path = "/{user}", produces=MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse<Auction> getUserAuctions(@PathVariable String user){
        return acs.getUserAuctions(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public GenericResponse<Auction> postAuction(@RequestBody Auction ac){
        return acs.postAuction(ac);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{user}/{id}")
    public GenericResponse<Auction> putAuction(@PathVariable String user, @PathVariable String id, @RequestBody Auction ac){
        return acs.putAuction(user, id, ac);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{user}/{id}")
    public GenericResponse<Auction> deleteAuction(@PathVariable String user, @PathVariable String id){
        return acs.deleteAuction(user, id);
    }
}
