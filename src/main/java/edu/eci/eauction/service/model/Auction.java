
package edu.eci.eauction.service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@Document(collection="auctions")
public class Auction {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    private String creator=null;
    
    private int price;

    private String name = null;

    private Date start;

    private int duration;

    private String url;

    private int seq;

    public Auction(String creator, int price, String name, Date start, int duration,  String url) {
        this.creator = creator;
        this.price = price;
        this.name = name;
        this.start = start;
        this.duration = duration;
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
        return price == auction.price && duration == auction.duration &&
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
