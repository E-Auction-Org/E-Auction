package edu.eci.eauction.service.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    @NonNull
    private Float rate;

    public Rating () {}

    public Rating(@NonNull Float rate) {
        this.rate = rate;
    }
}
