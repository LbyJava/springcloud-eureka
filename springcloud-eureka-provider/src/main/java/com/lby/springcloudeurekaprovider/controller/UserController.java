package com.lby.springcloudeurekaprovider.controller;

import com.lby.springcloudeurekaprovider.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/post/users")
    public List<User> users(@RequestBody User user) throws Exception {
        System.out.println("user.getName() = " + user.getName());
        System.out.println("user.getPass() = " + user.getPass());
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("a", "d", 1));
        users.add(new User("b", "e", 2));
        users.add(new User("c", "f", 3));
        return users;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/get/users")
    public List<User> users() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("a", "d", 1));
        users.add(new User("b", "e", 2));
        users.add(new User("c", "f", 3));
        return users;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/get/user")
    public User user(@RequestBody User user) throws Exception {
        System.out.println("user = " + user);
        return new User("a", "d", 1);
    }
}
