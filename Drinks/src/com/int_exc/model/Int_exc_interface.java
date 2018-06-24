package com.int_exc.model;

import java.util.List;


public interface Int_exc_interface {
	public void insert(Int_excVO int_excVO);
    public void update(Int_excVO int_excVO);
    public void delete(String int_exc_rec_id);
    public Int_excVO findByPrimaryKeyInt_exc_rec_id(String int_exc_rec_id);
    public Int_excVO findByPrimaryKeyMem_id(String mem_id);
    public List<Int_excVO> getAll();
}
