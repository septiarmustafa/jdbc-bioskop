package com.enigma.repository.impl;

import com.enigma.entity.Seat;
import com.enigma.repository.SeatRepo;

import java.sql.*;
import java.util.ArrayList;

public class SeatRepoImpl implements SeatRepo  {
    Connection conn;

    public SeatRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void getAll() {
        ArrayList<Seat> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select ts.*, tt.theater_number from t_seat ts join t_theater tt on ts.theater_id = tt.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Seat(result.getInt("id"), result.getString("seat_number"), result.getInt("theater_id"), result.getString("theater_number")));
            }
            getList(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Seat getById(Integer id) {
        Seat seat = null;
        try {
            PreparedStatement pr = conn.prepareStatement("select ts.*, tt.theater_number from t_seat ts join t_theater tt on ts.theater_id = tt.id where ts.id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                seat = new Seat(result.getInt("id"), result.getString("seat_number"), result.getInt("theater_id"), result.getString("theater_number"));
            }

            System.out.println(seat == null ? "Seat not found" : "Found " + seat);

            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
        return seat;
    }

    @Override
    public void save(Seat seat) throws SQLException {
        conn.setAutoCommit(false);
        try {
            PreparedStatement pr = conn.prepareStatement("insert into t_seat (seat_number, theater_id) VALUES (?,?)");
            pr.setString(1,seat.getSeatNumber());
            pr.setInt(2, seat.getTheaterId());
            pr.executeUpdate();
            PreparedStatement pr2 = conn.prepareStatement("update t_theater set stock = stock + 1 where id =" + seat.getTheaterId() +";");
            pr2.executeUpdate();
            conn.commit();
            System.out.println("success add new seat : " + seat.getSeatNumber());
            pr.close();
            pr2.close();
        } catch (SQLException e) {
            System.out.println("failed save seat : "+e.getMessage());
        }
        conn.setAutoCommit(true);
    }

    @Override
    public void update(Seat seat) {
        seat = getById(seat.getId());
        try{
            if (seat == null) {
                System.out.println("Cannot update seat");
            } else {
                PreparedStatement pr = conn.prepareStatement("update t_seat set seat_number=?, theater_id=? where id=?;");
                pr.setString(1, seat.getSeatNumber());
                pr.setInt(2, seat.getTheaterId());
                pr.setInt(3, seat.getId());

                int updated = pr.executeUpdate();
                if (updated > 0) System.out.println("success update seat " + seat.getSeatNumber());
                else System.out.println("no data updated");
                pr.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed update : " +e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Seat seat = getById(id);
        if (seat != null) {
        conn.setAutoCommit(false);
        try {
            PreparedStatement pr = conn.prepareStatement("DELETE from t_seat WHERE id =" + id + ";");
            int updated = pr.executeUpdate();

            PreparedStatement pr2 = conn.prepareStatement("update t_theater set stock = stock - 1 where id =" + seat.getTheaterId() +";");
            pr2.executeUpdate();
            conn.commit();

            if (updated > 0) System.out.println("success delete seat");
            else System.out.println("no seat deleted");

            pr.close();
            pr2.close();

        } catch (SQLException e) {
            System.out.println("failed save seat : "+e.getMessage());
            conn.rollback();
        }
        conn.setAutoCommit(true);
        } else {
            System.out.println("Cannot delete seat");
        }
    }
    public void getList(ArrayList<Seat> list){
        System.out.println("\nList Seat: ");
        int index = 0;
        try {
            for (Seat seat: list) {
                Seat seats;
                index++;
                seats = seat;
                System.out.println(seats == null ? "There's no seat\n" : index +". " + seats);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
