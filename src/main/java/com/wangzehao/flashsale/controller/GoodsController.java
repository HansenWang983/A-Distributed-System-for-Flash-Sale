package com.wangzehao.flashsale.controller;

import com.wangzehao.flashsale.service.GoodsService;
import com.wangzehao.flashsale.service.SaleUserService;
import com.wangzehao.flashsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SaleUserService saleUserService;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model,)
}
