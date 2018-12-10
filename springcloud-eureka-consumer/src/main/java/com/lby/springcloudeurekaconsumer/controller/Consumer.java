package com.lby.springcloudeurekaconsumer.controller;

import com.lby.springcloudeurekaconsumer.pojo.User;
import com.lby.springcloudeurekaconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/7 21:21
 */
@RestController
public class Consumer {
    @Autowired
    UserService userService;
    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/consumer")
    public List<User> consumer() throws Exception {
        List<User> user = userService.getUser();
        System.out.println("user = " + user);
        return user;
    }
}
