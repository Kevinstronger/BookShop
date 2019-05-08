package com.test.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/2.
 */
public class WebUtil {
    public static <T> T fillBean(HttpServletRequest request, Class<T> clazz){
        try {
            T bean = clazz.newInstance();
            BeanUtils.populate(bean, request.getParameterMap());
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("封装JavaBean时出错，请检查类的属性和请求参数的名称是否一致");
        }
    }
 }
