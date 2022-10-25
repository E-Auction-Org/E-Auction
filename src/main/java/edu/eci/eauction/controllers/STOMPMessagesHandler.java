package edu.eci.eauction.controllers;

import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.services.AuctionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @Autowired
    AuctionServices acs = null;

    @MessageMapping("/auction.{auction_id}")
    public void handlePointEvent(Auction auction, @DestinationVariable String auction_id) throws Exception {
        System.out.println("Nuevo punto recibido en el servidor!: "+ auction_id);
        AtomicInteger auctionPrice = new AtomicInteger(auction.getPrice());
        auction.setPrice(auctionPrice.get());
        msgt.convertAndSend("/topic/auction."+auction_id,
                acs.putAuction(auction.getCreator(), auction.getId(), auction));
    }
}
