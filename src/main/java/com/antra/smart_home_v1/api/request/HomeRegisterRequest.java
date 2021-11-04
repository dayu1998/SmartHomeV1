package com.antra.smart_home_v1.api.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class HomeRegisterRequest {
    @NotBlank
    @NotNull
    private String address;
    @Nullable
    private List<DeviceSerialNumberRequest> deviceSerialNumbers;
}
