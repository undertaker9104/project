package com.group_art.model;

import java.util.List;

public interface Group_ArtDAO_interface {
	public void insert(Group_ArtVO group_ArtVO);
    public void update(Group_ArtVO group_ArtVO);
    public void delete(String grou_id);
    public List<Group_ArtVO> findByMemId(String mem_id);
    public Group_ArtVO getOneArt(String grou_id);
    public List<Group_ArtVO> getAll();
    public List<Group_ArtVO> getAllForTrack();
    public List<Group_ArtVO> getAllBySr(String art_name);
}
