package com.acs.springbasic.filter;

import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.acs.springbasic.provider.JwtProvider;

import jakarta.el.ELException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//# filter : 
// - 서버로직과 서블릿 사이에서 http request에 대한 사전 검사 작업을 수행하는 영역
// - filter에서 걸러진 request는 서블릿까지 도달하지 못하고 reject 됨

@RequiredArgsConstructor
// OncePerRequestFilter라는 추상클래스를 확장 구현하여 filter 클래스로 생성
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            // 1. request 객체에서 token 가져오기
            String token = parseBearerToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2. token 검증
            String subject = jwtProvider.validate(token);
            if (subject == null) {
                filterChain.doFilter(request, response);
                return;
            }

        } catch (Exception exception) {

            exception.printStackTrace();

        }

    }

    // 1. request 객체 header 중 'Authorization' 필드의 값을 가져옴
    // 2. 'Authorization' 필드 값이 Bearer 형식인지 확인
    // 3. 'Authorization' 필드 값에서 토큰 추출 (앞에 붙는 Bearer 잘라내기)
    private String parseBearerToken(HttpServletRequest request) {

        // 1. request 객체 header 중 'Authorization' 필드의 값을 가져옴
        String authorization = request.getHeader("Authorization");

        // 2. 'Authorization' 필드 값이 Bearer 형식인지 확인
        // "Authorization" 필드 값의 존재 여부
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;
        // 문자열이 "Bearer "로 시작하는지 여부 판별
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        // 3. 'Authorization' 필드 값에서 토큰 추출 (앞에 붙는 'Bearer ' 잘라내기)
        String token = authorization.substring(7);

        return token;

    };
    
}
