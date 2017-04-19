package com.app.dao;

import com.app.model.po.AddressPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface AddressDao {

    int saveAddress(AddressPO addressPO);

    List<AddressPO> queryAddressByUserId(@Param("userId")Integer userId);

    AddressPO queryAddressById(@Param("id")Integer id);

}
