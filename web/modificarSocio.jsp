<%-- 
    Document   : modificarSocio
    Created on : 9 dic. 2023, 21:35:15
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico">
        <title>Modificar Socio</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 
    </head>
    <body>
        <div class="masthead">
            <h3 class="text-muted">BlackGym</h3>
            <nav>
                <ul class="nav nav-justified">
                    <li><a href="PlanesController?action=inicio">Inicio</a></li>            
                    <li><a href="SociosController?action=verSocios">Socios</a></li>                        
                    <li><a href="PlanesController?action=verPlanes">Planes</a></li>            
                </ul>
            </nav>
        </div>

        <div class="main-content">
            <div class="custom-form-container">
                <form class="my-custom-form" method="post" action="SociosController">
                    <input type="hidden" class="my-form-input" name="fol" value="${socio.fol}">

                    <label for="Nom" class="my-form-label">Nombre:</label>
                    <input type="text" autocomplete="off" name="Nom" value="${socio.nom}" class="my-form-input" required>

                    <label for="Eda" class="my-form-label">Edad:</label>
                    <input type="text" autocomplete="off" name="Eda" class="my-form-input" value="${socio.eda}" required>

                    <label for="Tel" class="my-form-label">Telefono:</label>
                    <input type="text" autocomplete="off" name="Tel" value="${socio.tel}" class="my-form-input" required>

                    <label for="CorElec" class="my-form-label">Email:</label>
                    <input type="text" autocomplete="off" name="CorElec" class="my-form-input" value="${socio.corElec}" required>

                    <label for="NumPlan" class="my-form-label">Seleccionar Plan:</label>
                    <select name="NumPlan" class="my-form-input" required>
                        <c:forEach var="plan" items="${listaPlanes}">
                            <c:choose>
                                <c:when test="${socio.numPlan eq plan.numPlan}">
                                    <option value="${plan.numPlan}" selected>${plan.nom}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${plan.numPlan}">${plan.nom}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>

                    <label for="fecha" class="my-form-label" >Seleccionar Fecha:</label>
                    <input type="date" class="my-form-input" value="${socio.inp}" id="fecha" name="fecha" required>

                    <label for="fechaOut" class="my-form-label">Fecha de Termino:</label>
                    <input type="date" class="my-form-input" id="fechaOut" value="${socio.fip}" name="fechaOut" readonly>


                    <button type="submit" class="my-form-button" name="action" value="modificar">Modificar Socio</button>
                </form>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, The Godfathers.</p>
        </footer>
                    
        <script src="Js/fecha.js"></script>
    </body>
</html>