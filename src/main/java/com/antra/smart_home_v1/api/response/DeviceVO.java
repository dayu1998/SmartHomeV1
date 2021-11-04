package com.antra.smart_home_v1.api.response;

import com.antra.smart_home_v1.domain.Device;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class DeviceVO {
    @JsonIgnore
    private Device device;

    public DeviceVO(Device device) {
        this.device = device;
    }
    @JsonProperty
    public String getDeviceName(){
        return device.getDeviceName();
    }
    @JsonProperty
    public Integer getSerialnumber(){
        return device.getSerialNumber();
    }
    @JsonProperty
    public Boolean getStatus(){
        return device.getStatus();
    }
    @JsonProperty
    public String getCategory(){
        return device.getCategory();
    }
    @JsonProperty
    public String getMake(){
        return device.getMake();
    }
    @JsonProperty
    public String getLocation(){
        return device.getLocation();
    }


}
