package com.shgx.caffine.repository;

import com.shgx.caffine.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * 设备数据库操作
 *
 * @author guangxush
 */
@Repository
public interface DeviceRepo extends JpaRepository<Device, Long> {

}
