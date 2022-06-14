package filters;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//@WebFilter(urlPatterns= {"/registration","/mydata"})
@WebFilter(urlPatterns= {"/index.jsp","/mydata"})
public class FormAccessFilter extends HttpFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		PrintWriter out = response.getWriter();

		//HttpSession session = req.getSession(false);
		
		System.out.println("In filter " + req.getRemoteAddr());
		
		if (req.getRemoteAddr().equals("127.0.0.1")) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN,"You are not allowed to access the servlet!"); 
			out.print("<p> Служители на ООД нямат право да влизат тук. </p>");
		} else {
			chain.doFilter(request, response);
			out.print("<h3><font color='green'>Passed successfuly from IP Filter<font></h3>");
		}
		
		
		//chain.doFilter(request, response);
	}

}
