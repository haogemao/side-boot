/**
 * 
 */
package com.side.service.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * @author gmc
 * @see 自定义认证成功处理方法
 */
public class MySavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache(); 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
//		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		User user = (User)authentication.getPrincipal();

		request.getSession().setAttribute("user", user);
		
		String url = null;
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			url = savedRequest.getRedirectUrl();
		} else {
			url = "http://localhost:8801/side/dologin";
			super.onAuthenticationSuccess(request, response, authentication);
			getRedirectStrategy().sendRedirect(request, response, url);
		}

		
//		if(savedRequest == null) {
//			int flag = 0;
//            //通过提交登录请求传递需要回调的URL callCustomRediretUrl
//            if(request.getSession().getAttribute("callCustomRediretUrl") != null && !"".equals(request.getSession().getAttribute("callCustomRediretUrl"))){
//                String url = String.valueOf(request.getSession().getAttribute("callCustomRediretUrl"));
//                //若session 存在则需要使用自定义回调的URL 而不是缓存的URL
//                super.setDefaultTargetUrl(url);
//                super.setAlwaysUseDefaultTargetUrl(true);
//                flag = 1;
//                request.getSession().setAttribute("callCustomRediretUrl", "");
//            }
//            //重设置默认URL为主页地址
//            if(flag  == 0){
//                super.setDefaultTargetUrl("/side/dologin");
//            }
//            super.onAuthenticationSuccess(request, response, authentication);
//		} else {
//			String targetUrlParameter = getTargetUrlParameter();
//	        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
//	            requestCache.removeRequest(request, response);
//	            super.setAlwaysUseDefaultTargetUrl(false);
//	            super.setDefaultTargetUrl("/");
//	            super.onAuthenticationSuccess(request, response, authentication);
//	            return;
//	        }
//	        //清除属性
//	        clearAuthenticationAttributes(request);
//	        // Use the DefaultSavedRequest URL
//	        String targetUrl = savedRequest.getRedirectUrl();
//	        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
//	        if(targetUrl != null && "".equals(targetUrl)){
//	            targetUrl = "/side/dologin";
//	        }
//	        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//		}
	}
	
}
