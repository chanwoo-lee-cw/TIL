package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class HangulFilter
 */
@WebFilter("/*")
public class HangulFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 매개변수는 ServletReques지만 실제로 전달 되는 것은 그 자식 클래스인 HttpServletRequest다./
		// 그래서 사용하려면 강제 형변환을 해줘야 한다.
		
		System.out.println("HangulFilter 수행 - before");
		if(((HttpServletRequest)request).getMethod().equals("POST"))
			request.setCharacterEncoding("UTF-8");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("HangulFilter 수행 - after");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
