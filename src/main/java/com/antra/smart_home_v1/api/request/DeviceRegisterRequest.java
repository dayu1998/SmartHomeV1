package com.antra.smart_home_v1.api.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DeviceRegisterRequest {
    @NotBlank
    @NotNull
    private String deviceName;
    @NotBlank
    @NotNull
    private Integer serialnumber;

    @NotBlank
    private String make;

    @Nullable
    private Boolean status = false;
    @NotBlank
    private String category;
    @Nullable
    private String location;
    @Nullable
    private Integer home;
}
