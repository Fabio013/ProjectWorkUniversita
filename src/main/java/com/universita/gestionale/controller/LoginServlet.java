// 🔧 Refactor 2025-07
// Classe spostata da `mypackage` a `controller`
// Rinominata per seguire le convenzioni Java per le servlet

package com.universita.gestionale.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.universita.gestionale.dao.Connessione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Connection conn = Connessione.getCon();
		try {
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery("select username,password from studente");
			HttpSession session;

			while (rs.next()) {
				if (rs.getString("username").equalsIgnoreCase(username) && rs.getString("password").equalsIgnoreCase(password)) {
					PreparedStatement smt1 = conn.prepareStatement("select matricola from studente where username=?");
					smt1.setString(1, username);
					ResultSet rs1 = smt1.executeQuery();
					rs1.next();
					String matricola = rs1.getString("matricola");

					Statement smt2 = conn.createStatement();
					ResultSet rs2 = smt2.executeQuery("select idcorso,materia,nome,cognome from corso join professore on cattedra=idprofessore");

					session = request.getSession(true);
					session.setAttribute("matricola", matricola);

					RequestDispatcher rd = request.getRequestDispatcher("studente.jsp");
					request.setAttribute("tabella_corso", rs2);
					rd.forward(request, response);
					return;  // <--- esci subito dopo il forward
				}
			}

			Statement smt3 = conn.createStatement();
			ResultSet rs3 = smt3.executeQuery("select username,password from professore");

			while (rs3.next()) {
				if (rs3.getString("username").equalsIgnoreCase(username) && rs3.getString("password").equalsIgnoreCase(password)) {
					session = request.getSession(true);

					PreparedStatement smt4 = conn.prepareStatement("select nome,cognome,idProfessore from professore where username=?");
					smt4.setString(1, username);
					ResultSet rs4 = smt4.executeQuery();
					rs4.next();
					String nome = rs4.getString("nome");
					String cognome = rs4.getString("cognome");
					int idProfessore = rs4.getInt("idProfessore");

					PreparedStatement smt5 = conn.prepareStatement("select idcorso,materia from corso where cattedra=?");
					smt5.setInt(1, idProfessore);
					ResultSet rs5 = smt5.executeQuery();
					rs5.next();
					int idcorso = rs5.getInt("idcorso");
					String materia = rs5.getString("materia");

					PreparedStatement smt6 = conn.prepareStatement("select idAppello,Data from appello where Materia=?");
					smt6.setInt(1, idcorso);
					ResultSet appelli = smt6.executeQuery();

					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					session.setAttribute("materia", materia);

					RequestDispatcher rd4 = request.getRequestDispatcher("professore.jsp");
					request.setAttribute("appelli", appelli);
					rd4.forward(request, response);
					return;  // <--- esci subito dopo il forward
				}
			}

			// Se non trova username e password in nessuno dei due ruoli
			RequestDispatcher rd3 = request.getRequestDispatcher("index.jsp");
			String messaggio = "username e password non sono presenti";
			request.setAttribute("messaggio", messaggio);
			rd3.forward(request, response);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
