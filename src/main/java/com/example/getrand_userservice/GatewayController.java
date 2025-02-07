package com.example.getrand_userservice;

import com.example.getrand_userservice.dto.UserRequestDTO;
import com.example.getrand_userservice.dto.UserResponseDTO;
import com.example.getrand_userservice.serivce.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userservice")
@RequiredArgsConstructor
public class GatewayController {
    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("userId") String userid,
            @RequestParam("password") String password) {

        userService.registerUser(new UserRequestDTO(userid, password));
        // 요청 처리 로직
        return "회원가입이 성공적으로 완료되었습니다";
    }

//    @GetMapping("/login")
//    public ResponseEntity<?> loginUser(
//            @RequestParam("userId") String userId,
//            @RequestParam("password") String password) {
//        UserRequestDTO requestDTO = new UserRequestDTO();
//        requestDTO.setUserId(userId);
//        requestDTO.setPassword(password);
//
//        try {
//            UserResponseDTO response = userService.loginUser(requestDTO);
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생: " + e.getMessage());
//        }
//    }

    @GetMapping("/login")
    public String login() {
//        UserResponseDTO responseDTO = userService.loginUser(user);
//        if(responseDTO != null) {// 인증이 성공
//            String mytoken = Jwts.builder()
//                    // 사용자정의클레임
//                    .setSubject(responseDTO.getUsername())
//                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("jwt.token-valid-in-millisecond"))))
//                    .signWith(SignatureAlgorithm.HS512,environment.getProperty("jwt.secret"))
//                    .compact();//위의 정보를 이용
//            // response의 헤더에 셋팅
//            response.setHeader("Authorization", mytoken);
//            response.setHeader("userId", responseDTO.getUsername());
//        }
        return "ok";
    }
}
