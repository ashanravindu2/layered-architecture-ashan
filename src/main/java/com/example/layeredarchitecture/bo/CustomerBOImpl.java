package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException{
    //customer business logic example
        return customerDAO.save(customerDTO);

    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.idGenerate();
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

}
