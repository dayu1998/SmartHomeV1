package com.antra.smart_home_v1.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceSerialNumberRequest {
    @NotBlank
    private Integer serialNumber;
}
