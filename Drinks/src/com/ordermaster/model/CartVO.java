package com.ordermaster.model;

import java.io.Serializable;

public class CartVO implements Serializable {
    private byte[] product_img;
    private String product_id;
    private String product_name;
    private String ice_id;
    private String sweet_id;
    private String take;
    private Integer quantity;
    private Integer price;

    public CartVO() {
        super();
    }

    public CartVO(String product_id, String ice_id, String sweet_id, Integer quantity, Integer price) {
        this.product_id = product_id;
        this.ice_id = ice_id;
        this.sweet_id = sweet_id;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartVO cartVO = (CartVO) o;

        if (product_id != null ? !product_id.equals(cartVO.product_id) : cartVO.product_id != null)
            return false;
        if (ice_id != null ? !ice_id.equals(cartVO.ice_id) : cartVO.ice_id != null) return false;
        if (sweet_id != null ? !sweet_id.equals(cartVO.sweet_id) : cartVO.sweet_id != null)
            return false;

        return true;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getIce_id() {
        return ice_id;
    }

    public void setIce_id(String ice_id) {
        this.ice_id = ice_id;
    }

    public String getSweet_id() {
        return sweet_id;
    }

    public void setSweet_id(String sweet_id) {
        this.sweet_id = sweet_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public byte[] getProduct_img() {
        return product_img;
    }

    public void setProduct_img(byte[] product_img) {
        this.product_img = product_img;
    }

    public String getTake() {
        return take;
    }

    public void setTake(String take) {
        this.take = take;
    }
}

