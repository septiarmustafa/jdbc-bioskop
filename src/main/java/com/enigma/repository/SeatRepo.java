package com.enigma.repository;

import com.enigma.entity.Seat;

import java.sql.SQLException;

public interface SeatRepo {
    void getAll();
    Seat getById (Integer id);
    void save (Seat seat) throws SQLException;
    void update(Seat seat);
    void delete (Integer id) throws SQLException;
}
