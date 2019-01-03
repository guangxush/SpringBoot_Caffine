package com.shgx.caffine.controller;


import com.shgx.caffine.model.Device;
import com.shgx.caffine.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Device findDeviceById(@PathVariable("id") Long id) {
        Device device = deviceService.fetchDeviceById(id);
        if (device!=null) {
            return device;
        } else {
            return null;
        }
    }
}
