package com.booking.controller.filter;

import com.booking.entity.Role;
import com.booking.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permission = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);

        Set<Role> client = new HashSet<>();
        client.add(Role.CLIENT);

        permission.put("/logout", all);
        permission.put("/room/list", all);
        permission.put("/order/list", all);
        permission.put("/order/edit", all);
        permission.put("/order/save", all);

        permission.put("/admin", admin);

        permission.put("/user/list", admin);
        permission.put("/user/edit", admin);
        permission.put("/user/save", admin);
        permission.put("/user/delete", admin);

        permission.put("/bill/list", admin);
        permission.put("/bill/edit", admin);
        permission.put("/bill/save", admin);
        permission.put("/bill/delete", admin);

        permission.put("/room/edit", admin);
        permission.put("/room/save", admin);
        permission.put("/room/delete", admin);

        permission.put("/order/delete", admin);

        permission.put("/client", client);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<Role> roles = permission.get(url);
        if (roles != null) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("session_user");
                if (user != null && roles.contains(user.getRole())) {
                    filterChain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            filterChain.doFilter(req, resp);
            return;
        }
        resp.sendRedirect(context + "/login.html?message=" + URLEncoder.encode("Доступ запрещен", StandardCharsets.UTF_8));
    }
}
