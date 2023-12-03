package com.enigma.repository.impl;

import com.enigma.entity.Ticket;
import com.enigma.repository.TicketRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class TicketRepoImpl implements TicketRepo {
    Connection conn;

    public TicketRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Ticket> getAll() {
        ArrayList<Ticket> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select txs.id, ts.seat_number, c.name from trx_ticket txs join t_seat ts on txs.seat_id = ts.id join m_customer c on txs.customer_id = c.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Ticket(result.getInt("id"), result.getString("seat_number"), result.getString("name")));
            }
//            getList(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public Ticket getById(Integer id) {
        Ticket ticket = null;
        try {
            PreparedStatement pr = conn.prepareStatement("select ts.id, ts.seat_number, c.name from trx_ticket txs join t_seat ts on txs.seat_id = ts.id join m_customer c on txs.customer_id = c.id where ts.id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                ticket = new Ticket(result.getInt("id"), result.getString("seat_number"),result.getString("name"));
            }
            System.out.println(ticket == null ? "Ticket not found" : "Found " + ticket);
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
        return ticket;
    }

    @Override
    public void save(Ticket ticket) throws SQLException {
        try{
             conn.setAutoCommit(false);
                int theaterId = 0;
                PreparedStatement pr = conn.prepareStatement("insert into trx_ticket (seat_id, customer_id) VALUES (?,?)");
                pr.setInt(1,ticket.getSeatId());
                pr.setInt(2, ticket.getCustomerId());
                pr.executeUpdate();
                pr.close();
                List<Ticket> tickets = getAll();
                int trxId =  tickets.get(tickets.size()-1).getId();
                PreparedStatement getTheaterId = conn.prepareStatement("select txs.*,ts.*, tt.* from trx_ticket txs right join t_seat ts on txs.seat_id = ts.id right join t_theater tt on ts.theater_id = tt.id where txs.id= " + trxId + ";");
                ResultSet resultId = getTheaterId.executeQuery();

                while (resultId.next()) {
                    theaterId = resultId.getInt("theater_id");
                }
                if (theaterId != 0) {
                    PreparedStatement pr2 = conn.prepareStatement("update t_theater set stock = (stock - 1) where id="+ theaterId +";");
                    pr2.executeUpdate();
                    pr2.close();
                }
                getTheaterId.close();
                conn.commit();
                System.out.println("Berhasil membeli tiket, selamat menonton!");

        } catch (SQLException e) {
            conn.rollback();
            System.out.println("failed save data ticket : "+e.getMessage());
        }
        conn.setAutoCommit(true);

    }

    public void buyTicket (Ticket ticket){
        try {
            LocalDate birthdate = null;
            int age = 0;
            PreparedStatement getAge = conn.prepareStatement("select * from m_customer where id=?;");
            getAge.setInt(1,ticket.getCustomerId());
            ResultSet resultAge = getAge.executeQuery();

            while (resultAge.next()) {
                birthdate = resultAge.getDate("birth_date").toLocalDate();
            }

            if (birthdate != null) {
                Period period = Period.between(birthdate, LocalDate.now());
                age = period.getYears();
            }

            int ratingId = 0;
            String ratingCode = "";
            PreparedStatement getRating = conn.prepareStatement("select ts.*,tt.*, tf.*, tr.* from t_seat ts join t_theater tt on ts.theater_id = tt.id join t_film tf on tt.film_id =tf.id join t_rating tr on tf.rating_id = tr.id where ts.id =?;");
            getRating.setInt(1,ticket.getSeatId());
            ResultSet resultRating = getRating.executeQuery();
            while (resultRating.next()) {
                ratingId = resultRating.getInt("rating_id");
                ratingCode = resultRating.getString("code");
            }

            if (ratingId == 1) {
                System.out.println("Film kategori umum");
                save(ticket);
            } else if ((ratingId == 2 && age < 13) ||(ratingId == 3 && age < 18) ||(ratingId == 4 && age < 21)){
                System.out.println("Maaf, usia anda "+ age +" tidak memenuhi syarat untuk menonton film kategori "+ ratingCode);
            } else {
                save(ticket);
            }
            getAge.close();
            getRating.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Ticket ticket) {
        Ticket tickets;
        tickets = getById(ticket.getId());
        if (tickets == null) {
            System.out.println("Cannot update ticket");
        } else {
            try{
                PreparedStatement pr = conn.prepareStatement("update trx_ticket set seat_id=?, customer_id=? where id=?;");
                pr.setInt(1, ticket.getSeatId());
                pr.setInt(2, ticket.getCustomerId());
                pr.setInt(3, ticket.getId());

                int updated = pr.executeUpdate();
                System.out.println(updated > 0 ? "success update ticket" : "no data ticket updated");
                pr.close();

            } catch (SQLException e) {
                System.out.println("Failed update : " +e.getMessage());
            }
        }
        
    }

    @Override
    public void delete(Integer id) {
        Ticket ticket = getById(id);
        if (ticket != null ){
            try {
                PreparedStatement pr = conn.prepareStatement("DELETE from trx_ticket WHERE id =" + id + ";");
                int updated = pr.executeUpdate();
                System.out.println(updated > 0 ? "success delete ticket" : "no ticket deleted");
                pr.close();

            } catch (SQLException e){
                System.out.println("Failed delete : " + e.getMessage());
            }
        }else  {
            System.out.println("Cannot delete ticket");
        }
        
    }

    public void getList(List<Ticket> list){
        System.out.println("\nList Ticket: ");
        int index = 0;
        try {
            for (Ticket ticket: list) {
                Ticket tickets;
                index++;
                tickets = ticket;
                System.out.println(tickets == null ? "There's no ticket\n" : index +". " + tickets);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
