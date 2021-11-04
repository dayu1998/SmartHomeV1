package com.antra.smart_home_v1.dao;

import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceDAO extends JpaRepository<Device, Integer> {

    List<Device> findDeviceByUser(User user);
    @Modifying

    List<Device> findDeviceByLocationAndUser(String Location, User User);

    List<Device> findDeviceByCategoryAndUser(String category, User User);
    Device findDeviceBySerialNumber(Integer SerialNumber);

}
