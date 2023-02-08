package shop.mtcoding.blog2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog2.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog2.ex.CustomException;
import shop.mtcoding.blog2.model.User;
import shop.mtcoding.blog2.model.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void join(JoinReqDto joinReqDto) {
        checkAlreadyHasSameUsername(joinReqDto.getUsername());
        try {
            userRepository.insert(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getEmail());
        } catch (Exception e) {
            throw new CustomException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void checkAlreadyHasSameUsername(String username) {
        User principal = userRepository.findByUsername(username);
        if (principal != null) {
            throw new CustomException("이미 존재하는 username입니다.");
        }
    }
}
