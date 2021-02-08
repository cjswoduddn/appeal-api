package com.appeal.api.portfolio;

public class PortfolioFactory {

    private static String YOUNGWOO_TEMPLATE = "template2";

    public static Portfolio createPortfolio(String dtype) {
        if(dtype.equals(YOUNGWOO_TEMPLATE))
            return new YoungWooTemplateOne();
        return null;
    }
}
