/**
 * 
 */
package com.side.users.IService;

import java.util.List;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.users.dto.SideUserDto;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
public interface ISideUserService extends ISideBasicService<SideUser>{

	public SideUser findSideUserByCode(String code);
	
	public List<SideUser> findAllUser(SideUserDto dto) throws Exception;
	
}
