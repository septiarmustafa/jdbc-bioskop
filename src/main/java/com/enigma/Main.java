package com.enigma;

import com.enigma.configure.DbConnector;
import com.enigma.entity.*;
import com.enigma.repository.impl.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        Connection conn = dbConnector.startConnection();

//        CustomerRepoImpl cust = new CustomerRepoImpl(conn);
//        cust.getAll();
//        System.out.println(cust.getById(31));
//        cust.save(new Customer("tiar","2000-08-09"));
//        cust.update(new Customer(31,"Septiar","2000-08-10"));

//        FilmRepoImpl film = new FilmRepoImpl(conn);
//        System.out.println(film.getAll());
//        film.getById(1);
//        film.save(new Film("Hantu Berkepala", 90, "2023-12-15", 30000, 1));
//        film.update(new Film(6, "Hantu Berkepala Dua", 100, "2023-12-15", 30000, 1));
//        film.delete(6);

//        SeatRepoImpl seat = new SeatRepoImpl(conn);
//        System.out.println(seat.getAll());
//        seat.getById(1);
//        try {
//            seat.save(new Seat("F3", 1));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }

//        seat.update(new Seat(59,"F2",2));
//        try {
//            seat.delete(62);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        TheaterRepoImpl theater = new TheaterRepoImpl(conn);
//        System.out.println(theater.getAll());
//        System.out.println(theater.getById(1));
//        theater.save(new Theater("F",20,1));
//        theater.update(new Theater(6,"F",25,1));
//        theater.delete(6);

        TicketRepoImpl ticket = new TicketRepoImpl(conn);
        ticket.buyTicket(new Ticket(29,31));
        //        ticket.update(new Ticket(1,1,2));
//        System.out.println(ticket.getAll());
//        ticket.delete(39);
//        ticket.getById(1);

    }
}