package com.side.property.IService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.basic.common.utils.PageMode;
import com.side.property.pojo.SidePropertyBaseInfo;

import java.util.List;

/**
 * 功能描述：
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
public interface ISidePropertyBaseInfoService extends ISideBasicService<SidePropertyBaseInfo> {

    /**
     * 根据审核状态查看
     * @param auditStatus
     * @return
     */
    public PageMode<SidePropertyBaseInfo> findByAuditStatus(Integer auditStatus, Integer pageIndex, Integer pageSize);

    /**
     * 根据地址模糊匹配
     */
    public PageMode<SidePropertyBaseInfo> findByAddress(String address, Integer pageIndex, Integer pageSize);
}
