package com.example.layeredarchitecture.bo;


import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailImpl;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO{
    ItemDAOImpl itemDAO = new ItemDAOImpl();
    OrderDAOImpl orderDAO = new OrderDAOImpl();
    OrderDetailImpl orderDetailimpl = new OrderDetailImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        boolean isOrderSaved ;
        boolean isOrderDetailSaved = false;
        boolean isItemUpdated = false;

            orderDAO.selectOrderId(orderId);

            isOrderSaved = orderDAO.saveOrder(orderId, orderDate, customerId);

            for (OrderDetailDTO detail : orderDetails) {
                isOrderDetailSaved = orderDetailimpl.saves(orderId, detail);

                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                isItemUpdated = itemDAO.update(item);
            }

            if (isOrderSaved && isOrderDetailSaved && isItemUpdated) {
                TransactionConnection.getConnection().commit();
                TransactionConnection.setAutoCommitTrue();
                return true;
            }

        return false;
    }


    public ItemDTO findItem(String code) {
        try {
            ItemDTO dto = itemDAO.search(code);

            return new ItemDTO(code,
                    dto.getDescription(),
                    dto.getUnitPrice(),
                    dto.getQtyOnHand()
            );

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

}



