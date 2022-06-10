package com.atzzazz.store.controller;

import com.atzzazz.store.pojo.Address;
import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.IAddressService;
import com.atzzazz.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address,
                                          HttpSession session) {
        User result = getUserFromSession(session);
        Integer userId = result.getUserId();
        String userName = result.getUserName();
        addressService.addNewAddress(address, userId, userName);
        return new JsonResult<>(OK);
    }

    @GetMapping({"/",""})
    public JsonResult<List<Address>> getAddressByUserId(HttpSession session){
        User result = getUserFromSession(session);
        Integer userId = result.getUserId();
        List<Address> data = addressService.getAddressByUserId(userId);
        return new JsonResult<>(OK, data);

    }
}
