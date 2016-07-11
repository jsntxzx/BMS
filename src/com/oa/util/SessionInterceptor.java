package com.oa.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		String uri = request.getRequestURI();
//
//		if (uri.indexOf("/web") != -1) {
//			return super.preHandle(request, response, handler);
//		}
//		if (uri.indexOf("/app") != -1) {
//			return super.preHandle(request, response, handler);
//		}
//		if (uri.indexOf("/login") != -1) {
//			return super.preHandle(request, response, handler);
//		}
//		HttpSession sesson = request.getSession();
//		if (sesson.getAttribute("session_user") == null) {
//			String path = request.getContextPath();
//			request.setAttribute("login_failure", "登录超时，请重新登录");
//			String basePath = request.getScheme() + "://"
//					+ request.getServerName() + ":" + request.getServerPort()
//					+ path;
//			response.sendRedirect(basePath);
//			return false;
//		}

		return super.preHandle(request, response, handler);

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// request.getRequestDispatcher("../admin.jsp").forward(request,
		// response);
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// request.getRequestDispatcher("../admin.jsp").forward(request,
		// response);
	}
}
