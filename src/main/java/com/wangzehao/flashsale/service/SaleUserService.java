package com.wangzehao.flashsale.service;

import com.wangzehao.flashsale.dao.SaleUserDao;
import com.wangzehao.flashsale.domain.SaleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleUserService {

    @Autowired
    SaleUserDao saleUserDao;


}
