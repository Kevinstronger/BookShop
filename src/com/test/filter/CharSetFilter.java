package com.test.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/3.
 */
public class CharSetFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取过滤器中配置的参数(在web.xml中配置)
        String encoding = filterConfig.getInitParameter("encoding");
        if(encoding == null){
            encoding = "UTF-8";//给定默认值
        }
        //解决post方式提交的中文乱码问题
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset="+encoding);

        MyHttpServletRequest myHttpServletRequest = new MyHttpServletRequest(request);

        filterChain.doFilter(myHttpServletRequest,response);
    }

    @Override
    public void destroy() {

    }
}



