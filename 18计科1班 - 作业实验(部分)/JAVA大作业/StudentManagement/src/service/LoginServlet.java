package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = null;
		//瀹炰緥鍖朥serDao瀵硅薄
		UserDao userDao = new UserDao();
		User user = userDao.login(username, password);
	    System.out.print(user);
		//鍒ゆ柇鏄惁鐧诲綍鎴愬姛
		if(user != null){//鎴愬姛
			level = user.getLevel();
			if(level.equals("管理员")){
				request.getSession().setAttribute("user", user);//灏嗙敤鎴峰璞℃斁鍒皊ession涓�
				//杞彂鍒皍ser.jsp涓�
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("admin", user);//灏嗙鐞嗗憳瀵硅薄鏀惧埌session涓�
				//杞彂鍒癮dmin.jsp涓�
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}	
		}else {//澶辫触
			request.setAttribute("info"," 閿欒:鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒锛�");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
