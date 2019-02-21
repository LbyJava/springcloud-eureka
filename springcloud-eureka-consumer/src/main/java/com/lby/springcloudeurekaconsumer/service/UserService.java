package com.lby.springcloudeurekaconsumer.service;

import com.lby.springcloudeurekaconsumer.config.RestUtils;
import com.lby.springcloudeurekaconsumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: TSF
 * @Description:gitTest
 * @Date: Create in 2018/12/7 19:06
 */
@Service
public class UserService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //    接收list
//    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
//    public List<User> getUser(){
//        //选择调用的服务的名称
//        //ServiceInstance 封装了服务的基本信息，如 IP，端口
//        ServiceInstance si = this.loadBalancerClient.choose("eureka-provider");
//        //拼接访问服务的URL
//        StringBuffer sb = new StringBuffer();
//        //http://localhost:9090/user
//        sb.append("http://").append(si.getHost()).append(":").append(si.getPort()).append("/user");
//
//        //打印调用了那个服务
//        System.out.println("stringBuffer = " + sb);
//
//        //springMVC RestTemplate
//        RestTemplate rt = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        ParameterizedTypeReference<List<User>> type = new ParameterizedTypeReference<List<User>>() {};
//        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(headers);
//        //ResponseEntity:封装了返回值信息
//        ResponseEntity<List<User>> response = rt.exchange(sb.toString(),HttpMethod.GET,request, type);
//        List<User> list =response.getBody();
//        return list;
//    }

    /**
     * 接收一个list
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
    public List<User> getUser() {
        List user = RestUtils.getUrlList(loadBalancerClient.choose("eureka-provider"), "get/users", User.class);
        return user;
    }

    /**
     * 传入一个pojo接收list
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
    public List<User> postUsers() {
        User user = new User();
        user.setName("Eriri");
        user.setPass("5433");
        user.setAge(17);
        List users = RestUtils.postUrlList(loadBalancerClient.choose("eureka-provider"), "post/users",user, User.class);
        return users;
    }

//    /**
//     * 传入一个pojo接收list
//     * @return
//     */
//    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
//    public User postUser() {
//        User user = new User();
//        user.setName("Eriri");
//        user.setPass("5433");
//        user.setAge(17);
//        User postUrlObject = RestUtils.postUrlObject(loadBalancerClient.choose("eureka-provider"), "post/user", user, User.class);
//        return postUrlObject;
//    }

    /**
     * 传入map
     * @return
     */
//    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
//    public List<User> getUser() {
//        ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-provider");
//        StringBuffer stringBuffer = new StringBuffer();
//        //拼接URL
//        stringBuffer.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/user");
//        //打印调用了那个服务
//        System.out.println("stringBuffer = " + stringBuffer);
//        //springMvc RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
//        User user = new User();
//        user.setName("Eriri");
//        user.setPass("5433");
//        user.setAge(17);
//        map.add("user", user);
//        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity( stringBuffer.toString(), request , String.class );
//        System.out.println(response.getBody());
//
//        return null;
//    }

    /**
     * 传入pojo
     * @return
     */
//    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
//    public List<User> getUser() {
//        ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-provider");
//        StringBuffer stringBuffer = new StringBuffer();
//        //拼接URL
//        stringBuffer.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/user");
//        //打印调用了那个服务
//        System.out.println("stringBuffer = " + stringBuffer);
//        //springMvc RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//
//        User user = new User();
//        user.setName("Eriri");
//        user.setPass("5433");
//        user.setAge(17);
//        ResponseEntity<String> response = restTemplate.postForEntity( stringBuffer.toString(), user , String.class );
//        System.out.println(response.getBody());
//
//        return null;
//    }

    public List<User> getUserFallbackMethod() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("方法出错误，请重试", null, 0));
        return users;
    }
}
