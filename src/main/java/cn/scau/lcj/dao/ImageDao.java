package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.Image;

public interface ImageDao extends GenericDao<Image,Integer> {
	void updateImageById(String queryString);
	void updateImageByObject(Image image);
	List<Image> selectImageBySqlString(String sqlString);
	void deleteImageByUserId(Integer imageId,Integer userId);
	
	void deleteBySqlString(String sqlString);
	
}
