package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.dao.DeviceDAO;
import com.antra.smart_home_v1.dao.UserRepository;
import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.Home;
import com.antra.smart_home_v1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDeviceServiceImpl implements UserDeviceService {
    @Autowired
    DeviceDAO deviceDAO;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public Boolean addUserDevices(Device device) {
        return deviceDAO.save(device)!=null;
    }

    @Override
    public List<Device> getAllUserDevices(Integer id) {
        Optional<User> user= userRepository.findById(id);
        return deviceDAO.findDeviceByUser(user.get());
    }
    @Override
    public List<Device> findAll(){
        return deviceDAO.findAll();
    }
    @Override
    public List<Device> getUserDevicesByLocation(String Location, Integer id) {
        Optional<User> user= userRepository.findById(id);
        return deviceDAO.findDeviceByLocationAndUser(Location,user.get());
    }
    @Override
    public Device getDeviceBySerialnumber(Integer SerialNumber){
        return deviceDAO.findDeviceBySerialNumber(SerialNumber);
    }
    @Override
    @Transactional
    public Boolean saveStatusChange(List<Device> devices){
        for(Device i:devices){
            if(deviceDAO.save(i)==null){
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public void remove(Device device){
        deviceDAO.delete(device);
    }

}
