package com.enigma.repository;
import com.enigma.entity.Customer;

public interface CustomerRepo {
    void getAll();
    Customer getById (Integer id);
    void save (Customer customer);
    void update(Customer customer);
    void delete (Integer id);
}
