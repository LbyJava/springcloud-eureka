package com.lby.springcloudeurekaprovider.controller;

import com.lby.springcloudeurekaprovider.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/7 18:48
 */
@RestController
public class UserController {
    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/user")
    public List<User> user() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("a", "d", 1));
        users.add(new User("b", "e", 2));
        users.add(new User("c", "f", 3));
        return users;
    }
}
