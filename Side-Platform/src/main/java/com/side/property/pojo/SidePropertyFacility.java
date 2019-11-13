package com.side.property.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：
 * 房屋设施
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
@Table(name = "side_property_facility", indexes = {@Index(columnList = "facility_id")})
public class SidePropertyFacility implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id", unique = true, nullable = false)
    private Long facilityId;

    /**
     * 设备名称
     */
    @Column(name = "facility_name", length = 100, nullable = false)
    private String facilityName;

    /**
     * 备注
     */
    @Column(name = "facility_remark")
    private String facilityRemark;

    /**
     * 状态:0未启用，1启用
     */
    @Column(name = "facility_status", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer facilityStatus;

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
