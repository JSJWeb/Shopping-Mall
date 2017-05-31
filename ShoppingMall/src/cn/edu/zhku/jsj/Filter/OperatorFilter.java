package cn.edu.zhku.jsj.Filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.Model.User;
/*
 * 运营商权限控制
 */
@WebFilter(filterName="OperatorFilter",urlPatterns="/Operator/*")
public class OperatorFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		if(null!=session.getAttribute("user"))
		{
			User user=(User)session.getAttribute("user");
			if(user.getType()!=2)
			{
				resp.sendRedirect(req.getContextPath()+"/authorityError.jsp");
				return;
			}
				
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/authorityError.jsp");
			return;
		}
			
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
