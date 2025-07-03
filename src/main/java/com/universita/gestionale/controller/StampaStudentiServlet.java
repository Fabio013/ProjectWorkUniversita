// 🔧 Refactor 2025-07
// Classe spostata da `mypackage` a `controller`
// Rinominata per seguire le convenzioni Java per le servlet

package com.universita.gestionale.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.universita.gestionale.dao.Connessione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class StampaStudenti
 */
@WebServlet("/StampaStudenti")
public class StampaStudentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StampaStudentiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idAppello= request.getParameter("ID_appello");
		Connection conn=Connessione.getCon();

		try {
			/*PreparedStatement smt1=conn.prepareStatement("select stud_prenotato from prenotazione where app_prenotato=CAST(? AS UNSIGNED INTEGER)");
			smt1.setString(1, idAppello);
			ResultSet rs1=smt1.executeQuery();
			while(rs1.next()) {


			String stud=rs1.getString(1);*/
			PreparedStatement smt= conn.prepareStatement("select Materia,Data from appello where idAppello=CAST(? AS UNSIGNED INTEGER)");
			smt.setString(1, idAppello);
			ResultSet rs=smt.executeQuery();
			rs.next();
			String Materia= rs.getString("Materia");
			String Data= rs.getString("Data");
			PreparedStatement smt2= conn.prepareStatement("select Materia from corso where idcorso=CAST(? AS UNSIGNED INTEGER)");
			smt2.setString(1, Materia);
			ResultSet rs2= smt2.executeQuery();
			rs2.next();
			String nomeMateria= rs2.getString(1);
			PreparedStatement smt1= conn.prepareStatement("select nome,cognome,Matricola from studente join (appello join prenotazione on CAST(? AS UNSIGNED INTEGER)=app_prenotato) on Matricola=stud_prenotato");
			smt1.setString(1, idAppello);
			ResultSet rs1=smt1.executeQuery();
			RequestDispatcher rd= request.getRequestDispatcher("professore.jsp");
			request.setAttribute("Materia",nomeMateria);
			request.setAttribute("Data",Data);
			request.setAttribute("elenco_studenti", rs1);
			rd.forward(request, response);






		} catch (SQLException e) {

			e.printStackTrace();
		}



	}

	

}
