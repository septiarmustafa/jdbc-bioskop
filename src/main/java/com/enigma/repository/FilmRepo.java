package com.enigma.repository;

import com.enigma.entity.Film;

public interface FilmRepo {
    void getAll();
    Film getById (Integer id);
    void save (Film film);
    void update(Film film);
    void delete (Integer id);
}
