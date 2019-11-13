package com.side.property.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 功能描述：
 * 房间照片
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
@Table(name = "side_property_room_pic", indexes = {@Index(columnList = "pic_id")})
public class SidePropertyRoomPic implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pic_id", nullable = false, unique = true)
    private Long picId;

    /**
     * 图片地址
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 备注
     */
    @Column(name = "pic_remark")
    private String picRemark;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "room_id")
    private SidePropertyRoomInfo sidePropertyRoomInfo;
}
