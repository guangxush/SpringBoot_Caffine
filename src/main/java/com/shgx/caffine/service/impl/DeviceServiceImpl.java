package com.shgx.caffine.service.impl;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.shgx.caffine.model.Device;
import com.shgx.caffine.repository.DeviceRepo;
import com.shgx.caffine.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取设备信息
 *
 * @author guangxush
 */
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;

    public static final String DEVICE_CACHE = "deviceCache";

    @Autowired
    @Qualifier(DEVICE_CACHE)
    private Caffeine<Object, Object> caffeineBuilder;

    private LoadingCache<Long, Device> cache;

    @PostConstruct
    public void initCache() {
        cache = caffeineBuilder.build(new CacheLoader<Long, Device>() {
            @Override
            public Device load(Long key) throws Exception {
                log.info("fail to hit cache for key={}, try to find it via RPC.", key);
                List<Device> list = findAllDeviceById(Lists.newArrayList(key));
                if (list.isEmpty()) {
                    return null;
                }
                return list.get(0);
            }

            @Override
            public Map<Long, Device> loadAll(Iterable<? extends Long> keys) throws Exception {
                log.info("fail to hit cache for keys={}, try to find them via RPC.", keys.toString());
                List<Long> ids = new ArrayList<>();
                keys.forEach(ids::add);
                List<Device> list = findAllDeviceById(ids);
                return list.stream().collect(Collectors.toMap(Device::getId, e -> e));
            }
        });
    }

    @Override
    public Device findDeviceById(Long id) {
        Optional<Device> deviceOption = deviceRepo.findById(id);
        return deviceOption.orElse(null);
    }

    private List<Device> findAllDeviceById(List<Long> list) {
        Optional<List<Device>> deviceList = Optional.ofNullable(deviceRepo.findAllById(list));
        return deviceList.orElse(null);
    }

    @Override
    public Device fetchDeviceById(Long id) {
        if (id == null) {
            log.error("the id is null");
            throw new RuntimeException("the id is null");
        }
        Device vo = cache.get(id);
        //设置默认值
        if (vo == null) {
            vo = new Device();
            vo.setId(id);
            vo.setDeviceName("computer");
            vo.setPrice(1200.0F);
        }
        return vo;
    }

    @Override
    public List<Device> fetchDecivesByIds(List<Long> ids) {
        Map<Long, Device> vos = null;
        try {
            vos = cache.getAll(ids);
        } catch (Exception e) {
            log.error("fetch userPassport from cache error, ids={}", ids.toString(), e);
        }
        return vos.entrySet().stream().map(Map.Entry::getValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
