<%-- 
    Document   : ventas
    Created on : 11 dic. 2023, 15:24:36
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar Venta</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 

        <script>
            // Función para obtener la fecha actual en formato YYYY-MM-DD
            function obtenerFechaActual() {
                const fechaActual = new Date();
                const año = fechaActual.getFullYear();
                let mes = fechaActual.getMonth() + 1;
                let dia = fechaActual.getDate();

                // Agregar un cero al mes o día si son menores a 10
                mes = mes < 10 ? '0' + mes : mes;
                dia = dia < 10 ? '0' + dia : dia;

                return `${año}-${mes}-${dia}`;
                    }

                    // Establecer el valor predeterminado del campo de fecha al cargar la página
                    window.onload = function () {
                        const inputFecha = document.getElementById('FecVID'); // Reemplaza 'tuCampoFecha' con el ID de tu campo de fecha
                        console.log(inputFecha);
                        inputFecha.value = obtenerFechaActual();
                    };
        </script>

        <script>
            function validarNumeroPositivo(input) {
                // Eliminar cualquier caracter no numérico
                input.value = input.value.replace(/[^0-9]/g, '');

                // Obtener el valor actual como un número
                var valor = parseInt(input.value, 10);

                // Validar si el número es negativo o cero
                if (isNaN(valor) || valor <= 0) {
                    alert("Por favor, ingrese un número positivo mayor que cero.");
                    input.value = "";  // Limpiar el campo
                }
            }
        </script>
        
        <script>
        // Agregar un event listener para el evento input
        document.getElementById('numeroDecimal').addEventListener('input', function() {
            // Obtener el valor del campo de entrada
            var valor = parseFloat(this.value);

            // Validar si el valor es menor que 1
            if (isNaN(valor) || valor < 1) {
                alert("Por favor, ingrese un valor mayor o igual a 1.");
                this.value = "";  // Limpiar el campo
            }
        });
    </script>

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
                <form class="my-custom-form" method="post" action="VentasController">

                    <label for="CanP" class="my-form-label">Cantidad de Productos:</label>
                    <input type="number" id="numeroPositivo" oninput="validarNumeroPositivo(this)" autocomplete="off" name="CanP" class="my-form-input" required>

                    <label for="DesV" class="my-form-label">Descripción Venta:</label>
                    <textarea id="id" name="DesV" style="resize: none;" rows="5" cols="10" class="my-form-input"></textarea>

                    <label for="CosV" class="my-form-label">Costo Venta: $</label>
                    <input type="number" id="numeroDecimal" autocomplete="off" name="CosV" class="my-form-input" required>

                    <label for="FecV" class="my-form-label">Fecha Venta: </label>
                    <input type="date" id="FecVID" autocomplete="off" name="FecV" class="my-form-input" required>

                    <label for="ForP" class="my-form-label">Forma de Pago</label>
                    <input type="text" class="my-form-input" name="ForP" autocomplete="off">

                    <button type="submit" class="my-form-button" name="action" value="insertar">Agregar Venta</button>
                </form>


            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, The Godfathers.</p>
        </footer>

    </body>
</html>