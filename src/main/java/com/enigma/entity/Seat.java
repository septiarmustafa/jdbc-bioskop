package com.enigma.entity;

public class Seat {
    private Integer id;
    private  String seatNumber;
    private Integer theaterId;
    private String theaterNumber;

    public Seat(Integer id, String seatNumber, Integer theaterId, String theaterNumber) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
        this.theaterNumber = theaterNumber;
    }

    public Seat(Integer id, String seatNumber, Integer theaterId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
    }

    public Seat(String seatNumber, Integer theaterId) {
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
    }

    public Seat() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public String toString() {
        return "Seat : " +
                "id= " + id +
                ", seat number= " + seatNumber +
                ", theater number= " + theaterNumber
                ;
    }
}
