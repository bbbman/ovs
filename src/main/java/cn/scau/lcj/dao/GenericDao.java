package cn.scau.lcj.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,PK extends Serializable> {
	
	T load(PK id);
	
	T get(PK id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	Integer save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(PK id);
	
	void flush();	
}
