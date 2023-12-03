package com.enigma.entity;

public class Theater {
    private Integer id;
    private String theaterNumber;
    private Integer stock;
    private Integer filmId;
    private String filmTitle;

    public Theater(Integer id, String theaterNumber, Integer stock,  String filmTitle) {
        this.id = id;
        this.theaterNumber = theaterNumber;
        this.stock = stock;
        this.filmTitle = filmTitle;
    }

    public Theater(String theaterNumber, Integer stock, Integer filmId) {
        this.theaterNumber = theaterNumber;
        this.stock = stock;
        this.filmId = filmId;
    }

    public Theater(Integer id, String theaterNumber, Integer stock, Integer filmId) {
        this.id = id;
        this.theaterNumber = theaterNumber;
        this.stock = stock;
        this.filmId = filmId;
    }

    public Theater() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheaterNumber() {
        return theaterNumber;
    }

    public void setTheaterNumber(String theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "Theater : " +
                "id=" + id +
                ", theater number=" + theaterNumber +
                ", stock=" + stock +
                ", film title=" + filmTitle
                ;
    }
}
