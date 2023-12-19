package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;

public interface QueryDAO {

    void orderDetail() throws SQLException, ClassNotFoundException;
}
