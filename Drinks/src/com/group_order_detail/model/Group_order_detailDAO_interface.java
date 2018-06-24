package com.group_order_detail.model;

import java.util.List;
import java.util.Set;


import com.group_art.model.Group_ArtVO;

public interface Group_order_detailDAO_interface {

    public void insert(Group_order_detailVO group_order_detailVO);
    public void update(Group_order_detailVO group_order_detailVO);
    public void delete(String grou_id,String mem_id,String product_id,String sweet_id,String ice_id,String ord_id);
    public void deleteByMem_id(String mem_id);
    public Group_order_detailVO findGrou_id(String grou_id);
    public Group_order_detailVO findMem_id(String mem_id);
    public Group_order_detailVO getThreeInfo(String grou_id);
    public List<Group_order_detailVO> findByGrou_id(String grou_id);
    public List<Group_order_detailVO> findMem(String grou_id);
    public List<Group_order_detailVO> getAll();
    public List<Group_order_detailVO> findMemPrice(String mem_id,String grou_id);
    public List<Group_order_detailVO> everymem_id(String grou_id);
}
