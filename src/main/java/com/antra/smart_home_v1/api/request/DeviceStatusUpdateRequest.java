package com.antra.smart_home_v1.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceStatusUpdateRequest {
    @NotBlank
    private Boolean status;
    @NotBlank
    private Integer serialnumber;

}
