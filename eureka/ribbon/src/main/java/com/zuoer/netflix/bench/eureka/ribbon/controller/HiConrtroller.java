package com.zuoer.netflix.bench.eureka.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuoer.netflix.bench.eureka.ribbon.service.HiService;

/**
 * 
 * 调用service
 * @author zuoer
 *
 * @version $Id: HiConrtroller.java, v 0.1 2019年11月14日 上午10:38:57 zuoer Exp $
 */
@Controller
public class HiConrtroller {

	@Autowired
    private HiService hiService;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    @ResponseBody
    public String sayHi(@RequestParam String message) {
        return hiService.sayHi(message);
    }
    
}
