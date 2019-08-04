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
	public final static String FIND_SYSTEM_USER_BYSQL = "SELECT a.id userId, a.userName, a.userCode, a.account, a.userStatus, b.roleId, r.roleName FROM side_user a LEFT JOIN side_user_role b ON a.id=b.userId left join side_role r on b.roleId=r.id WHERE 1=1 AND (r.roleName NOT LIKE '%租户%' OR r.roleName NOT LIKE '%房东%' OR b.roleId IS NULL)";

}
