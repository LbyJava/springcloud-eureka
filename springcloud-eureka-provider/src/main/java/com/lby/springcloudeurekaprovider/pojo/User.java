package com.lby.springcloudeurekaprovider.pojo;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/7 18:49
 */
public class User {
    public User() {
    }

    public User(String name, String pass, int age) {
        this.name = name;
        this.pass = pass;
        this.age = age;
    }

    private String name;
    private String pass;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
