package com.side.property.serviceImpl;

import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.PageMode;
import com.side.property.IDao.ISidePropertyRoomInfoDao;
import com.side.property.IService.ISidePropertyRoomInfoService;
import com.side.property.pojo.SidePropertyBaseInfo;
import com.side.property.pojo.SidePropertyRoomApartment;
import com.side.property.pojo.SidePropertyRoomInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
@Service("sidePropertyRoomInfoService")
public class SidePropertyRoomInfoServiceImpl extends SideBasicServiceImpl<SidePropertyRoomInfo> implements ISidePropertyRoomInfoService {

    @Autowired
    private ISidePropertyRoomInfoDao sidePropertyRoomInfoDao;

    /**
     * 根据出租状态查看
     *
     * @param rentalStatus
     * @return
     */
    @Override
    public List<SidePropertyRoomInfo> findByRentalStatus(Integer rentalStatus) {
        return null;
    }

    /**
     * 根据物业查看
     *
     * @param sidePropertyBaseInfo
     */
    @Override
    public List<SidePropertyRoomInfo> findBySidePropertyBaseInfo(SidePropertyBaseInfo sidePropertyBaseInfo) {
        return null;
    }

    /**
     * 根据户型查看
     *
     * @param sidePropertyRoomApartment
     */
    @Override
    public PageMode<SidePropertyRoomInfo> findBySidePropertyRoomApartment(SidePropertyRoomApartment sidePropertyRoomApartment, Integer pageIndex, Integer pageSize) {
        PageMode<SidePropertyRoomInfo> sidePropertyRoomInfoPageMode = null;
        DetachedCriteriaTS<SidePropertyRoomInfo> criteria = new DetachedCriteriaTS<SidePropertyRoomInfo>(SidePropertyRoomInfo.class);
        if (sidePropertyRoomApartment != null) {
            criteria.add(Restrictions.eq("sidePropertyRoomApartment", sidePropertyRoomApartment));
            sidePropertyRoomInfoPageMode = sidePropertyRoomInfoDao.findForPage(criteria, pageIndex, pageSize);
        }
        return sidePropertyRoomInfoPageMode;
    }
}
