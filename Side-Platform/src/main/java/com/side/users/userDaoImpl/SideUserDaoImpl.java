/**
 * 
 */
package com.side.users.userDaoImpl;

import org.springframework.stereotype.Component;

import com.side.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.side.users.IUserDao.ISideUserDao;

/**
 * @author gmc
 *
 */
@Component("sideUserDao")
public class SideUserDaoImpl extends HibernateEntitryDaoImpl implements ISideUserDao {

}
