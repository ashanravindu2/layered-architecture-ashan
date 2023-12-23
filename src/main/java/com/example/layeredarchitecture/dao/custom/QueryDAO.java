package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {

    void orderDetail() throws SQLException, ClassNotFoundException;
}
