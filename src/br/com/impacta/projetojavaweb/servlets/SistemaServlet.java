package br.com.impacta.projetojavaweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.impacta.projetojavaweb.models.Usuario;

@WebServlet("/sistema")
public class SistemaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailReq = req.getParameter("email");
		Cookie[] cookies = req.getCookies();
		String email = null;
		
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("email")){
					email = cookie.getValue();
					break;
				}
			}
		}

		if(email == null && emailReq !=null && !emailReq.isEmpty()){
			email = emailReq;
		}
		
		if(email == null){
			RequestDispatcher dispatcher = req.getRequestDispatcher("/coletaEmail");
			dispatcher.forward(req, resp);
		}else{
		
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
			usuario.setEmail(email);
			
			resp.setCharacterEncoding("ISO-8859-1");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html> <head>");
			out.println("<title>Sistema - Home</title>");
			out.println("</head> <body>");
			out.println("<h1>Bem Vindo " + usuario.getLogin() + "  !" + "</h1>");
			out.println("<h1>Seu email é " + email + "</h1>");
			out.println("<h3>Esta é uma página principal do sistema</h3>");
			out.println("<h3><a href='login.html'>Logout</a></h3>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
