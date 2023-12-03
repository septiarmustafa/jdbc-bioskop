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
//        cust.getById(70);
//        cust.save(new Customer("tiar","2000-09-09"));
//        cust.update(new Customer(31,"Septiar mus","2000-10-20"));

        // FILM
        FilmRepoImpl film = new FilmRepoImpl(conn);
//        film.getAll();
//        film.getById(14);
//        film.save(new Film("Psikopat II", 100, "2023-12-15", 30000, 1));
//        film.update(new Film(14, "Psikopat II", 90, "2023-01-20", 30000, 1));
//        film.delete(6);

        // SEAT
        SeatRepoImpl seat = new SeatRepoImpl(conn);
//        seat.getAll();
//        seat.getById(90);
//        seat.update(new Seat(68,"F2",4));
//        try {
//            seat.save(new Seat("A7", 5));
//            seat.delete(90);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }

        // THEATER
        TheaterRepoImpl theater = new TheaterRepoImpl(conn);
//        theater.getAll();
//        theater.getById(1);
//        theater.save(new Theater("F",20,1));
//        theater.update(new Theater(6,"F",25,1));
//        theater.delete(6);

        // TICKET
        TicketRepoImpl ticket = new TicketRepoImpl(conn);
//        ticket.buyTicket(new Ticket(77,33));
//        ticket.update(new Ticket(1,1,2));
//        ticket.delete(39);
//        ticket.getById(1);
//        ticket.getList(ticket.getAll());

        // RATING
        RatingRepoImpl rating = new RatingRepoImpl(conn);
//        rating.getAll();
//        rating.getById(1);
//        rating.save(new Rating("AA", "Example"));
//        rating.update(new Rating(5,"AB", "Example"));
//        rating.delete(1);
    }
}