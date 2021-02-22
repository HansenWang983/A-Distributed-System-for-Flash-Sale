package com.wangzehao.flashsale.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    private Double salePrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
