package com.antra.smart_home_v1.controller;

import com.antra.smart_home_v1.api.request.DeviceSerialNumberRequest;
import com.antra.smart_home_v1.api.request.HomeRegisterRequest;
import com.antra.smart_home_v1.api.response.*;
import com.antra.smart_home_v1.config.security.UserPrincipal;
import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.Home;
import com.antra.smart_home_v1.domain.User;
import com.antra.smart_home_v1.exception.UnauthorizedChangeException;
import com.antra.smart_home_v1.service.AdminUserService;
import com.antra.smart_home_v1.service.UserDeviceService;
import com.antra.smart_home_v1.service.UserHomeService;
import com.antra.smart_home_v1.utility.CurrentUserIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@Secured({"ROLE_USER"})
@Slf4j
@RequestMapping("/api/home")
public class UserHomeController {
    @Autowired
    AdminUserService userService;
    @Autowired
    UserDeviceService deviceService;
    @Autowired
    UserHomeService homeService;
    @GetMapping
    public HomeListResponse getUserHomes(){

        Optional<User> entity = userService.getUserById(CurrentUserIdGenerator.generate());
        User user = entity.get();

        HomeListResponse response = HomeListResponse.builder()
                .user(user.getName())
                .homes(user
                        .getHomes()
                        .stream()
                        .map(t -> new HomeVO(t))
                        .collect(Collectors.toList()))
                .build();

        return response;
    }

    @PostMapping
    public UserResponse registerUserHome(@RequestParam String address, @RequestBody List<DeviceSerialNumberRequest> request){

        Integer userId = CurrentUserIdGenerator.generate();
        Optional<User> entity = userService.getUserById(userId);
        User user = entity.get();
        List<Device> homeDevices = null;
        if(request!=null){
            homeDevices=new ArrayList<>();
            for(DeviceSerialNumberRequest i : request){
                Integer serialNumber = i.getSerialNumber();
                Device device = deviceService.getDeviceBySerialnumber(serialNumber);
                if(device!=null){
                    if(device.getUser().getId()!=userId){
                        throw new UnauthorizedChangeException();
                    }
                    homeDevices.add(device);
                }
            }
        }

        Home home = Home.builder().user(user).address(address).devices(homeDevices).build();
        user.getHomes().add(home);
        homeService.modify(home);

        return new UserResponse(userService.modify(user),"completed");
    }
    @DeleteMapping
    public UserResponse deleteHome(@RequestParam String address){
        Integer userId = CurrentUserIdGenerator.generate();
        Optional<User> user = userService.getUserById(userId);
        Home home = homeService.getHomeByAddressAndUser(address,user.get());
        homeService.remove(home);

        return new UserResponse(true,"completed");
    }

    @PostMapping("/registerDevice")
    public UserResponse registerDeviceToHome(@RequestParam String address, @RequestBody List<DeviceSerialNumberRequest> request){
        Integer userId = CurrentUserIdGenerator.generate();
        Optional<User> user = userService.getUserById(userId);
        Home home = homeService.getHomeByAddressAndUser(address,user.get());

        List<Device> homeDevices = home.getDevices();
        if(request!=null){
            if(homeDevices==null){
                homeDevices=new ArrayList<>();
            }
            for(DeviceSerialNumberRequest i : request){
                Integer serialNumber = i.getSerialNumber();
                Device device = deviceService.getDeviceBySerialnumber(serialNumber);
                if(device!=null){
                    if(device.getUser().getId()!=userId){
                        throw new UnauthorizedChangeException();
                    }
                    homeDevices.add(device);
                }
            }
        }

        return new UserResponse(homeService.modify(home),"completed");
    }
}
