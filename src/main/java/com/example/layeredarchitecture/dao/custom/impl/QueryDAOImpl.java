package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public void orderDetail() throws SQLException, ClassNotFoundException {
        System.out.println("OrderDetail\n");

        ResultSet rst = SQLUtil.execute("SELECT\n" +
                "    Orders.oid,\n" +
                "    Orders.date,\n" +
                "    Customer.id AS customerID,\n" +
                "    Customer.name AS customerName,\n" +
                "    OrderDetails.itemCode,\n" +
                "    Item.description AS itemDescription,\n" +
                "    OrderDetails.qty,\n" +
                "    OrderDetails.unitPrice AS orderDetailUnitPrice,\n" +
                "    Item.unitPrice AS itemUnitPrice\n" +
                "FROM\n" +
                "    Orders\n" +
                "        JOIN\n" +
                "    Customer ON Orders.customerID = Customer.id\n" +
                "        JOIN\n" +
                "    OrderDetails ON Orders.oid = OrderDetails.oid\n" +
                "        JOIN\n" +
                "    Item ON OrderDetails.itemCode = Item.code");

        while (rst.next()) {
            System.out.println("ORDER ID : "+rst.getString("oid"));
            System.out.println("ORDER DATE : "+rst.getString("date"));
            System.out.println("CUSTOMER ID ; "+rst.getString("customerID"));
            System.out.println("CUSTOMER NAME : "+rst.getString("customerName"));
            System.out.println("ITEM CODE "+rst.getString("itemCode"));
            System.out.println("ITEM DESCRIPTION : "+rst.getString("itemDescription"));
            System.out.println("QTY : "+rst.getString("qty"));
            System.out.println("ORDER TOTAL PRICE : "+rst.getString("orderDetailUnitPrice"));
            System.out.println("ITEM UNIT PRICE : "+rst.getString("itemUnitPrice")+"\n");

        }
    }
}
