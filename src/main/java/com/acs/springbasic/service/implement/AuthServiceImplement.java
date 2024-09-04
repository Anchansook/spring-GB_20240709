package com.acs.springbasic.service.implement;

import org.springframework.stereotype.Service;

import com.acs.springbasic.dto.PostUserRequestDto;
import com.acs.springbasic.entity.SampleUserEntity;
import com.acs.springbasic.repository.SampleUserRepository;
import com.acs.springbasic.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    @Override
    public String signUp(PostUserRequestDto dto) {

        private final SampleUserRepository sampleUserRepository;
        
        try {

            String userId = dto.getUserId();
            boolean isExistedId = sampleUserRepository.existsById(userId);
            if (isExistedId) return "존재하는 아이디!";

            String telNumber = dto.getTelNumber();
            boolean isExistedTelNumber = sampleUserRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return "존재하는 전화번호!";

            String password = dto.getPassword();
            String name = dto.getName();
            String address = dto.getAddress();
            // SampleUserEntity userEntity = new SampleUserEntity(userId, password, name, address, telNumber);
            
            // SampleUserEntity userEntity = new SampleUserEntity();
            // userEntity.setUserId(userId);
            // userEntity.setPassword(password);
            // => 원래 Entity는 Setter를 가지면 안됨, 그래서 좋지 않은 방법임

        } catch(Exception exception) {
            exception.printStackTrace();
            return "예외 발생!";
        } 

    }
    
}
