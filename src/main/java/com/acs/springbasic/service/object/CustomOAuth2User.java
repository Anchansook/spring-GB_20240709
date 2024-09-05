package com.acs.springbasic.service.object;

import java.util.Collections;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

//# OAuth2 인증 성공 시 인증 서버로부터 클라이언트의 정보를 받아서 저장할 객체
// - 반드시 OAuth2User 인터페이스를 구현해야 함
public class CustomOAuth2User implements OAuth2User {

    private String name;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    // 생성자 : 따로 추가 설정할거라서 따로 만듦
    public CustomOAuth2User(String name, Map<String, Object> attributes) {
        this.name = name;
        this.attributes = attributes;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 다른 받아오는 속성들
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    // 권한 리스트
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
