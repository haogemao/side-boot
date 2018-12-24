/**
 * 
 */
package com.side.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;



/**
 * @author gmc
 * @see 网关统一鉴权过滤器,验证所有请求是否带有accessToken。带有accessToken并且请求服务存在用户权限中。
 * 		则通过验证，并转发到对应的微服务中。当accessToken不存在。转发到认证中心进行登录授权，并返回accessToken。
 * 		存在accessToken时，校验本次请求是否存在用户权限中。不存在转发到统一错误页面。提示权限不足
 */
public class AuthorizationCheckFilter extends ZuulFilter {

	private final Logger logger = LoggerFactory.getLogger(AuthorizationCheckFilter.class);
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;	//客户端id
	
	@Value("${security.oauth2.client.client-secret}")
	private String secret;	//客户端密码
	
//	@Value("${security.oauth2.client.grant-type}")
//	private String grantType;	//认证方式
	
	@Value("${security.oauth2.sso.login-path}")
	private String ssoLoginUrl;	//统一登录地址
	
	/**
     * 是否通过这个过滤器，默认为true，改成false则不启用
     */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器类型选择
	 * pre 为路由前
     * route 为路由过程中
     * post 为路由过程后
     * error 为出现错误的时候
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
     * 用来过滤器排序执行的
     * @return 排序的序号
     */
	@Override
	public int filterOrder() {
		return 0;
	}
	
	/**
     * 过滤器的逻辑
     */
	@Override
	public Object run() throws ZuulException {
		//获取当前请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //取出当前请求
        HttpServletRequest request = ctx.getRequest();
        
        logger.info("当前请求服务url:{}，访问的方法：{}",request.getRequestURL(),request.getMethod());
        
        String accessToken = request.getHeader("accessToken");
        
    	if (StringUtils.isEmpty(accessToken)) {
        	logger.info("当前请求头中不存在accessToken，转发认证中心鉴权");
        	
        	String body = null;
        	if(StringUtils.isNotEmpty(clientId) && StringUtils.isNotEmpty(secret)/* && StringUtils.isNotEmpty(grantType)*/) {
        		body = "clientId=" + clientId + "&secret=" + secret + /*"&grantType=" + grantType + */"&redirect_url=" + request.getRequestURL();
        	}
        	request.setAttribute("clientId", clientId);
        	request.setAttribute("secret", secret);
//        	request.setAttribute("grantType", grantType);
        	request.setAttribute("redirect_url", request.getRequestURL());
        	ctx.put(FilterConstants.REQUEST_URI_KEY, ssoLoginUrl);
        } else {
        	
        }
		
		return null;
	}

}
