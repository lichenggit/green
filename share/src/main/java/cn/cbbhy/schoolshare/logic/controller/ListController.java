package cn.cbbhy.schoolshare.logic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/1 0001.
 *
 *
 */
@Controller
@RequestMapping("/list")
public class ListController {

    @RequestMapping("/home.html")
    public String home(){
        return "redirect:/home/index.html";
    }

    @RequestMapping("/article.html")
    public String article(){
        return "redirect:/article/index.html";
    }

    @RequestMapping("/need.html")
    public String need(){
        return "redirect:/need/index.html";
    }
}
