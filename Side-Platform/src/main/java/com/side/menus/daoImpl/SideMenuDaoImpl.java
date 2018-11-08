/**
 * 
 */
package com.side.menus.daoImpl;

import org.springframework.stereotype.Component;

import com.side.basic.baseDaoImpl.HibernateEntitryDaoImpl;
import com.side.menus.IDao.ISideMenuDao;

/**
 * @author gmc
 *
 */
@Component("sideMenuDao")
public class SideMenuDaoImpl extends HibernateEntitryDaoImpl implements ISideMenuDao {

}
