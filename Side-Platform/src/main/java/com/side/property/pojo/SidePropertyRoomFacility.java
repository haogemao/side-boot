package com.side.property.pojo;

import javax.persistence.*;

/**
 * 功能描述：
 * 房间和房屋设施
 *
 * @author sie
 * @version 0.1
 * 修改记录
 * 修改后的版本  修改人  修改日期   修改内容
 */
@Entity
@Table(name = "side_property_room_facility")
public class SidePropertyRoomFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_facility_id", nullable = false, unique = true)
    private Long roomFacilityId;

    /**
     * 房间Id
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", nullable = false)
    private SidePropertyRoomInfo roomId;


    /**
     * 设施Id
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id", nullable = false)
    private SidePropertyRoomFacility facilityId;
}
