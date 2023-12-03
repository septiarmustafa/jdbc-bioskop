package com.enigma.repository;

import com.enigma.entity.Rating;

public interface RatingRepo {
    void getAll();
    Rating getById (Integer id);
    void save (Rating rating);
    void update(Rating rating);
    void delete (Integer id);
}
