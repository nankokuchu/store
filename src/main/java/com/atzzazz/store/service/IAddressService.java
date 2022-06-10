package com.atzzazz.store.service;

import com.atzzazz.store.pojo.Address;

import java.util.List;

public interface IAddressService {

    void addNewAddress(Address address,Integer userId,String userName);

    List<Address> getAddressByUserId(Integer userId);
}
