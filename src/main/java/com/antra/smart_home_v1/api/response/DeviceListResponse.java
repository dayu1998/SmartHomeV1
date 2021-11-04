package com.antra.smart_home_v1.api.response;

import com.antra.smart_home_v1.domain.Device;
import com.antra.smart_home_v1.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class DeviceListResponse{

    private String message;

    private List<DeviceVO> devices;

}
