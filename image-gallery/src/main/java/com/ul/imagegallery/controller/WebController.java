package com.ul.imagegallery.controller;

import com.ul.imagegallery.model.UserDto;
import com.ul.imagegallery.services.picture.PictureService;
import com.ul.imagegallery.services.user.UserService;
import com.ul.imagegallery.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;


    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/add")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") UserDto userDto,
            HttpServletRequest request,
            Errors errors) {


        String firstName = userDto.getFirstName();
        userService.saveUser(firstName, userDto.getPassword());


        return new ModelAndView("successRegister", "user", userDto);
    }

    @GetMapping("/")
    public String index(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        byte[] picture = pictureService.getPicture();
        model.addAttribute("user", userDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userDto.setFirstName(authentication.getName());
        userDto.setImgUtil(new ImageUtil());
        userDto.setImgData(picture);
        return "index";
    }

}