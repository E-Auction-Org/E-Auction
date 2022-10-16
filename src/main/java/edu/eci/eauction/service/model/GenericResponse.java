package edu.eci.eauction.service.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GenericResponse<T> {
    private HttpStatus code;
    private String message;
    private boolean state;
    private List<?> data;

    public GenericResponse(HttpStatus code, String message, boolean state, List<?> data) {
        this.code = code;
        this.message = message;
        this.state = state;
        this.data = data;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isState() {
        return state;
    }

    public List<?> getData() {
        return data;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
