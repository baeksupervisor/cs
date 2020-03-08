package com.baeksupervisor.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Hello????");
        return "index";
    }
}
