package shop.mtcoding.blog2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog2.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog2.ex.CustomException;
import shop.mtcoding.blog2.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        validateString(joinReqDto.getUsername());
        validateString(joinReqDto.getPassword());
        validateString(joinReqDto.getEmail());
        userService.join(joinReqDto);
        return "redirect:/loginForm";
    }

    private void validateString(String data) {
        if (data.isEmpty() || data == null) {
            throw new CustomException("username을 입력하세요.");
        }
    }
}
