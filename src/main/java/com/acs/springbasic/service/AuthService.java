package com.acs.springbasic.service;

import com.acs.springbasic.dto.PostUserRequestDto;

public interface AuthService {
    String signUp(PostUserRequestDto dto);
}
