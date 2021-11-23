package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/demo")
class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model){

       model.addAttribute("name","sfj");
       return "hello";
    }


    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
    User user =userService.getById(1);
    return Result.success(user);

}

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        try {
            userService.tx();
        }catch(Exception e){
            System.out.println("主键重复");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success(true);

    }

}