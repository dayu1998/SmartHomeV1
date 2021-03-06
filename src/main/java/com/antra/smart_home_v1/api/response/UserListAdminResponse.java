package com.antra.smart_home_v1.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListAdminResponse {
    private int page;
    private int size;
    private List<UserVO> users;
}
