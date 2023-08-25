package com.txxw.sso.config.security;

import com.alibaba.fastjson.JSONObject;
import com.txxw.sso.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 *
 * @author zhoubin
 * @since 1.0.0
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader(tokenHeader);
		//是否存在token,存在是否以tokenHead开头
		if (null != authHeader && authHeader.startsWith(tokenHead)) {
			//得到token
			String authToken = authHeader.substring(tokenHead.length());
			//根据token获取用户名
			String username = jwtTokenUtil.getUserNameFromToken(authToken);
			//token存在用户名但未登录
			if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
				//登录，拿到userDetails就相当于登陆了，token存在就用userDetailsService去登录
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				//验证token是否有效，重新设置用户对象
				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}else{//验证token无效
					response.setContentType("application/json;charset=utf-8");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", 401);
					jsonObject.put("msg", "token失效");
					response.getWriter().write(jsonObject.toString());
                    response.getWriter().flush();
                    response.getWriter().close();
				}

			}
		}
		filterChain.doFilter(request, response);
	}
}