package com.acs.springbasic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acs.springbasic.service.BasicService;
// import com.acs.springbasic.service.implement.BasicServiceImplement;

//# 응답 처리만 해주는 컨트롤러 레이어

@RestController
@RequestMapping("/service")
public class ServiceController {

    private BasicService basicService;

    @GetMapping("")
    public ResponseEntity<String> getService() {
        return basicService.getService();
    };

}
