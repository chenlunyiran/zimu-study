package com.zimu.thread.latch;

/**
 * ClassName:ProductInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-05-23 10:50 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class ProductInfo {

    private String name;

    private String price;

    ProductInfo(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
