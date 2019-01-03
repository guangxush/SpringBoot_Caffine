package com.shgx.caffine.service;

import com.shgx.caffine.model.Device;

import java.util.List;
/**
 * 获取设备信息
 *
 * @author guangxush
 */
public interface DeviceService {

    Device findDeviceById(Long id);

    Device fetchDeviceById(Long id);

    List<Device> fetchDecivesByIds(List<Long> ids);
}
