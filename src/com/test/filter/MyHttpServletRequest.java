package com.test.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/5/4.
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper {
    public MyHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    //只对get请求方式进行改写
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if(value == null)
            return value;
        //判断请求方式
        if("get".equalsIgnoreCase(super.getMethod())){
            try {
                value = new String(value.getBytes("ISO-8859-1"), super.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}