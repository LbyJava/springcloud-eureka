package com.lby.springcloudeurekaconsumer.service;

import com.lby.springcloudeurekaconsumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/7 19:06
 */
@Service
public class UserService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "getUserFallbackMethod")
    public List<User> getUser() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-provider");
        StringBuffer stringBuffer = new StringBuffer();
        //拼接URL
        stringBuffer.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/user");

        //打印调用了那个服务
        System.out.println("stringBuffer = " + stringBuffer);
        //springMvc RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<User>> parameterizedTypeReference = new ParameterizedTypeReference<List<User>>() {};

        //ResponseEntity:封装了返回值信息
        ResponseEntity<List<User>> listResponseEntity = restTemplate.exchange(stringBuffer.toString(), HttpMethod.GET, null, parameterizedTypeReference);

        List<User> list = listResponseEntity.getBody();
        return list;
    }

    public List<User> getUserFallbackMethod() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("方法出错误，请重试", null, 0));
        return users;
    }
}
