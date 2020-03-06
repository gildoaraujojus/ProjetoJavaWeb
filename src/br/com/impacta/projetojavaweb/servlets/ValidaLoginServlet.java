package br.com.impacta.projetojavaweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.impacta.projetojavaweb.models.Usuario;

@WebServlet("/validaLogin")
public class ValidaLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		if(login != null  && senha != null){
			login = login.trim();
			senha = senha.trim();
			
			if(!login.isEmpty() && !senha.isEmpty() 
					&& login.equals("admin") 
					&& senha.equals("admin123")){

				Usuario usuario = new Usuario();
				usuario.setLogin(login);
				usuario.setSenha(senha);
				req.getSession().setAttribute("usuario", usuario);				
				
				RequestDispatcher dispatcher  = req.getRequestDispatcher("/sistema");
				dispatcher.forward(req, resp);
				return;
			}
		}
			resp.sendRedirect("/ProjetoJavaWeb/erroLogin.html");
	}
}
