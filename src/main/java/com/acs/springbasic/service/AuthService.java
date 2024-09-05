package com.acs.springbasic.service;

import com.acs.springbasic.dto.PostUserRequestDto;
import com.acs.springbasic.dto.SignInRequestDto;

public interface AuthService {
    String signUp(PostUserRequestDto dto);
    String signIn(SignInRequestDto dto);
}