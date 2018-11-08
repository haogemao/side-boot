/**
 * 
 */
package com.side.authorization.daoImpl;

import org.springframework.stereotype.Component;

import com.side.authorization.IDao.IAuthorizationDao;
import com.side.basic.baseDaoImpl.HibernateEntitryDaoImpl;

/**
 * @author gmc
 *
 */
@Component("authorizationDao")
public class AuthorizationDaoImpl extends HibernateEntitryDaoImpl implements IAuthorizationDao {
	
	
}
