package com.example.bravobra.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AdminInterceptor  implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        // session이 null인지 먼저 확인
        if (session == null) {
            log.info(">>>>>>>>>> Session is null");
            response.sendRedirect("/errorAccess");
            return false;
        }

        Object member = session.getAttribute("memberType");

        // member 값이 "ADMIN"이 아닌 경우 처리
        if (!("ADMIN".equals(member))) {
            log.info(">>>>>>>>>> Session memberType: {}", member);
            response.sendRedirect("/errorAccess");
            return false;
        } // 미치겠네 진짜  admin 인데 왜자꾸 이 조건문을 타는거냐고~~~~!!!!!! null은 아닌데 확실히./

        return true;
    }
}

