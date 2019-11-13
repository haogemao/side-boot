package com.side.property.dto;

import com.side.property.pojo.SidePropertyRoomInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Getter
@Setter
public class SidePropertyBaseInfoDto implements Serializable {

    private Long propertyId;

    /**
     * 层数
     */
    private Integer floor;

    /**
     * 房间数
     */
    private Integer roomNumber;

    /**
     * 水费
     */
    private Double waterRent;

    /**
     * 电费
     */
    private Double electricityFees;

    /**
     * 审核状态,0未审核，1审核通过
     */
    private Integer auditStatus;

    private String address;

    private Double longitude;

    private Double latitude;

    private Timestamp createDate;

    private Long createBy;

    public SidePropertyBaseInfoDto() {
    }

    public SidePropertyBaseInfoDto(Long propertyId, Integer floor, Integer roomNumber, Double waterRent, Double electricityFees, Integer auditStatus, String address, Double longitude, Double latitude, Timestamp createDate, Long createBy) {
        this.propertyId = propertyId;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.waterRent = waterRent;
        this.electricityFees = electricityFees;
        this.auditStatus = auditStatus;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createDate = createDate;
        this.createBy = createBy;
    }
}
