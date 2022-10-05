/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.controllers;

import edu.eci.eauction.service.services.EAuctionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/blueprints")
public class EAuctionAPIController {
    @Autowired
    EAuctionServices bps = null;

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuctions(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{user}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserAuctions(@PathVariable String user){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{user}/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthorBlueprintName(@PathVariable String user, @PathVariable String id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postBlueprint(@RequestBody String bp){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{user}/{id}")
    public ResponseEntity<?> putBlueprint(@PathVariable String user, @PathVariable String id, @RequestBody String bp){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{user}/{id}")
    public ResponseEntity<?> deleteBlueprint(@PathVariable String user, @PathVariable String id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
