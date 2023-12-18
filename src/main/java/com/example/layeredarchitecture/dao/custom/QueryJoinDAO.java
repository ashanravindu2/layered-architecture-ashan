package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.security.PublicKey;

public interface QueryJoinDAO {

    void customerOrderDetail(CustomerDTO customerDTO);
}
