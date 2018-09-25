package com.test.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.LogManager;

@Controller
@RequestMapping("/")
public class UserController {
    private static List<User> userList;
    public UserController()
    {
        userList = new ArrayList<User>();
    }
    //注册界面
    @RequestMapping("/register0")
    public String register(){
        return "register";
    }
    @RequestMapping("/register")
    public String register(
            @RequestParam("userName")String userName,
            @RequestParam("password")String password
    ){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userList.add(user);
        return "login";
    }//成功跳转登陆界面
    @RequestMapping("/login0")
    public String Login(){
        return "login";
    }//直接跳转登录界
    @RequestMapping("/login")
    public String login(@RequestParam("userName")String userName, @RequestParam("password")String password, Model model){
        for (User user:userList){//到集合中查找用户是否存在，模拟数据库验证
            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                model.addAttribute("user",user);
                return "manager";//成功跳转
            }
        }
        return "login";
    }
    @RequestMapping(value = "/manager",method =RequestMethod.GET)
    private String manager(Model model){
        model.addAttribute("list",userList);
        return "manager";
    }

}
