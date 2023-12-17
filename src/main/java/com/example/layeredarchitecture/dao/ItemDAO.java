package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    void itemDelete(String code) throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean itemUpdate(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean existsItem(String code) throws SQLException, ClassNotFoundException ;

    ResultSet itemCodeGenerate() throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;
}
