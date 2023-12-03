package com.enigma.repository.impl;

import com.enigma.entity.Film;
import com.enigma.repository.FilmRepo;

import java.sql.*;
import java.util.ArrayList;

public class FilmRepoImpl implements FilmRepo {
    Connection conn;

    public FilmRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void getAll() {
        ArrayList<Film> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select tf.*, tr.code from t_film tf join t_rating tr on tf.rating_id = tr.id;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Film(result.getInt("id"),result.getString("title"), result.getInt("duration"), result.getString("show_date"),result.getInt("price"), result.getInt("rating_id"), result.getString("code")));
            }
            getList(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Film getById(Integer id) {
        Film film = null;
        try {
            PreparedStatement pr = conn.prepareStatement("select tf.*, tr.code from t_film tf join t_rating tr on tf.rating_id = tr.id where tf.id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                film = new Film(result.getInt("id"),result.getString("title"), result.getInt("duration"), result.getString("show_date"),result.getInt("price"), result.getInt("rating_id"), result.getString("code"));
            }
            System.out.println(film == null ? "Film not found" : "Found " + film);
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
        return film;
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
            System.out.println("success add new film : title= " + film.getTitle() + ", duration= " + film.getDuration() + ", Date= " + film.getShowDate() +", Price= " + film
                    .getPrice());
            pr.close();
        } catch (SQLException e) {
            System.out.println("failed save data : "+e.getMessage());
        }

    }

    @Override
    public void update(Film film) {
        Film films;
        films = getById(film.getId());
        try{
            if (films == null) {
                System.out.println("Cannot update film");
            } else {
                PreparedStatement pr = conn.prepareStatement("update t_film set title=?, duration=?, show_date=?, price=?, rating_id=? where id=?;");
                pr.setString(1, film.getTitle());
                pr.setInt(2, film.getDuration());
                pr.setDate(3,Date.valueOf(film.getShowDate()));
                pr.setInt(4,film.getPrice());
                pr.setInt(5,film.getRatingId());
                pr.setInt(6, film.getId());

                int updated = pr.executeUpdate();
                if (updated > 0) System.out.println("success update film " + film.getTitle());
                else System.out.println("no data film updated");
                pr.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed update film : " +e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        Film film = getById(id);
            try {
                if (film != null) {
                    PreparedStatement pr = conn.prepareStatement("DELETE from t_film WHERE id =" + id + ";");
                    int updated = pr.executeUpdate();
                    if (updated > 0) System.out.println("success delete film");
                    else System.out.println("no film deleted");
                    pr.close();
                } else  {
                    System.out.println("Cannot delete film");
                }
            } catch (SQLException e){
                System.out.println("Failed delete film : " + e.getMessage());
            }
    }

    public void getList(ArrayList<Film> list){
        System.out.println("\nList Film: ");
        int index = 0;
        try {
            for (Film film: list) {
                Film films;
                index++;
                films = film;
                System.out.println(films == null ? "There's no film\n" : index +". " + films);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
