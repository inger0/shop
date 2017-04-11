package com.app.dao;

import com.app.model.po.OrderPO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface OrderDao {
    OrderPO queryOrderByUserIdAndStatus(@Param("status")int status,@Param("userId") int userId);

    //TODO 明天继续 完成OrderDao的编写
}
