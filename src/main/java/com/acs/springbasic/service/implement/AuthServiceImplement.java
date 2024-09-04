package com.acs.springbasic.service.implement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acs.springbasic.dto.PostUserRequestDto;
import com.acs.springbasic.entity.SampleUserEntity;
import com.acs.springbasic.repository.SampleUserRepository;
import com.acs.springbasic.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final SampleUserRepository sampleUserRepository;

    //# PasswordEncoder 인터페이스
    // : 비밀번호를 안전하고 쉽게 암호화하여 관리할 수 있도록 도움을 주는 인터페이스
    // - 구현체
    // - BCryptPasswordEncoder, SCryptPasswordEncoder, Pbkdf2PasswordEncoder
    // : 3가지 방식이 있기 때문에 내부에서 선택해서 생성해주어야 함
    // - String encode(평문 비밀번호) : 평문 비밀번호를 암호화하여 반환
    // - boolean matches(평문 비밀번호, 암호화된 비밀번호) 
    //   : 평문 비밀번호와 암호화된 비밀번호가 일치하는지 여부를 반환
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String signUp(PostUserRequestDto dto) {
        
        try {

            String userId = dto.getUserId();
            boolean isExistedId = sampleUserRepository.existsById(userId);
            if (isExistedId) return "존재하는 아이디!";

            String telNumber = dto.getTelNumber();
            boolean isExistedTelNumber = sampleUserRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return "존재하는 전화번호!";

            // String password = dto.getPassword();
            // String name = dto.getName();
            // String address = dto.getAddress();
            // : 로직이 너무 길어짐, 아래에 한 번에 받는 로직 있음

            //& 아래 쪽 방법들은 이러한 방법이 있다~ (잘 안 씀)
            // SampleUserEntity userEntity = new SampleUserEntity(userId, password, name, address, telNumber);
            // => AllArgs는 실수할 여지가 있다.

            // SampleUserEntity userEntity = new SampleUserEntity();
            // userEntity.setUserId(userId);
            // userEntity.setPassword(password);
            // => 원래 Entity는 Setter를 가지면 안됨

            // ▼ @Builder 사용법
            // 필드명에 해당하는 값을 직접적으로 넣어주는 방법
            // => 안정적임
            // SampleUserEntity userEntity = 
            //     SampleUserEntity.builder()
            //         .userId(userId)
            //         .password(password)
            //         .name(name)
            //         .address(address)
            //         .telNumber(telNumber)
            //         .build();
            // => 비즈니스 로직이 아님, 사용 안 함
            
            //% 비밀번호 암호화
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            SampleUserEntity userEntity = new SampleUserEntity(dto);
            // : Entity로 가서 직접 생성자를 생성
            sampleUserRepository.save(userEntity);

            return "성공";

        } catch(Exception exception) {
            exception.printStackTrace();
            return "예외 발생!";
        } 

    }
    
}
