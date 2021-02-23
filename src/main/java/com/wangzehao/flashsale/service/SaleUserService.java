package com.wangzehao.flashsale.service;

import com.wangzehao.flashsale.dao.SaleUserDao;
import com.wangzehao.flashsale.domain.SaleUser;
import com.wangzehao.flashsale.util.Md5;
import com.wangzehao.flashsale.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class SaleUserService {

    @Autowired
    private SaleUserDao saleUserDao;

    public static final String COOKIE_NAME_TOKEN = "token";
    private static Logger logger = LoggerFactory.getLogger(SaleUserService.class);

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo==null){
            return false;
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        SaleUser user = saleUserDao.getByNickname(mobile);
        if(user==null){
            return false;
        }
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        if(!Md5.inputPass2DBPass(password,salt).equals(dbPass)){
            return false;
        }
        String token = UUID.randomUUID().toString().replace("-","");
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

}
