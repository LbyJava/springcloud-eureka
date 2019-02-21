package com.lby.springcloudeurekaconsumer.controller;

import com.lby.springcloudeurekaconsumer.pojo.User;
import com.lby.springcloudeurekaconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public List<User> consumer(HttpServletRequest request, String message) throws Exception {
        System.out.println("message = " + message);
        List<User> user = userService.getUser();
        return user;
    }

    @GetMapping("/get/users")
    public List<User> getUsers(HttpServletRequest request) throws Exception {
        String message = (String) request.getAttribute("message");
        System.out.println("message = " + message);
        List<User> user = userService.postUsers();
        return user;
    }

//    @GetMapping("/get/user")
//    public User getUser() throws Exception {
//        User user = userService.postUser();
//        return user;
//    }
}
