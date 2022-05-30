package com.huanghong.lab;

import javax.servlet.*;
import java.io.IOException;

public class YourNameFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("YourNameFilter-->before chain");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("YourNameFilter-->after chain");
    }

    @Override
    public void destroy() {

    }
}
