package com.wangzehao.flashsale.controller;

import com.wangzehao.flashsale.domain.SaleUser;
import com.wangzehao.flashsale.service.GoodsService;
import com.wangzehao.flashsale.service.SaleUserService;
import com.wangzehao.flashsale.service.UserService;
import com.wangzehao.flashsale.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SaleUserService saleUserService;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, SaleUser user){
        model.addAttribute("user", user);
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVoList);
        return render(request, response, model, "goods_list");
    }
}
