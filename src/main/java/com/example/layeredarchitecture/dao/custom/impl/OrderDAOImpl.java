package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        return stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
    }
    //transacion

    @Override
    public void selectOrderId(String orderId) throws SQLException, ClassNotFoundException {
        Connection connection = TransactionConnection.setConnection();

        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);

        SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        /*if order id already exist*/
        if (stm.executeQuery().next()) {

        }
    }
    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {

       Connection connection =TransactionConnection.setAutoCommitFalse();

       PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
      stm.setString(1, orderId);
      stm.setDate(2, Date.valueOf(orderDate));
       stm.setString(3, customerId);


        if (stm.executeUpdate() != 1) {
            connection.rollback();
            TransactionConnection.setAutoCommitTrue();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet idGenerate() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
