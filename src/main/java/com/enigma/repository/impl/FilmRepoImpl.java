package com.enigma.repository.impl;

import com.enigma.entity.Film;
import com.enigma.entity.Rating;
import com.enigma.repository.FilmRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepoImpl implements FilmRepo {
    Connection conn;

    public FilmRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Film> getAll() {
        List<Film> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select tf.*, tr.code from t_film tf join t_rating tr on tf.rating_id = tr.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Film(result.getInt("id"),result.getString("title"), result.getInt("duration"), result.getString("show_date"),result.getInt("price"), result.getInt("rating_id"), result.getString("code")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public void getById(Integer id) {
        Film film = null;
        try {
            PreparedStatement pr = conn.prepareStatement("select tf.*, tr.code from t_film tf join t_rating tr on tf.rating_id = tr.id where tf.id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                film = new Film(result.getInt("id"),result.getString("title"), result.getInt("duration"), result.getString("show_date"),result.getInt("price"), result.getInt("rating_id"), result.getString("code"));
            }
            pr.close();
            System.out.println("Found : " + film );
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
    }

    @Override
    public void save(Film film) {
        try{
            PreparedStatement pr = conn.prepareStatement("insert into t_film (title, duration, show_date, price, rating_id) VALUES (?,?,?,?,?)");
            pr.setString(1,film.getTitle());
            pr.setInt(2, film.getDuration());
            pr.setDate(3, Date.valueOf(film.getShowDate()));
            pr.setInt(4, film.getPrice());
            pr.setInt(5, film.getRatingId());

            pr.executeUpdate();
            System.out.println("success add new data : " + film.getTitle());
            pr.close();
        } catch (SQLException e) {
            System.out.println("failed save data : "+e.getMessage());
        }

    }

    @Override
    public void update(Film film) {
        try{
            PreparedStatement pr = conn.prepareStatement("update t_film set title=?, duration=?, show_date=?, price=?, rating_id=? where id=?;");
            pr.setString(1, film.getTitle());
            pr.setInt(2, film.getDuration());
            pr.setDate(3,Date.valueOf(film.getShowDate()));
            pr.setInt(4,film.getPrice());
            pr.setInt(5,film.getRatingId());
            pr.setInt(6, film.getId());

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
            PreparedStatement pr = conn.prepareStatement("DELETE from t_film WHERE id =" + id + ";");
            int updated = pr.executeUpdate();
            if (updated > 0) System.out.println("success delete data");
            else System.out.println("no data deleted");
            pr.close();

        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }

    }
}
