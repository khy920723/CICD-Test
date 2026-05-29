package com.example.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test", description = "로컬 테스트용 API")
public class TestController {

    @GetMapping("/public")
    @Operation(summary = "공개 엔드포인트", description = "JWT 토큰 없이 접근 가능")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        return ResponseEntity.ok(Map.of("message", "인증 없이 접근 성공"));
    }

    @GetMapping("/secured")
    @Operation(summary = "인증 필요 엔드포인트", description = "JWT 토큰 필요. Swagger UI 상단 Authorize 버튼에서 토큰 입력 후 호출")
    public ResponseEntity<Map<String, Object>> securedEndpoint(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "message", "인증된 사용자 접근 성공",
                "username", authentication.getName()
        ));
    }
}
