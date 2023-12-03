package com.enigma.repository.impl;

import com.enigma.entity.Rating;
import com.enigma.repository.RatingRepo;

import java.sql.*;
import java.util.ArrayList;

public class RatingRepoImpl implements RatingRepo {

    Connection conn;

    public RatingRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void getAll() {
        ArrayList<Rating> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select * from t_rating;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Rating(result.getInt("id"), result.getString("code"),result.getString("description")));
            }
            getList(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
            System.out.println(rating == null ? "Rating not found" : "Found " + rating);
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get rating by Id " + e.getMessage());
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
        Rating rate;
        rate = getById(rating.getId());
        if (rate == null) {
            System.out.println("Cannot update rating");
        } else {
            try{
                PreparedStatement pr = conn.prepareStatement("update t_rating set code=?, description=? where id=?;");
                pr.setString(1,rating.getCode());
                pr.setString(2,rating.getDesc());
                pr.setInt(3,rating.getId());

                int updated = pr.executeUpdate();
                if (updated > 0) System.out.println("success update rating");
                else System.out.println("no data rating updated");
                pr.close();

            } catch (SQLException e) {
                System.out.println("Failed update : " +e.getMessage());
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Rating rating = getById(id);
        try {
            if (rating != null) {
                PreparedStatement pr = conn.prepareStatement("DELETE from t_rating WHERE id =" + id + ";");
                int updated = pr.executeUpdate();
                System.out.println(updated > 0 ? "success delete data" :"no data deleted" );
                pr.close();
            } else  {
                System.out.println("Cannot delete rating");
            }
        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }
    }

    public void getList(ArrayList<Rating> list){
        System.out.println("\nList Rating: ");
        int index = 0;
        try {
            for (Rating rating: list) {
                Rating ratings;
                index++;
                ratings = rating;
                System.out.println(ratings == null ? "There's no rating\n" : index +". " + ratings);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
