package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public  class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

       ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> allItems = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            allItems.add(itemDTO);
        }
        return allItems;
    }
    @Override
public boolean delete(String code) throws SQLException, ClassNotFoundException {
//    Connection connection = DBConnection.getDbConnection().getConnection();
//    PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
//    pstm.setString(1, code);
//    pstm.executeUpdate();
        SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
        return false;
    }
    @Override
public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
//    Connection connection = DBConnection.getDbConnection().getConnection();
//    PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
//    pstm.setString(1, itemDTO.getCode());
//    pstm.setString(2, itemDTO.getDescription());
//    pstm.setBigDecimal(3, itemDTO.getUnitPrice());
//    pstm.setInt(4, itemDTO.getQtyOnHand());
//    return pstm.executeUpdate() > 0;
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(), itemDTO.getQtyOnHand());
}
    @Override
public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
//    Connection connection = DBConnection.getDbConnection().getConnection();
//    PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
//    pstm.setString(1, itemDTO.getDescription());
//    pstm.setBigDecimal(2, itemDTO.getUnitPrice());
//    pstm.setInt(3, itemDTO.getQtyOnHand());
//    pstm.setString(4, itemDTO.getCode());
//    return pstm.executeUpdate() > 0;
     return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", itemDTO.getDescription(),itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(),itemDTO.getCode());

    }
    @Override
public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT code FROM item WHERE code=?",code);
        return rst.next();
}
    @Override
public ResultSet idGenerate() throws SQLException, ClassNotFoundException {
//    Connection connection = DBConnection.getDbConnection().getConnection();
//    ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
//    return rst;
       return SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
}
    @Override
public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {
//    Connection connection = DBConnection.getDbConnection().getConnection();
//    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
//    pstm.setString(1, newItemCode + "");
//    ResultSet rst = pstm.executeQuery();
//    rst.next();
//        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
//        return item;

        ResultSet rst =  SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode);
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;

}
}
