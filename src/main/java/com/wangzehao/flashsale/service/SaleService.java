package com.wangzehao.flashsale.service;

import com.wangzehao.flashsale.domain.OrderInfo;
import com.wangzehao.flashsale.domain.SaleOrder;
import com.wangzehao.flashsale.domain.SaleUser;
import com.wangzehao.flashsale.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo sale(SaleUser user, GoodsVo goods) {
        // reduce stock & make order
        boolean success = goodsService.reduceStock(goods);
        if(success){
            return orderService.createOrder(user,goods) ;
        }else {
            return null;
        }
    }

    public long getSaleResult(Long userId, long goodsId) {
        SaleOrder order = orderService.getSaleOrderByUserIdGoodsId(userId, goodsId);
        if(order != null) {// sale success
            return order.getOrderId();
        }
        return 0;
    }

}
