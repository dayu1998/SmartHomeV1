package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.dao.HomeDAO;
import com.antra.smart_home_v1.domain.Home;
import com.antra.smart_home_v1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserHomeServiceImpl implements UserHomeService{
    @Autowired
    HomeDAO homeDAO;
    @Override
    public List<Home> getAllRegisteredHome() {
        return homeDAO.findAll();
    }

    @Override
    public Home getHomeByAddressAndUser(String address, User User) {
        return homeDAO.findHomeByAddressAndUser(address,User);
    }
    @Override
    @Transactional
    public Boolean modify(Home home){
        return homeDAO.save(home)!=null;
    }
    @Override
    @Transactional
    public void remove(Home home){
        homeDAO.delete(home);
    }
}
