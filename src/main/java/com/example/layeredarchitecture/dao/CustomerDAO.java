package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> gellAllCustomer() throws SQLException, ClassNotFoundException;

    boolean customerSave(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    void customerUpdate(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    void customerDelete(String id) throws SQLException, ClassNotFoundException;

    ResultSet customerIdGenerate() throws SQLException, ClassNotFoundException ;

    String searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
