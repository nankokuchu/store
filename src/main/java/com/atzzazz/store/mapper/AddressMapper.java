package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

    Integer insertAddress(Address address);

    Integer countUserAddressByUserId(Integer userId);

    List<Address> selectAddressByUserId(@Param("userId") Integer userId);
}
