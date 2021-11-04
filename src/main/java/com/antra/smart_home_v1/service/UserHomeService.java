package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.domain.Home;
import com.antra.smart_home_v1.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserHomeService {
    List<Home> getAllRegisteredHome();

    Home getHomeByAddressAndUser(String address, User User);
    Boolean modify(Home home);
    void remove(Home home);

}
