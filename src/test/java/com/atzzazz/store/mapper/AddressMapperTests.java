package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddress() {
        Address address = new Address();
        address.setUserId(14);
        address.setName("xiaogongju");
        address.setPhone("123456");

        Integer rows = 0;
        for (int i = 0; i < 15; i++) {
            rows = addressMapper.insertAddress(address);
            rows++;
        }

        System.out.println(rows);

    }


    @Test
    public void countUserAddressByUserId() {
        Integer rows = addressMapper.countUserAddressByUserId(14);
        System.out.println(rows);
    }

    @Test
    public void selectAddressByUserId() {
        List<Address> list = addressMapper.selectAddressByUserId(15);
        list.forEach(System.err::println);
    }


    @Test
    public void findByAddressId() {
        Address byAddressId = addressMapper.findByAddressId(24);
        System.err.println(byAddressId);
    }

    ;

    @Test
    public void updateNonDefault() {
        Integer rows = addressMapper.updateNonDefault(15);
        System.err.println(rows);
    }

    ;

    @Test
    public void updateDefaultByAddressId() {
        Integer rows = addressMapper.updateDefaultByAddressId(25, "admin", new Date());
        System.out.println(rows);
    }

    ;

}
