package com.side.property.IService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.basic.common.utils.PageMode;
import com.side.property.pojo.SidePropertyBaseInfo;
import com.side.property.pojo.SidePropertyRoomApartment;
import com.side.property.pojo.SidePropertyRoomInfo;

import java.util.List;

/**
 * 功能描述：
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
public interface ISidePropertyRoomInfoService extends ISideBasicService<SidePropertyRoomInfo> {

    /**
     * 根据出租状态查看
     * @param rentalStatus
     * @return
     */
    public List<SidePropertyRoomInfo> findByRentalStatus(Integer rentalStatus);

    /**
     * 根据物业查看
     */
    public List<SidePropertyRoomInfo> findBySidePropertyBaseInfo(SidePropertyBaseInfo sidePropertyBaseInfo);

    /**
     * 根据户型查看
     */
    public PageMode<SidePropertyRoomInfo> findBySidePropertyRoomApartment(SidePropertyRoomApartment sidePropertyRoomApartment, Integer pageIndex, Integer pageSize);
}
