package com.enigma.entity;

public class Customer {
    private Integer id;
    private String name;
    private String birth_date;

    public Customer(String name, String birth_date) {
        this.name = name;
        this.birth_date = birth_date;
    }

    public Customer(Integer id, String name, String birth_date) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
    }

    public Customer (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Customer : " +
                "name= " + name +
                ", birth_date= " + birth_date ;
    }
}
