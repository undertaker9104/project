package com.deposit_records.model;

import java.util.List;


public interface Deposit_records_interface {
	
	public void insert(Deposit_recordsVO deposit_recordsVO);
    public void update(Deposit_recordsVO deposit_recordsVO);
    public void delete(String dep_rec_id);
    public Deposit_recordsVO findByPrimaryKey(String dep_rec_id);
    public Deposit_recordsVO findByPrimaryKey_MemId(String mem_id);
    public List<Deposit_recordsVO> findByPrimaryKey_MemIdList(String mem_id);
    public List<Deposit_recordsVO> getAll();
    public List<Deposit_recordsVO> getAll_MemId();

}
