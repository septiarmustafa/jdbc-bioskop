package com.enigma.repository;
import com.enigma.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketRepo {
    List<Ticket> getAll();
    Ticket getById (Integer id);
    void save (Ticket ticket) throws SQLException;
    void update(Ticket ticket);
    void delete (Integer id);
}
