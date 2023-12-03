package com.enigma.repository;

import com.enigma.entity.Film;

import java.util.List;

public interface FilmRepo {
    List<Film> getAll();
    void getById (Integer id);
    void save (Film film);
    void update(Film film);
    void delete (Integer id);
}
