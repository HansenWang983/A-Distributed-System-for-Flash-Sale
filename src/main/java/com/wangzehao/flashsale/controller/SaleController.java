package com.wangzehao.flashsale.controller;

import com.wangzehao.flashsale.domain.OrderInfo;
import com.wangzehao.flashsale.domain.SaleUser;
import com.wangzehao.flashsale.service.GoodsService;
import com.wangzehao.flashsale.service.OrderService;
import com.wangzehao.flashsale.service.SaleService;
import com.wangzehao.flashsale.service.SaleUserService;
import com.wangzehao.flashsale.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController implements InitializingBean {

    @Autowired
    private SaleUserService saleUserService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SaleService saleService;

    private static Logger logger = LoggerFactory.getLogger(SaleController.class);

    /**
     * system initialization
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        if (goodsList == null) {
            return;
        }
        for (GoodsVo goods : goodsList) {
//            缓存
//            redisService.set(GoodsKey.getMiaoshaGoodsStock, "" + goods.getId(), goods.getStockCount());
//            localOverMap.put(goods.getId(), false);
        }
    }

    @RequestMapping(value = "/verifyCodeRegister")
    @ResponseBody
    public String getVerifyCode(HttpServletResponse response){
        try {
            BufferedImage image = saleService.createVerifyCodeRegister();
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return "success";
        } catch (Exception e) {
            logger.error("generate verify code error in register{}", e);
            return "error";
        }
    }

    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public String getVerifyCode(HttpServletResponse response, SaleUser user,
                                                   @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return "error";
        }
        try {
            BufferedImage image = saleService.createVerifyCode(user, goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return "success";
        } catch (Exception e) {
            logger.error("generate verify code error in register{}", e);
            return "error";
        }
    }

    @RequestMapping(value="/path")
    @ResponseBody
    public String getSalePath(HttpServletRequest request, SaleUser user,
                              @RequestParam("goodsId") long goodsId,
                              @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode){
        if(user == null){
            return "error";
        }
//        boolean check = saleService.checkVerifyCode(user, goodsId, verifyCode);
//        if (!check) {
//            return "error";
//        }
//        String path = saleService.createSalePath(user, goodsId);
//        return path;
        return saleService.createBuyPath(user, goodsId);
    }

    @RequestMapping(value = "/do_sale")
    @ResponseBody
    public String placeOrder(HttpServletRequest request, SaleUser user,
                              GoodsVo goodsVo){
        if(user == null){
            return "error";
        }
//        boolean check = saleService.checkVerifyCode(user, goodsId, verifyCode);
//        if (!check) {
//            return "error";
//        }
//        String path = saleService.createSalePath(user, goodsId);
//        return path;
        System.out.println(goodsVo.getId());
        OrderInfo orderInfo = saleService.sale(user, goodsVo);
        return "success";
    }
}
