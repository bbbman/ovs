package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.createVote.Image;

public interface ImageService {
	Integer saveImage(Image image);
	Image getImageById(Integer imageId);
	void updateImage(Image image);
	List<Image> selectImageByUserId(Integer userId);
	void deleteOneImageById(Integer id,Integer userId);
	void deleteMoreimageByIds(Integer[] imageIds,Integer userId);
	
	List<Image> selectByPageSeq(Integer pageSeq);
	void deleteByPageSeq(Integer pageSeq);
	
	void update2ZeroByPageSeq(Integer pageSeq);
}
