package com.side.property.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 功能描述：
 * 物业基础信息
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "side_property_base_info", indexes = {@Index(columnList = "property_id")})
public class SidePropertyBaseInfo implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false, unique = true)
    private Long propertyId;

    /**
     * 层数
     */
    @Column(name = "floor", length = 3)
    private Integer floor;

    /**
     * 房间数
     */
    @Column(name = "room_number", length = 4)
    private Integer roomNumber;

    /**
     * 水费
     */
    @Column(name = "water_rent")
    private Double waterRent;

    /**
     * 电费
     */
    @Column(name = "electricity_fees")
    private Double electricityFees;

    /**
     * 审核状态,0未审核，1审核通过
     */
    @Column(name = "audit_status", length = 1, nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer auditStatus;

    /**
     * 详细地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 房间
     */
    @OneToMany(mappedBy = "sidePropertyBaseInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SidePropertyRoomInfo> sidePropertyRoomInfos;

    /**
     * 创建时间
     */
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;

    /**
     * 创建人
     */
    @Column(name = "create_by", length=32, nullable=false)
    private Long createBy;

    /**
     * 最后更新人
     */
    @Column(name = "last_update_by", length=32, nullable=true)
    private Long lastUpdateBy;
}
