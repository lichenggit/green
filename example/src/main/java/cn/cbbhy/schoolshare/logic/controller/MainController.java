package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/12/1 0001.
 * <p>
 * 对外开放入口
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/index.json")
    @ResponseBody
    public Object index() {
        return accountService.selectUserByUsername("licheng");
    }
}
