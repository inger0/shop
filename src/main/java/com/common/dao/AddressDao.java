package com.common.dao;

import com.common.model.po.AddressPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface AddressDao {

    int saveAddress(AddressPO addressPO);

    List<AddressPO> queryAddressByUserId(@Param("userId") Integer userId);

    AddressPO queryAddressById(@Param("id") Integer id);

    AddressPO queryAddressByStatus(@Param("status") Integer status, @Param("userId") Integer userId);


}
