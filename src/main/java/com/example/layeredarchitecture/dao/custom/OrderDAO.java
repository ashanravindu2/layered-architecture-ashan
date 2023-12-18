package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.*;
import java.time.LocalDate;

public interface OrderDAO {
     ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException;

     void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;

     boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
}
