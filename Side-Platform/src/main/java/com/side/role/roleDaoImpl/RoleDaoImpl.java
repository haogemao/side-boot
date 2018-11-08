/**
 * 
 */
package com.side.role.roleDaoImpl;

import org.springframework.stereotype.Component;

import com.side.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.side.role.IRoleDao.IRoleDao;

/**
 * @author gmc
 *
 */
@Component("roleDao")
public class RoleDaoImpl extends HibernateEntitryDaoImpl implements IRoleDao {

}
