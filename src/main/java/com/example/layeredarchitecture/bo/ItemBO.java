package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
   ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

  void itemDelete(String code) throws SQLException, ClassNotFoundException;

  boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

   boolean itemUpdate(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean existsItem(String code) throws SQLException, ClassNotFoundException ;

    ResultSet itemCodeGenerate() throws SQLException, ClassNotFoundException;

   ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;

}
