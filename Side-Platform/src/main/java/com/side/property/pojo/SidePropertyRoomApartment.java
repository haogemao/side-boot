package com.side.property.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 功能描述：
 * 户型
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
@Table(name = "side_property_room_apartment", indexes = {@Index(columnList = "apartment_id")})
public class SidePropertyRoomApartment {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id", nullable = false, unique = true)
    private Long apartmentId;

    /**
     * 名称
     */
    @Column(name = "apartment_name")
    private String apartmentName;

    /**
     * 房间
     */
    @OneToMany(mappedBy = "sidePropertyRoomApartment", fetch = FetchType.LAZY)
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
