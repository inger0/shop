package com.app.dao;

import com.app.model.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Repository
public interface UserDao {
    UserPO queryUserById(@Param("userId") Integer userId);
}
