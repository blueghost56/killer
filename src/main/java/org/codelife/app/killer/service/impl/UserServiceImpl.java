package org.codelife.app.killer.service.impl;

import org.codelife.app.killer.mapper.UserMapper;
import org.codelife.app.killer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author csl
 * @create 07/26/2017 13:11
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
}
