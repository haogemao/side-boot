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
 * 房间信息
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
@Table(name = "side_property_room_info", indexes = {@Index(columnList = "room_id")})
public class SidePropertyRoomInfo implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, unique = true)
    private Long roomId;

    /**
     * 楼层
     */
    @Column(name = "floor", length = 3)
    private Integer floor;

    /**
     * 房间号
     */
    @Column(name = "room_number", length = 100)
    private String roomNumber;

    /**
     * 面积
     */
    @Column(name = "room_measure", nullable = false)
    private Integer roomMeasure;

    /**
     * 朝向
     */
    @Column(name = "room_orientation")
    private String roomOrientation;

    /**
     * 租金
     */
    @Column(name = "room_rent")
    private Integer roomRent;

    /**
     * 押金
     */
    @Column(name = "room_deposit")
    private Integer roomDeposit;

    /**
     * 出租状态,0未出租，1已出租
     */
    @Column(name = "rental_status", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer rentalStatus;

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

    /**
     * 房屋配套
     */
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id")
//    private List<SidePropertyFacility> sidePropertyRoomFacilities;

    /**
     * 户型
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private SidePropertyRoomApartment sidePropertyRoomApartment;

    /**
     * 物业
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    private SidePropertyBaseInfo sidePropertyBaseInfo;


    /**
     * 图片
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sidePropertyRoomInfo", cascade = CascadeType.ALL)
    private List<SidePropertyRoomPic> sidePropertyRoomPics;
}
