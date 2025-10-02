package com.back2.Instagram.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 로그인 성공 -> 토큰 생성
    public String generateToken(String username) {
        Date now = new Date();
        long validityTime = 1000 * 60 * 60;
        Date expiryDate = new Date(now.getTime() + validityTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    // 요청 시마다 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            // key로 토큰을 파싱하여, 만료 여부, 변조 여부 등을 한 번에 검증합니다.
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            // 서명이 유효하지 않거나 잘못된 형식
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // 만료된 토큰
            System.out.println("Expired JWT token: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 토큰 형식
            System.out.println("Unsupported JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // JWT 클레임 문자열이 비어있거나 잘못됨
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

    // 토큰에서 유저 정보 찾아서 확인
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}