package com.enigma.entity;

public class Ticket {
    private Integer id;
    private Integer seatId;
    private Integer customerId;
    private String customerName;
    private String seatName;


    public Ticket(Integer id,  String seatName, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.seatName = seatName;
    }

    public Ticket(Integer seatId, Integer customerId) {
        this.seatId = seatId;
        this.customerId = customerId;
    }

    public Ticket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seat=" + seatName +
                ", customer=" + customerName +
                '}';
    }
}
