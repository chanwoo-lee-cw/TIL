package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FlowFilter
 */
@WebFilter({"/fisrtone","/fisrttwo"})
public class FlowFilter implements Filter {

    /**
     * Default constructor. 
     */
    public FlowFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("FlowFilter 객체 생성");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("FlowFilter 객체 삭제");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("서블릿 수행 전....");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("서블릿 수행 후....");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("FlowFilter 객체 초기화");
	}

}
