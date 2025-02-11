package cn.yiming1234.NottinghamWall.interceptor;

import cn.yiming1234.NottinghamWall.constant.JwtClaimsConstant;
import cn.yiming1234.NottinghamWall.context.BaseContext;
import cn.yiming1234.NottinghamWall.properties.JwtProperties;
import cn.yiming1234.NottinghamWall.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     */
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;

        }
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Integer empId = (Integer) claims.get(JwtClaimsConstant.EMP_ID);
            log.info("当前员工id:{}", empId);
            BaseContext.setCurrentId(empId);
            return true;
        } catch (ExpiredJwtException ex) {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);
            BaseContext.setCurrentId(userId);

            Map<String, Object> newClaims = new HashMap<>(claims);
            String refreshToken = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), newClaims);
            log.info("生成的新jwt令牌: {}", refreshToken);
            return  true;
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }
}
