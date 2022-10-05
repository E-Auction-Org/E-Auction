/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.eauction.service.persistence.impl;

import edu.eci.eauction.service.model.Auction;
import edu.eci.eauction.service.model.Point;
import edu.eci.eauction.service.persistence.AuctionNotFoundException;
import edu.eci.eauction.service.persistence.AuctionPersistenceException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Service
public class EAuctionPersistence implements edu.eci.eauction.service.persistence.EAuctionPersistence {

    private final ConcurrentHashMap<Tuple<String,String>, Auction> auctions =new ConcurrentHashMap<>();

    public EAuctionPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 11)};
        Auction bp=new Auction("_authorname_", "_bpname_",pts);
        Auction bp1=new Auction("pedro","casa38", pts1);
        Auction bp2=new Auction("juan","casa39", pts1);
        Auction bp3=new Auction("pedro","casa40", pts1);
        Auction bp4=new Auction("pedro","casa41", pts1);
        Auction bp5=new Auction("juan","casa42", pts1);
        auctions.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        auctions.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        auctions.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        auctions.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        auctions.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
        auctions.put(new Tuple<>(bp5.getAuthor(),bp5.getName()), bp5);
    }

    @Override
    public Set<Auction> getAuctions() throws AuctionNotFoundException {
        Set<Auction> authorAuctions = new HashSet<>();
        for (Tuple<String,String> key: auctions.keySet()){
            authorAuctions.add(getAuction(key.o1, key.o2));
        }
        return authorAuctions;
    }

    @Override
    public Auction getAuction(String user, String id) throws AuctionNotFoundException {
        return auctions.get(new Tuple<>(user, id));
    }

    @Override
    public Set<Auction> getUserAuction(String user) throws AuctionNotFoundException {
        Set<Auction> authorAuctions = new HashSet<>();
        for (Tuple<String,String> key: auctions.keySet()){
            if(key.o1.equals(user)){
                authorAuctions.add(getAuction(user, key.o2));
            }
        }
        if (authorAuctions.size() == 0) throw new AuctionNotFoundException("Autor no encontrado");
        return authorAuctions;
    }

    @Override
    public void postAuction(Auction auction) throws AuctionPersistenceException {
        if (auctions.containsKey(new Tuple<>(auction.getAuthor(),auction.getName()))) throw new AuctionPersistenceException("This object already exists");
        else {
            auctions.put(new Tuple<>(auction.getAuthor(),auction.getName()), auction);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void putAuction(String user, String id , Auction bp) throws AuctionPersistenceException {
        if (!auctions.containsKey(new Tuple<>(user,id))) throw new AuctionPersistenceException("This object doesn't exists");
        else
        {
            auctions.replace(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteAuction(String user, String id) throws AuctionPersistenceException {
        if (!auctions.containsKey(new Tuple<>(user,id))) throw new AuctionPersistenceException("This object doesn't exists");
        else
        {
            auctions.remove(new Tuple<>(user,id));
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
