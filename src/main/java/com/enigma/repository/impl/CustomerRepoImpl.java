package com.enigma.repository.impl;

import com.enigma.entity.Customer;
import com.enigma.repository.CustomerRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CustomerRepo {

    Connection conn;

    public CustomerRepoImpl(Connection conn) {
        this.conn = conn;
    }
    public CustomerRepoImpl() {
    }

    @Override
    public void getAll() {
        ArrayList<Customer> data = new ArrayList<>();
        try{
            PreparedStatement pr = conn.prepareStatement("select * from m_customer;");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                data.add(new Customer(result.getString("name"),result.getString("birth_date")));
            }
            getListCustomer(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer getById(Integer id) {
        Customer customer = null;
        try {
            PreparedStatement pr = conn.prepareStatement("SELECT * from m_customer WHERE id =" + id + ";");
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                customer = new Customer(result.getInt("id"),result.getString("name"),result.getString("birth_date"));
            }
            pr.close();
        } catch (SQLException e){
            System.out.println("Failed get data customer by Id " + e.getMessage());
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        try{
            PreparedStatement pr = conn.prepareStatement("insert into m_customer (name, birth_date) VALUES (?,?)");
            pr.setString(1,customer.getName());
            pr.setDate(2,Date.valueOf(customer.getBirth_date()));
            pr.executeUpdate();
            System.out.println("success add new data : " + customer.getName());
            pr.close();
        } catch (SQLException e) {
            System.out.println("failed save data : "+e.getMessage());
        }
    }

    @Override
    public void update(Customer customer) {
        try{
            PreparedStatement pr = conn.prepareStatement("update m_customer set name=?, birth_date=? where id=?;");
            pr.setString(1,customer.getName());
            pr.setDate(2,Date.valueOf(customer.getBirth_date()));
            pr.setInt(3,customer.getId());

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
            PreparedStatement pr = conn.prepareStatement("DELETE from m_customer WHERE id =" + id + ";");
            int updated = pr.executeUpdate();
            if (updated > 0) System.out.println("success delete data");
            else System.out.println("no data deleted");
            pr.close();

        } catch (SQLException e){
            System.out.println("Failed delete : " + e.getMessage());
        }
    }

    public void getListCustomer(ArrayList<Customer> list){
        System.out.println("\nList Customer: ");
        int index = 0;
        try {
            for (Customer cust: list) {
                Customer customer;
                index++;
                customer = cust;
                System.out.println(customer == null ? "There's no customer\n" : index +". " + customer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
