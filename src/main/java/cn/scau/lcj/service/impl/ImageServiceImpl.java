package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.ImageDao;
import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.service.ImageService;
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDao imageDao;

	@Override
	public Integer saveImage(Image image) {
		// TODO Auto-generated method stub
		return imageDao.save(image);
	}

	@Override
	public Image getImageById(Integer imageId) {
		// TODO Auto-generated method stub
		return imageDao.get(imageId);
	}

	@Override
	public void updateImage(Image image) {
		// TODO Auto-generated method stub
		imageDao.updateImageByObject(image);
	}

	@Override
	public List<Image> selectImageByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
		String sqlString = "select * from image where user_id = "+userId;
		
		return imageDao.selectImageBySqlString(sqlString);
	}

	@Override
	public void deleteOneImageById(Integer id,Integer userId) {
		// TODO Auto-generated method stub
		imageDao.deleteImageByUserId(id, userId);
	}

	@Override
	public void deleteMoreimageByIds(Integer[] imageIds,Integer userId) {
		// TODO Auto-generated method stub
		for(int i=0;i<imageIds.length;i++){
			Integer imageId = imageIds[i];
			if(imageId !=null){
				imageDao.deleteImageByUserId(imageId, userId);
			}
		}
	}

	@Override
	public List<Image> selectByPageSeq(Integer pageSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from image where page_id = "+pageSeq);
		return imageDao.selectImageBySqlString(sb.toString());
	}

	@Override
	public void deleteByPageSeq(Integer pageSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("delete from image where page_id = "+pageSeq);
	}

	@Override
	public void update2ZeroByPageSeq(Integer pageSeq) {
		// TODO Auto-generated method stub
		String sb = "update image set browse_times = 0 where page_id = "+pageSeq;
		imageDao.updateImageById(sb);
	}
}
