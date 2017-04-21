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

    UserPO queryUserByInvitationCode(@Param("invitationCode")String invitationCode);

    int updateHeadImgById(@Param("headImg")String headImg,@Param("userId")int userId);

    int updatePayPassword(@Param("payPassword")String payPassword,@Param("userId")int userId);

    int updateTelephone(@Param("telephone")String telephone,@Param("userId")int userId);

    int saveUser(@Param("telephone")String telephone, @Param("inviterId")int InviterId);


}
