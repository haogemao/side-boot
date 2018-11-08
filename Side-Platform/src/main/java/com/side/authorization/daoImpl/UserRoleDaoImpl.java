/**
 * 
 */
package com.side.authorization.daoImpl;

import org.springframework.stereotype.Component;

import com.side.authorization.IDao.IUserRoleDao;
import com.side.basic.baseDaoImpl.HibernateEntitryDaoImpl;

/**
 * @author gmc
 *
 */
@Component("userRoleDao")
public class UserRoleDaoImpl extends HibernateEntitryDaoImpl implements IUserRoleDao {

}
