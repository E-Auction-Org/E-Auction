/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.persistence;

/**
 *
 * @author JuanRojasCastr
 */
public class AuctionNotFoundException extends Exception{

    public AuctionNotFoundException(String message) {
        super(message);
    }

    public AuctionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
