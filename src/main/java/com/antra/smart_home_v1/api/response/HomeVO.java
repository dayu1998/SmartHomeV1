package com.antra.smart_home_v1.api.response;

import com.antra.smart_home_v1.domain.Home;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HomeVO {
    @JsonIgnore
    private Home home;

    public HomeVO(Home home) {
        this.home = home;
    }

    @JsonProperty
    public Integer getHomeId(){
        return home.getId();
    }
    public String getHomeAddress(){
        return home.getAddress();
    }
    public Integer getHomeDeviceNumber(){
        return home.getDevices().size();
    }
}
