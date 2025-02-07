package com.example.getrand_userservice.serivce;

import com.example.getrand_userservice.Entity.UserEntity;
import com.example.getrand_userservice.Repository.UsersRepository;
import com.example.getrand_userservice.dto.UserRequestDTO;
import com.example.getrand_userservice.dto.UserResponseDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UsersRepository usersRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    // 회원가입
    public void registerUser(UserRequestDTO userRequestDTO) {
        // 중복 사용자 확인
        if (usersRepository.existsByUserId(userRequestDTO.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 ID입니다.");
        }

        // 비밀번호 암호화 후 저장
        UserEntity user = new UserEntity();
        user.setUserId(userRequestDTO.getUserId()); // userId 설정
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword())); // 암호화된 비밀번호 설정
        usersRepository.save(user);// 저장
    }


    // 로그인
    public UserResponseDTO loginUser(UserRequestDTO userRequestDTO) {
        // 사용자 조회
        UserEntity user = usersRepository.findByUserId(userRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 ID 또는 비밀번호가 잘못되었습니다."));

        // 비밀번호 검증
//        if (!bCryptPasswordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("사용자 ID 또는 비밀번호가 잘못되었습니다.");
//        }

        // 응답 DTO 생성 및 반환
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(user.getUserId());
        return responseDTO;
    }
}


