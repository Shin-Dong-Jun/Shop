package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.LoginDto;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.dto.request.FindIdDto;
import com.example.bravobra.dto.request.FindPasswordDto;
import com.example.bravobra.dto.request.resetPasswordDto;
import com.example.bravobra.repository.LoginAttemptRepository;
import com.example.bravobra.repository.MemberRepository;
import com.example.bravobra.service.LoginAttemptService;
import com.example.bravobra.service.LoginService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.service.PasswordService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.bravobra.domain.QMember.member;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final LoginAttemptService loginAttemptService;

    // Login 화면 호출
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginDto", LoginDto.builder().build());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult result, HttpServletRequest request, HttpServletResponse response, Model model
    ) throws Exception {
        log.debug("Received LoginDto: {}", loginDto);
        log.debug("Email: {}, Password: {}", loginDto.getEmail(), loginDto.getPassword());


        model.addAttribute("loginDto", loginDto);

        //1. 입력을 제대로 했는지 부터 확인
        if (result.hasErrors()) {
            log.warn("Validation errors: {}", result.getAllErrors());
            return "login";
        }
        //2. 제대로 입력했으면 잠금상태 확인
        if (loginAttemptService.isAccountLocked(loginDto.getEmail())) {
            result.reject("loginFail", "계정이 잠겨있습니다. 잠시 후 다시 시도해주세요.");
            model.addAttribute("errorMessage", "계정이 잠겨있습니다. 잠시 후 다시 시도해주세요."); // 에러 메시지 추가
            log.warn("로그인 차단: 계정 {} 잠금 상태", loginDto.getEmail());
            return "login";
        }

        //3. 잠기지 않았으면 로그인 시도
        try {
            Member loginMember = loginService.login(loginDto.getEmail(), loginDto.getPassword());
            loginAttemptService.resetAttempts(loginDto.getEmail()); // 로그인 성공했으니까 리셋.
            //4. 로그인 성공했으면 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

            if (loginMember.getMemberType() != null) {
                session.setAttribute("memberType", loginMember.getMemberType());
            }

            log.info("로그인 성공: 세션 생성, 세션 ID: {}", session.getId());
            //5. 쿠키도 설정
            Cookie loginCookie = new Cookie("loginMember", URLEncoder.encode(loginMember.getEmail(), "UTF-8"));
            loginCookie.setHttpOnly(true);
            loginCookie.setPath("/");
            loginCookie.setMaxAge(60 * 60);

            response.addCookie(loginCookie);

            return "redirect:/";
            // 예외발생하면
        } catch (IllegalArgumentException e) {
            loginAttemptService.recordFailAttempt(loginDto.getEmail());

            int remainingAttempts = LoginAttemptService.MAX_ATTEMPTS - loginAttemptService.getAttempts(loginDto.getEmail());
            if (remainingAttempts > 0) {
                log.warn("로그인 실패: 사용자 {}, 남은 시도 횟수 {}", loginDto.getEmail(), remainingAttempts);
                model.addAttribute("errorMessage", "이메일 또는 비밀번호가 맞지 않습니다. 남은 시도 횟수: " + remainingAttempts);

                result.reject("loginFail", "이메일 또는 비밀번호가 맞지 않습니다. 남은 시도 횟수: " + remainingAttempts);
            } else {
                log.warn("계정 {}잠김", loginDto.getEmail());
                result.reject("loginFail", "계정이 잠겼습니다. 잠시 후 다시 시도해주세요,");
                model.addAttribute("errorMessage", "계정이 잠겼습니다. 잠시 후 다시 시도해주세요.");
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // invalidate 세션 제거하는 메서드.
        }

        //쿠키 삭제
        Cookie loginCookie = new Cookie("loginMember", null);
        loginCookie.setMaxAge(0);
        loginCookie.setPath("/");
        response.addCookie(loginCookie);

        log.info("로그아웃: 세션 및 쿠키 삭제 완료");
        return "redirect:/";
    }


}
