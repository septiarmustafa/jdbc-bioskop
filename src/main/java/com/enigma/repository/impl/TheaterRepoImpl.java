package com.enigma.repository.impl;

import com.enigma.entity.Theater;
import com.enigma.repository.TheaterRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheaterRepoImpl implements TheaterRepo {

    Connection conn;

    public TheaterRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void getAll() {
        ArrayList<Theater> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select tt.*, tf.title from t_theater tt join t_film tf on tt.film_id = tf.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Theater(result.getInt("id"), result.getString("theater_number"), result.getInt("stock"),result.getString("title")));
            }
            getList(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
            System.out.println(theater == null ? "Theater not found" : "Found " + theater);
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
        Theater theaters;
        theaters = getById(theater.getId());
        if (theaters == null) {
            System.out.println("Cannot update theater");
        } else {
            try{
                PreparedStatement pr = conn.prepareStatement("update t_theater set theater_number=?, stock=?, film_id=? where id=?;");
                pr.setString(1, theater.getTheaterNumber());
                pr.setInt(2, theater.getStock());
                pr.setInt(3, theater.getFilmId());
                pr.setInt(4, theater.getId());
                int updated = pr.executeUpdate();
                System.out.println(updated > 0 ? "success update theater" : "no data theater updated");
                pr.close();
            } catch (SQLException e) {
                System.out.println("Failed update : " +e.getMessage());
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Theater theater = getById(id);
        try {
            if (theater != null) {
                PreparedStatement pr = conn.prepareStatement("DELETE from t_theater WHERE id =" + id + ";");
                int updated = pr.executeUpdate();
                System.out.println(updated > 0 ? "success delete theater" : "no data theater deleted");
                pr.close();
            } else  {
                System.out.println("Cannot delete theater");
            }


        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }
    }

    public void getList(ArrayList<Theater> list){
        System.out.println("\nList Theater: ");
        int index = 0;
        try {
            for (Theater theater: list) {
                Theater theaters;
                index++;
                theaters = theater;
                System.out.println(theaters == null ? "There's no theater\n" : index +". " + theaters);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
