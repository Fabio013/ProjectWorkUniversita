<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Università</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
    // 🔐 Validazione lato client
    function validateForm() {
        const username = document.forms["loginForm"]["username"].value;
        const password = document.forms["loginForm"]["password"].value;
        const errorDiv = document.getElementById("error-msg");

        if (username.trim() === "" || password.trim() === "") {
            errorDiv.textContent = "⚠️ Inserisci username e password";
            return false;
        }
        return true;
    }
    </script>
</head>
<body>
    <%
    String messaggio = (String) request.getAttribute("messaggio");
    %>

    <% if (messaggio != null) { %>
        <p class="error"><%= messaggio %></p>
    <% } %>

    <form name="loginForm" action="login" method="post" onsubmit="return validateForm();">
        <h2>Login Universitario</h2>
        <input type="text" name="username" placeholder="Username"><br>
        <input type="password" name="password" placeholder="Password"><br>
        <input type="submit" value="Accedi">
        <div id="error-msg" class="error"></div>
    </form>
</body>
</html>
