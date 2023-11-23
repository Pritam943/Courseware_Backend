package com.Courseware.cutm.model;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String userName;
    private String password;
}
