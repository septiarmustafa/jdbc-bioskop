package com.enigma.repository;

import com.enigma.entity.Theater;

import java.util.List;

public interface TheaterRepo {
    List<Theater> getAll();
    Theater getById (Integer id);
    void save (Theater theater);
    void update(Theater theater);
    void delete (Integer id);
}
