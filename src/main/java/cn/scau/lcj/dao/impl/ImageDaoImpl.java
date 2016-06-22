package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.common.FileUtil;
import cn.scau.lcj.dao.ImageDao;
import cn.scau.lcj.entity.createVote.Image;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Image load(Integer id) {
		// TODO Auto-generated method stub
		return (Image) this.getCurrentSession().load(Image.class, id);
	}

	@Override
	public Image get(Integer id) {
		// TODO Auto-generated method stub
		return (Image) this.getCurrentSession().get(Image.class, id);
	}

	@Override
	public List<Image> findAll() {
		// TODO Auto-generated method stub
		List<Image> list = this.getCurrentSession().createQuery("from Image")
				.list();
		return list;
	}

	@Override
	public void persist(Image entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(Image entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Image entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Image image = this.load(id);
		this.getCurrentSession().delete(image);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

	@Override
	public void updateImageById(String queryString) {
		this.getCurrentSession().createSQLQuery(queryString).executeUpdate();
	}

	@Override
	public void updateImageByObject(Image entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().update(entity);
	}

	@Override
	public List<Image> selectImageBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		List<Image> list = this.getCurrentSession().createSQLQuery(sqlString)
				.addEntity(Image.class).list();
		return list;
	}

	@Override
	public void deleteImageByUserId(Integer imageId, Integer userId) {
		// TODO Auto-generated method stub
		Image image = this.load(imageId);
		if (image != null && userId == image.getUserId()) {
			// 删除文件应该弄个独立的线程，出来去删，暂时先这样子删除
			if (FileUtil.delFile(image.getImagePath()))
				this.getCurrentSession().delete(image);
		}
	}

	@Override
	public void deleteBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
		this.flush();
	}

}
