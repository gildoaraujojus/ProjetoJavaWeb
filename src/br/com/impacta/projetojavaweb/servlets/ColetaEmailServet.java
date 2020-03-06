package br.com.impacta.projetojavaweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.impacta.projetojavaweb.models.Usuario;

@WebServlet("/coletaEmail")
public class ColetaEmailServet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		PrintWriter out = resp.getWriter();
		out.println("<html> <head>");
		out.println("<title>Cadastro de Email</title>");
		out.println("</head> <body>");
		out.println("<h2>" + usuario.getLogin() + " informe seu email: </h2><br/>");
		out.println("<form method ='post' action='adicionaEmailCookie'>");
		out.println("Email <br> <input type='text' name='email' size='80'> <br/>");
		out.println("<p><input type='submit' value='Enviar'></p>");
		out.println("</body>");
		out.println("</html>");		
	}
}
