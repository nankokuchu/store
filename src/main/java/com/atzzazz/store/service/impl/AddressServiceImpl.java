package com.atzzazz.store.service.impl;

import com.atzzazz.store.mapper.AddressMapper;
import com.atzzazz.store.pojo.Address;
import com.atzzazz.store.service.IAddressService;
import com.atzzazz.store.service.ex.AddressCountLimitException;
import com.atzzazz.store.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Value("${user.address.max-count}")
    private Integer maxCount;

    /**
     * @param address
     * @param userId
     * @param userName
     */
    @Override
    public void addNewAddress(Address address, Integer userId, String userName) {

        //ユーザーが登録している住所をチェックする
        Integer count = addressMapper.countUserAddressByUserId(userId);


        //登録している住所の個数が20個异常の場合登録できないようにする
        if (count > maxCount) {
            throw new AddressCountLimitException("登録できる住所が超えています");
        }

        //住所を初めて登録する際には、デフォルト住所に設定する
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);


        //addressの情報を設定する
        address.setUserId(userId);
        address.setCreatedUser(userName);
        address.setCreatedTime(new Date());
        address.setModifiedUser(userName);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insertAddress(address);

        //データ登録されているかをチェックする
        Util.checkoutRows(rows);
    }

    @Override
    public List<Address> getAddressByUserId(Integer userId) {
        List<Address> list = addressMapper.selectAddressByUserId(userId);
        for (Address address : list){
            address.setProvinceName(null);
            address.setCityName(null);
            address.setAreaName(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

}
