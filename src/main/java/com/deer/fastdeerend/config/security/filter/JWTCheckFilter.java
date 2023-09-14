package com.deer.fastdeerend.config.security.filter;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.config.security.model.FastDeerUser;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.ResponseUtil;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT检查过滤器，校验token
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Component
public class JWTCheckFilter extends OncePerRequestFilter {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private ResponseUtil responseUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getRequestURI()) || "/admin/login".equals(request.getRequestURI())) {
            doFilter(request, response, filterChain);
            return;
        }

        if (request.getRequestURI().startsWith("/static")) {
            doFilter(request, response, filterChain);
            return;
        }

        /*
         *  下方添加无需登录授权的请求，示例
         *  if ("/example".equals(request.getRequestURL())) {
         *   doFilter(request, response, filterChain);
         *   return;
         *  }
         * */

        if ("/post/selectPostPage".equals(request.getRequestURI()) ||
                "/comment/selectAllCommentByPostId".equals(request.getRequestURI())) {
            doFilter(request, response, filterChain);
            return;
        }

        if ("/academic/selectAcademicDisplayList".equals(request.getRequestURI()) ||
                "/academic/getAcademicContentByAcademicId".equals(request.getRequestURI()) ||
                "/academic/selectAcademicCommentListByAcademicId".equals(request.getRequestURI())) {
            doFilter(request, response, filterChain);
            return;
        }

        if ("/resource/selectResourceList".equals(request.getRequestURI())) {
            doFilter(request, response, filterChain);
            return;
        }

        // The Code during testing, deleted before going live
        if (request.getRequestURI().startsWith("/ws")) {
            doFilter(request, response, filterChain);
            return;
        }

        String authorization = request.getHeader("Authorization");

        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();

        if (!StringUtils.hasText(authorization)) {
            responseUtil.printResult(response, builder.code(HttpResponseStatusCodeSet.Forbidden.getValue()).msg("缺少token").build());
            return;
        }

        VerifyTokenResult verifyTokenResult = jwtUtil.verifyToken(authorization);

        if (!verifyTokenResult.isStatus()) {
            responseUtil.printResult(response, builder.code(HttpResponseStatusCodeSet.Forbidden.getValue()).msg(verifyTokenResult.getData()).build());
            return;
        }
        User user = objectMapper.readValue(verifyTokenResult.getData(), User.class);
        List<SimpleGrantedAuthority> authList = List.of(new SimpleGrantedAuthority(user.getRole()));
        FastDeerUser fastDeerUser = new FastDeerUser(user);
        fastDeerUser.setAuthorityList(authList);
        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(fastDeerUser, fastDeerUser.getPassword(), authList);
        SecurityContextHolder.getContext().setAuthentication(token);
        doFilter(request, response, filterChain);
    }
}
