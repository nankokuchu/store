package com.atzzazz.store.service;

import com.atzzazz.store.mapper.AddressMapper;
import com.atzzazz.store.pojo.Address;
import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;


    @Test
    public void addNewAddress() {

        Address address = new Address();
        address.setName("xiaogongju");
        address.setPhone("123456");
        String userName = "admin";
        Integer userId = 2;

        addressService.addNewAddress(address,userId,userName);
    }

    @Test
    public void getAddressByUserId(){
        List<Address> list = addressService.getAddressByUserId(15);
        for (Address address : list) {
            System.err.println(address);
        }
    }

}
