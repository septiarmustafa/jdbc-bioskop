package com.enigma;

import com.enigma.configure.DbConnector;
import com.enigma.entity.*;
import com.enigma.repository.impl.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        Connection conn = dbConnector.startConnection();

        // CUSTOMER
        CustomerRepoImpl cust = new CustomerRepoImpl(conn);
//        cust.getAll();
//        cust.delete(30);
//        cust.getById(720);
//        cust.save(new Customer("tiar","2000-09-09"));
//        cust.update(new Customer(31,"Septiar mustafa","2000-09-01"));

        // FILM
        FilmRepoImpl film = new FilmRepoImpl(conn);
//        film.getAll();
//        film.getById(124);
//        film.save(new Film("Psikopat III", 100, "2023-01-15", 30000, 1));
//        film.update(new Film(14, "Psikopat II", 90, "2023-01-20", 25000, 3));
//        film.delete(6);

        // SEAT
        SeatRepoImpl seat = new SeatRepoImpl(conn);
//        seat.getAll();
//        seat.getById(90);
//        seat.update(new Seat(68,"B1",5));
//        try {
//            seat.save(new Seat("A9", 5));
//            seat.delete(90);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }

        // THEATER
        TheaterRepoImpl theater = new TheaterRepoImpl(conn);
//        theater.getAll();
//        theater.getById(121);
//        theater.save(new Theater("FF",20,1));
//        theater.update(new Theater(7,"Theater 7",25,1));
//        theater.delete(6);

        // TICKET
        TicketRepoImpl ticket = new TicketRepoImpl(conn);
//        ticket.buyTicket(new Ticket(7,33));
        ticket.update(new Ticket(61,83,3));
//        ticket.delete(39);
//        ticket.getById(61);
//        ticket.getList(ticket.getAll());

        // RATING
        RatingRepoImpl rating = new RatingRepoImpl(conn);
//        rating.getAll();
//        rating.getById(31);
//        rating.save(new Rating("AA", "Example"));
//        rating.update(new Rating(5,"ABC", "Example"));
//        rating.delete(6);
    }
}