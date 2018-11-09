/**
 * 
 */
package com.side.basic.baseServiceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.side.basic.IbaseDao.HibernateEntitryDao;
import com.side.basic.IbaseService.ISideBasicService;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.PageMode;

/**
 * @author gmc
 *
 */
@Service("sideBasicService")
@Transactional
public class SideBasicServiceImpl<T> implements ISideBasicService<T> {

	@Autowired
	private HibernateEntitryDao hibernateEntitryDao;
	
	@Override
	@Transactional
	public void save(T entity) {
		hibernateEntitryDao.save(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		hibernateEntitryDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public void updateAll(List<T> list) {
		hibernateEntitryDao.saveOrUpdateAll(list);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		hibernateEntitryDao.delete(entity);
	}

	@Override
	@Transactional
	public void deleteAll(List<T> list) {
		hibernateEntitryDao.deleteAll(list);
	}

	@Override
	public PageMode<T> findForList(DetachedCriteriaTS<T> criteria, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findForPage(criteria, pageNumber, pageSize);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T find(DetachedCriteriaTS<T> criteria) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.find(criteria);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> find(DetachedCriteriaTS<T> detachedCriteria, int maxResult) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.find(detachedCriteria, maxResult);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> findAll(DetachedCriteriaTS<T> detachedCriteria) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.findAll(detachedCriteria);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return hibernateEntitryDao.get(entityClass, id);
	}

}
