package com.appeal.api.portfolio.util;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.YoungWooTemplateOne;

public class PortfolioFactory {

    private static String YOUNGWOO_TEMPLATE = "template2";

    public static Portfolio createPortfolio(String dtype) {
        if(dtype.equals(YOUNGWOO_TEMPLATE))
            return new YoungWooTemplateOne();
        return null;
    }
}
