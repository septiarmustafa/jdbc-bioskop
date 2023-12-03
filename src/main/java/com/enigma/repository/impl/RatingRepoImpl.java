package com.enigma.repository.impl;

import com.enigma.entity.Customer;
import com.enigma.entity.Rating;
import com.enigma.repository.RatingRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingRepoImpl implements RatingRepo {

    Connection conn;

    public RatingRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Rating> getAll() {
        List<Rating> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select * from t_rating;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Rating(result.getString("code"),result.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public Rating getById(Integer id) {
        Rating rating = null;
        try {
            PreparedStatement pr = conn.prepareStatement("SELECT * from t_rating WHERE id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                rating = new Rating(result.getInt("id"),result.getString("code"),result.getString("description"));
            }
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data by Id " + e.getMessage());
        }
        return rating;
    }

    @Override
    public void save(Rating rating) {
        try{
            PreparedStatement pr = conn.prepareStatement("insert into t_rating (code, description) VALUES (?,?)");
            pr.setString(1,rating.getCode());
            pr.setString(2,rating.getDesc());

            pr.executeUpdate();
            System.out.println("success add new data : " + rating.getCode());
            pr.close();
        } catch (SQLException e) {
            System.out.println("failed save data : "+e.getMessage());
        }
    }

    @Override
    public void update(Rating rating) {
        try{
            PreparedStatement pr = conn.prepareStatement("update t_rating set code=?, description=? where id=?;");
            pr.setString(1,rating.getCode());
            pr.setString(2,rating.getDesc());
            pr.setInt(3,rating.getId());

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
            PreparedStatement pr = conn.prepareStatement("DELETE from t_rating WHERE id =" + id + ";");
            int updated = pr.executeUpdate();
            if (updated > 0) System.out.println("success delete data");
            else System.out.println("no data deleted");
            pr.close();

        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }
    }
}
