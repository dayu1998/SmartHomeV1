package com.antra.smart_home_v1.dao;

import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.Home;
import com.antra.smart_home_v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HomeDAO extends JpaRepository<Home,Integer>{
    Home findHomeByAddressAndUser(String address, User user);
}
