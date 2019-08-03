/**
 * 
 */
package com.side.users.sql;

/**
 * @author gmc
 * @see 用户SQL集合
 */
public interface UserSQL {
	
	/**
	 * 系统用户查询语句
	 */
	public final static String FIND_SYSTEM_USER_BYSQL = "SELECT a.*, b.roleId FROM side_user a LEFT JOIN side_user_role b ON a.id=b.userId WHERE b.roleId not in (?)";

}
