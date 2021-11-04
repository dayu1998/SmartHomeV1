package com.antra.smart_home_v1.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HomeListResponse {
    private String user;
    private List<HomeVO> homes;

}
