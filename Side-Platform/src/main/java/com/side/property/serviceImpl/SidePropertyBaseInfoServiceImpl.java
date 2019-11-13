package com.side.property.serviceImpl;

import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.PageMode;
import com.side.property.IDao.ISidePropertyBaseInfoDao;
import com.side.property.IService.ISidePropertyBaseInfoService;
import com.side.property.pojo.SidePropertyBaseInfo;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
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
@Service("sidePropertyBaseInfoService")
public class SidePropertyBaseInfoServiceImpl extends SideBasicServiceImpl<SidePropertyBaseInfo> implements ISidePropertyBaseInfoService {

    @Autowired
    private ISidePropertyBaseInfoDao sidePropertyBaseInfoDao;

    /**
     * 根据审核状态查看
     *
     * @param auditStatus
     * @return
     */
    @Override
    public PageMode<SidePropertyBaseInfo> findByAuditStatus(Integer auditStatus, Integer pageIndex, Integer pageSize) {
        PageMode<SidePropertyBaseInfo> sidePropertyBaseInfos = null;
        DetachedCriteriaTS<SidePropertyBaseInfo> criteria = new DetachedCriteriaTS<SidePropertyBaseInfo>(SidePropertyBaseInfo.class);
        if (auditStatus != null) {
            criteria.add(Restrictions.eq("auditStatus", auditStatus));
            sidePropertyBaseInfos = sidePropertyBaseInfoDao.findForPage(criteria, pageIndex, pageSize);
        }
        return sidePropertyBaseInfos;
    }

    /**
     * 根据地址模糊匹配
     *
     * @param address
     */
    @Override
    public PageMode<SidePropertyBaseInfo> findByAddress(String address, Integer pageIndex, Integer pageSize) {
        PageMode<SidePropertyBaseInfo> sidePropertyBaseInfos = null;
        DetachedCriteriaTS<SidePropertyBaseInfo> criteria = new DetachedCriteriaTS<SidePropertyBaseInfo>(SidePropertyBaseInfo.class);
        if (address != null) {
            criteria.add(Restrictions.ilike("address", address, MatchMode.ANYWHERE));
            sidePropertyBaseInfos = sidePropertyBaseInfoDao.findForPage(criteria, pageIndex, pageSize);
        }
        return sidePropertyBaseInfos;
    }
}
