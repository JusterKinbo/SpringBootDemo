

package demo.juster.spboot.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;

@Order(1)
//重点
@WebFilter(filterName = "testFilter2", urlPatterns = "/user/*")
public class TestFilterSecond implements Filter {
@Override
public void init(FilterConfig filterConfig) throws ServletException {

}

@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException {
  System.out.println("TestFilter2 before");
  filterChain.doFilter(servletRequest,servletResponse);
  System.out.println("TestFilter2 after");
}

@Override
public void destroy() {

}
}