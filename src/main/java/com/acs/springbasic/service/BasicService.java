package com.acs.springbasic.service;

import org.springframework.http.ResponseEntity;

//# 서비스 인터페이스

public interface BasicService {
    
    ResponseEntity<String> getService();

}
