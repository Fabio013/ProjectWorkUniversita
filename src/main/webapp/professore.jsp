<%-- 
 Refactor 2025-07
 Autore: Fabio013

 Descrizione logica:
 - Corretto controllo su attributi sessione "nome" e "cognome"
 - Evitata stampa di "nullnull" in assenza di dati
 - Introdotto return per bloccare esecuzione dopo redirect

 Descrizione UI:
 - Allineamento visivo con login e studente.jsp
 - Tabelle migliorate con CSS responsive
 - Messaggi chiari per UX più fluida
 - Uniformità con stile moderno e accessibile

 Note:
 Questa modifica previene bug visivi e comportamenti errati
 se il professore accede alla pagina senza sessione valida.
 Inoltre migliora l'usabilità e la leggibilità con stile coerente.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Professore</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<%
String nome = (String) session.getAttribute("nome");
String cognome = (String) session.getAttribute("cognome");
String materia = (String) session.getAttribute("materia");
ResultSet appelli = (ResultSet) request.getAttribute("appelli");
ResultSet elenco = (ResultSet) request.getAttribute("elenco_studenti");
String nomeMateria = (String) request.getAttribute("Materia");
String Data = (String) request.getAttribute("Data");

if (nome == null || cognome == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>

<h2>👨‍🏫 Bentornato <%= nome %> <%= cognome %></h2>
<a href="logout.jsp">Logout</a>

<% if (appelli != null) { %>
    <h3>Appelli disponibili per la materia: <%= materia %></h3>
    <table>
        <tr>
            <th>ID Appello</th>
            <th>Data</th>
        </tr>
        <% while (appelli.next()) { %>
        <tr>
            <td><%= appelli.getInt(1) %></td>
            <td><%= appelli.getDate("Data") %></td>
        </tr>
        <% } %>
    </table>

    <form action="StampaStudenti" method="post">
        <p>Inserisci l'ID dell'appello per visualizzare gli studenti:</p>
        <input type="number" name="ID_appello">
        <input type="submit" value="Visualizza Iscritti">
    </form>
<% } %>

<% if (elenco != null) { %>
    <h3>Studenti prenotati per <%= nomeMateria %> in data <%= Data %></h3>
    <table>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Matricola</th>
        </tr>
        <% while (elenco.next()) { %>
        <tr>
            <td><%= elenco.getString("nome") %></td>
            <td><%= elenco.getString("cognome") %></td>
            <td><%= elenco.getString("Matricola") %></td>
        </tr>
        <% } %>
    </table>
<% } %>

</body>
</html>