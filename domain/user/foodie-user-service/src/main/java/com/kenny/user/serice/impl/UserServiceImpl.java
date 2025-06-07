package com.kenny.user.serice.impl;

import com.kenny.enums.Sex;
import com.kenny.user.mapper.UsersMapper;
import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.UserBO;
import com.kenny.user.service.UserService;
import com.kenny.utils.DateUtil;
import com.kenny.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {

//        try {
//            Thread.sleep(3500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Default nickname is same as username
        user.setNickname(userBO.getUsername());
        // Default avatar
        user.setFace(USER_FACE);
        // Default birthday
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // Default gender is secret
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }
}
