
package edu.eci.eauction.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection="auctions")
public class Auction {

    @Id
    private String id;

    private String creator=null;
    
    private int price;

    private String name = null;

    private Date start;

    private int duration;

    private String url;

    public Auction(String creator, int price, String name, Date start, int duration,  String url) {
        this.creator = creator;
        this.price = price;
        this.name = name;
        this.start = start;
        this.duration = duration;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public Date getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return price == auction.price && duration == auction.duration && Objects.equals(id, auction.id) &&
                Objects.equals(creator, auction.creator) && Objects.equals(name, auction.name) &&
                Objects.equals(start, auction.start) && Objects.equals(url, auction.url);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id='" + id + '\'' +
                ", creator='" + creator + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                ", url=" + url +
                '}';
    }
}
