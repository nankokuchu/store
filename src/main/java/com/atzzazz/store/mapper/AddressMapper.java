package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    Integer insertAddress(Address address);

    Integer countUserAddressByUserId(Integer userId);

    List<Address> selectAddressByUserId(@Param("userId") Integer userId);

    /**
     * addressIdでアドレスを探す
     * @param addressId
     * @return
     */
    Address findByAddressId(@Param("addressId") Integer addressId);

    Integer updateNonDefault(@Param("userId") Integer userId);

    Integer updateDefaultByAddressId(@Param("AddressId") Integer addressId,
                                     @Param("modifiedUser") String modifiedUser,
                                     @Param("modifiedTime") Date modifiedTime);
}
