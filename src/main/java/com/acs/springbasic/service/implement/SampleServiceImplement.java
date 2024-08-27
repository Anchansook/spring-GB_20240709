package com.acs.springbasic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acs.springbasic.dto.PostSample1RequestDto;
import com.acs.springbasic.service.SampleService;

@Service
public class SampleServiceImplement implements SampleService {

    @Override
    public ResponseEntity<String> postSample1(PostSample1RequestDto dto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");

    }
    
}
