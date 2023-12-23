package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


   @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
            allCustomer.add(customerDTO);
        }
        return allCustomer;
    }
    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress());
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
    }
    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());
        pstm.executeUpdate() ;
*/
        SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());

        return false;
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        pstm.executeUpdate();*/
        SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
        return false;
    }
    @Override
    public ResultSet idGenerate() throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        return rst;*/
       return SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    }
    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));

    }

}
