package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.Home;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDeviceService {

    Boolean addUserDevices(Device device);
    List<Device> getAllUserDevices(Integer id);
    List<Device> findAll();
    List<Device> getUserDevicesByLocation(String Location,Integer id);
    Device getDeviceBySerialnumber(Integer SerialNumber);
    Boolean saveStatusChange(List<Device> devices);
    void remove(Device device);
}
