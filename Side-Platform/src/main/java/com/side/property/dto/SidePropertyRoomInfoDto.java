package com.side.property.dto;

import com.side.property.pojo.SidePropertyBaseInfo;
import com.side.property.pojo.SidePropertyRoomApartment;
import com.side.property.pojo.SidePropertyRoomPic;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 功能描述：
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
public class SidePropertyRoomInfoDto implements Serializable {

    /**
     * 主键
     */
    private Long roomId;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 面积
     */
    private Integer roomMeasure;

    /**
     * 朝向
     */
    private String roomOrientation;

    /**
     * 租金
     */
    private Integer roomRent;

    /**
     * 押金
     */
    private Integer roomDeposit;

    /**
     * 出租状态,0未出租，1已出租
     */
    private Integer rentalStatus;

    /**
     * 创建时间
     */
    private Timestamp createDate;

    /**
     * 最后更新时间
     */
//    private Timestamp lastUpdateDate;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 最后更新人
     */
//    private Long lastUpdateBy;

    /**
     * 户型
     */
    private Long apartmentId;

    /**
     * 物业
     */
    private Long propertyId;
}
