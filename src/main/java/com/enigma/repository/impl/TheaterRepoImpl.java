package com.enigma.repository.impl;

import com.enigma.entity.Customer;
import com.enigma.entity.Seat;
import com.enigma.entity.Theater;
import com.enigma.repository.TheaterRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheaterRepoImpl implements TheaterRepo {

    Connection conn;

    public TheaterRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Theater> getAll() {
        List<Theater> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select tt.*, tf.title from t_theater tt join t_film tf on tt.film_id = tf.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Theater(result.getInt("id"), result.getString("theater_number"), result.getInt("stock"),result.getString("title")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public Theater getById(Integer id) {
        Theater theater = null;
        try {
            PreparedStatement pr = conn.prepareStatement("select tt.*, tf.title from t_theater tt join t_film tf on tt.film_id = tf.id where tt.id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                theater = new Theater(result.getInt("id"), result.getString("theater_number"), result.getInt("stock"),result.getString("title"));
            }
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
        return theater;
    }

    @Override
    public void save(Theater theater) {
        try{
            PreparedStatement pr = conn.prepareStatement("insert into t_theater (theater_number, stock, film_id) VALUES (?,?,?)");
            pr.setString(1,theater.getTheaterNumber());
            pr.setInt(2, theater.getStock());
            pr.setInt(3, theater.getFilmId());


            pr.executeUpdate();
            System.out.println("success add new data : " + theater.getTheaterNumber());
            pr.close();
        } catch (SQLException e) {
            System.out.println("failed save data : "+e.getMessage());
        }
    }

    @Override
    public void update(Theater theater) {
        try{
            PreparedStatement pr = conn.prepareStatement("update t_theater set theater_number=?, stock=?, film_id=? where id=?;");
            pr.setString(1, theater.getTheaterNumber());
            pr.setInt(2, theater.getStock());
            pr.setInt(3, theater.getFilmId());
            pr.setInt(4, theater.getId());
            int updated = pr.executeUpdate();
            if (updated > 0) System.out.println("success update data");
            else System.out.println("no data updated");
            pr.close();

        } catch (SQLException e) {
            System.out.println("Failed update : " +e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement pr = conn.prepareStatement("DELETE from t_theater WHERE id =" + id + ";");
            int updated = pr.executeUpdate();
            if (updated > 0) System.out.println("success delete data");
            else System.out.println("no data deleted");
            pr.close();

        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }
    }
}
