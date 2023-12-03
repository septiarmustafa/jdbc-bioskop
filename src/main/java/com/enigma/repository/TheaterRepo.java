package com.enigma.repository;

import com.enigma.entity.Theater;

public interface TheaterRepo {
    void getAll();
    Theater getById (Integer id);
    void save (Theater theater);
    void update(Theater theater);
    void delete (Integer id);
}
