package com.shgx.caffine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * 设备VO
 *
 * @author guangxush
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_device")
public class Device {
    /**
     * 设备ID
     */
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * 设备名字
     */
    @Column(name = "device_name")
    private String deviceName;
    /**
     * 设备单价
     */
    @Column(name = "price")
    private Float price;
    /**
     * 设备数量
     */
    @Column(name = "count")
    private int count;
}
