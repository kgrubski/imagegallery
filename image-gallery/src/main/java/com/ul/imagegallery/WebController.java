package com.ul.imagegallery;

import com.ul.imagegallery.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "You made it!";
    }

}