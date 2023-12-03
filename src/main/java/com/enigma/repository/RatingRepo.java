package com.enigma.repository;

import com.enigma.entity.Rating;

import java.util.List;

public interface RatingRepo {
    List<Rating> getAll();
    Rating getById (Integer id);
    void save (Rating rating);
    void update(Rating rating);
    void delete (Integer id);
}
