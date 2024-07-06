package com.xxl.deep.admin.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * permission annotation
 *
 * @author xuxueli 2015-12-12 18:29:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
	
	/**
	 * 登录拦截 (默认拦截)
	 */
	boolean limit() default true;

	/**
	 * 要求管理员权限（默认需要）
	 *
	 * @return
	 */
	boolean adminuser() default false;

}