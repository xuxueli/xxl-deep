package com.xxl.permission.controller.resolver;

import com.xxl.permission.core.exception.WebException;
import com.xxl.permission.core.result.ReturnT;
import com.xxl.permission.core.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常解析器
 * 
 * @author xuxueli
 */
public class WebExceptionResolver implements HandlerExceptionResolver {
	private static transient Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.info(ex.getMessage(), ex);
		ModelAndView mv = new ModelAndView();

		boolean isJson = false;
		HandlerMethod method = (HandlerMethod)handler;
		ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
		if (responseBody != null) {
			isJson = true;
		}

		ReturnT<String> result = new ReturnT<String>(null);
		if (ex instanceof WebException) {
			result.setCode(((WebException) ex).getCode());
			result.setMsg(((WebException) ex).getMsg());
		} else {
			result.setCode(500);
			result.setMsg(ex.toString().replaceAll("\n", "<br/>"));

		}

		if (isJson) {
			/*response.setContentType("application/json;charset=utf-8");
			mv.addObject("result", JacksonUtil.writeValueAsString(result));
			mv.setViewName("/common/common.result");*/

			try {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(JacksonUtil.writeValueAsString(result));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			mv.addObject("exceptionMsg", result.getMsg());
			mv.setViewName("common/common.exception");
		}

		return mv;

	}

	
}
