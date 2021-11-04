package com.antra.smart_home_v1.controller;

import com.antra.smart_home_v1.api.request.DeviceRegisterRequest;
import com.antra.smart_home_v1.api.request.DeviceStatusUpdateRequest;
import com.antra.smart_home_v1.api.response.DeviceListResponse;
import com.antra.smart_home_v1.api.response.DeviceVO;
import com.antra.smart_home_v1.api.response.UserResponse;
import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.User;
import com.antra.smart_home_v1.exception.DeviceNotFoundException;
import com.antra.smart_home_v1.exception.UnauthorizedChangeException;
import com.antra.smart_home_v1.service.AdminUserService;
import com.antra.smart_home_v1.service.UserDeviceService;
import com.antra.smart_home_v1.utility.CurrentUserIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@Secured({"ROLE_USER"})
@Slf4j
@RequestMapping("/api/devices")
public class UserDeviceController {
    @Autowired
    UserDeviceService userDeviceService;
    @Autowired
    AdminUserService userService;

    @GetMapping
    public DeviceListResponse getAllUserDevice() {


        Optional<User> entity = userService.getUserById(CurrentUserIdGenerator.generate());
        User user = entity.get();
        DeviceListResponse response = DeviceListResponse.builder()
                .message(user.getName())
                .devices(user
                        .getDevices()
                        .stream()
                        .map(t -> new DeviceVO(t))
                        .collect(Collectors.toList()))
                .build();

        return response;
    }

    @PostMapping
    public UserResponse registerUserDevice(@Valid @RequestBody DeviceRegisterRequest request) {

        Optional<User> entity = userService.getUserById(CurrentUserIdGenerator.generate());
        User user = entity.get();
        Device device = Device.builder()
                .deviceName(request.getDeviceName())
                .category(request.getCategory())
                .user(user).
                location(request.getLocation())
                .make(request.getMake())
                .serialNumber(request.getSerialnumber()).
                status(request.getStatus())
                .home(null)
                .build();

        user.getDevices().add(device);


        return new UserResponse(userService.modify(user), "complete");
    }
    @DeleteMapping
    public UserResponse removeDevice(@RequestParam Integer SerialNumber){
        Device device = userDeviceService.getDeviceBySerialnumber(SerialNumber);
        userDeviceService.remove(device);
        return new UserResponse(true,"completed");
    }
    @PostMapping("/status")
    public UserResponse changeDeviceStatus(@RequestBody List<DeviceStatusUpdateRequest> request) {
        Integer userId = CurrentUserIdGenerator.generate();
        List<Device> deviceList = new ArrayList<>();
        int k = 0;
        for (DeviceStatusUpdateRequest i : request) {
            Device device = userDeviceService.getDeviceBySerialnumber(i.getSerialnumber());
            if (device == null) {
                throw new DeviceNotFoundException();
            }
            if (!device.getUser().getId().equals(userId)) {
                throw new UnauthorizedChangeException();
            }
            device.setStatus(i.getStatus());

            deviceList.add(device);
            k++;
        }

        return new UserResponse(userDeviceService.saveStatusChange(deviceList), "complete");
    }
    @GetMapping("/home/location")
    public DeviceListResponse getHomeDevicesByLocation(@RequestParam String address ,@RequestParam String location){
        Integer userId = CurrentUserIdGenerator.generate();
        List<Device> deviceList= userDeviceService.getUserDevicesByLocation(location,userId);
        List<DeviceVO> voList = deviceList.stream().filter(t -> t.getHome().getAddress().equals(address)).map(t->new DeviceVO(t)).collect(Collectors.toList());
        return DeviceListResponse.builder().devices(voList).message(address).build();
    }


}
