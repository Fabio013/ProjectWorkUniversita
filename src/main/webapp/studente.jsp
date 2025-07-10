<%-- 
 Refactor 2025-07
 Autore: Fabio013

 Descrizione UI:
 - Aggiunto foglio di stile esterno (style.css)
 - Tabelle responsive e leggibili
 - Layout coerente con index.jsp (login)
 - Miglioramento UX generale con messaggi più chiari

 Descrizione logica:
 - Aggiunto controllo sessione matricola
 - Evitato output "null" indesiderato
 - Corretta stampa messaggi condizionali

 Note:
 Questo file è parte di un refactoring strutturato per modernizzare
 l'interfaccia JSP senza alterare la logica back-end esistente.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Studenti</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<%
String matricola = (String) session.getAttribute("matricola");
ResultSet res = (ResultSet) request.getAttribute("tabella_corso");
ResultSet res1 = (ResultSet) request.getAttribute("elenco_appelli");
String materia = (String) request.getAttribute("materia");
String messaggio = (String) request.getAttribute("successo");
String data = (String) request.getAttribute("data");
String materia2 = (String) request.getAttribute("materia2");

if (matricola == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>

<h2>🎓 Benvenuto Studente (matricola: <%= matricola %>)</h2>
<a href="logout.jsp">Logout</a>

<% if (res != null) { %>
    <h3>Corsi disponibili:</h3>
    <table>
        <tr>
            <th>ID Corso</th>
            <th>Materia</th>
            <th>Nome Docente</th>
            <th>Cognome Docente</th>
        </tr>
        <% while (res.next()) { %>
        <tr>
            <td><%= res.getInt("idcorso") %></td>
            <td><%= res.getString("materia") %></td>
            <td><%= res.getString("nome") %></td>
            <td><%= res.getString("cognome") %></td>
        </tr>
        <% } %>
    </table>

    <form action="Prenotazione" method="post">
        <p>Inserisci l'ID del corso da prenotare:</p>
        <input type="number" name="materia">
        <input type="submit" value="Prenota">
    </form>
<% } %>

<% if (res1 != null) { %>
    <h3>Appelli disponibili per <%= materia %>:</h3>
    <table>
        <tr>
            <th>ID Appello</th>
            <th>Data</th>
        </tr>
        <% while (res1.next()) { %>
        <tr>
            <td><%= res1.getInt("idAppello") %></td>
            <td><%= res1.getDate("Data") %></td>
        </tr>
        <% } %>
    </table>

    <form action="Prenota" method="post">
        <p>Inserisci l'ID dell'appello da prenotare:</p>
        <input type="number" name="appello">
        <input type="submit" value="Prenota">
    </form>
<% } %>

<% if (messaggio != null) { %>
    <p><%= messaggio %></p>
<% } %>

<% if (materia2 != null && data != null) { %>
    <p>✅ Prenotazione effettuata con successo in data <%= data %> per il corso <%= materia2 %></p>
<% } %>

</body>
</html>
