package com.common.dao;

import com.common.model.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Repository
public interface UserDao {
    UserPO queryUserById(@Param("userId") Integer userId);

    UserPO queryUserByUUID(@Param("UUID")String UUID);

    UserPO queryUserByInvitationCode(@Param("invitationCode")String invitationCode);

    int updateHeadImgById(@Param("headImg")String headImg,@Param("userId")int userId);

    int updatePayPassword(@Param("payPassword")String payPassword,@Param("userId")int userId);

    int updateTelephone(@Param("telephone")String telephone,@Param("userId")int userId);

    int saveUser(@Param("telephone")String telephone, @Param("inviterId")int InviterId);

    int savePO(UserPO userPO);

    UserPO queryUserByStatus(@Param("status") Integer status);

    UserPO queryUserByTelephone(@Param("telephone") String telephone);


    UserPO queryUserByLoginCode(@Param("loginCode")String loginCode);

    List<UserPO> queryUsersByInviterId(@Param("inviterId")Integer inviterId);

    int updatePO(UserPO userPO);
}
