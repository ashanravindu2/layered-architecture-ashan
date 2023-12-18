package com.example.layeredarchitecture.dao.custom.impl;


import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OrderDetailImpl implements OrderDetailDAO {

    @Override
    public boolean saves(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException {

        Connection connection = TransactionConnection.getConnection();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1, orderId);
        stm.setString(2, detail.getItemCode());
        stm.setBigDecimal(3, detail.getUnitPrice());
        stm.setInt(4, detail.getQty());

        if (stm.executeUpdate() != 1) {
            connection.rollback();
            TransactionConnection.setAutoCommitTrue();
            return false;
        }
        return true;
    }

}