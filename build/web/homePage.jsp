<%@ page import="java.net.URLEncoder" %><!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Styles/homePage.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
        <title>BlackGYM</title>

        <!-- Agrega la referencia a jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

        <!-- Agrega el script JavaScript para el autocompletado -->
        <script>
            $(document).ready(function () {
                // Captura el evento de escribir en el campo de búsqueda
                $("#searchInput").on("input", function () {
                    // Obtén el valor del campo de búsqueda
                    var query = $(this).val();

                    // Realiza una solicitud AJAX al servidor para obtener sugerencias
                    $.ajax({
                        type: "POST",
                        url: "AutocompleteController",
                        data: {query: query},
                        success: function (data) {
                            // Verifica si data es un objeto y no una cadena
                            var suggestions = typeof data === 'string' ? JSON.parse(data) : data;

                            // Actualiza el contenido de la lista de sugerencias
                            var suggestionList = $("#suggestionList");
                            suggestionList.empty(); // Limpia las sugerencias anteriores

                            // Agrega las nuevas sugerencias como enlaces
                            suggestions.forEach(function (suggestion) {
                                suggestionList.append("<a href='#' data-nom='" + suggestion.Nom + "' data-inp='" + suggestion.Inp + "' data-fip='" + suggestion.Fip + "' data-fol='" + suggestion.fol + "'>" + suggestion.Nom + "</a>");
                            });
                        }
                    });
                });

                // Maneja el clic en un enlace de sugerencia
                $("#suggestionList").on("click", "a", function (event) {
                    event.preventDefault();

                    // Obtén los datos del enlace clicado
                    var selectedSocio = {
                        fol: $(this).data("fol"),
                        Nom: $(this).data("nom"),
                        Inp: $(this).data("inp"),
                        Fip: $(this).data("fip")
                    };

                    // Realiza alguna acción con los datos del socio
                    console.log("Datos del socio seleccionado:", selectedSocio);

                    // Redirecciona a la página deseada con los parámetros del socio
                    window.location.href = "homePage.jsp?nombre=" + encodeURIComponent(selectedSocio.Nom) + "&inp=" + selectedSocio.Inp + "&fip=" + selectedSocio.Fip + "&fol=" + selectedSocio.fol;
                    console.log("Datos del socio seleccionado:", selectedSocio);
                });
            });

        </script>

    </head>
    <body>

        <!-- Barra de navegaciï¿½n moderna -->
        <nav class="navbar">
            <div class="navbar-left">
                <img src="img/LogoBlackGym-removebg-preview.png" alt="Logo de BlackGym" class="logo">
            </div>
            <div class="contenedorBuscardor">
                <form method="post" class="navbar-form navbar-right" id="searchForm">
                    <div class="form-group">
                        <input type="text" autocomplete="off" name="query" required placeholder="Buscar Socio..." class="form-control" id="searchInput">
                    </div>
                </form>
                <div id="suggestionsContainer">
                    <ul id="suggestionList"></ul>
                </div>
            </div>

            <!-- Agregamos el botï¿½n de hamburguesa para dispositivos mï¿½viles -->
            <div class="burger-menu" onclick="toggleSidebar()">
                <div class="bar"></div>
                <div class="bar"></div>
                <div class="bar"></div>
            </div>
        </nav>

        <!-- Contenido principal -->
        <div class="main-container">
            <aside class="sidebar">
                <div class="menu-item"><a href="PlanesController?action=verPlanes"><i class="icono"></i> Planes</a></div>
                <div class="menu-item submenu">
                    <a href="SociosController?action=verSocios"><i class="icono"></i> Socios</a>
                    <div class="submenu-content">
                        <a href="SociosController?action=agregarSocios"><i class="icono"></i> Agregar</a>
                        <a href="SociosController?action=verSocios"><i class="icono"></i> Ver Socios</a>
                    </div>
                </div>
                <div class="menu-item"><a href="VentasController?action=agregarVentas"><i class="icono"></i> A. Ventas</a></div>
                <div class="menu-item"><a href="VentasController?action=verReporte"><i class="icono"></i> Reportes</a></div>
                <div class="menu-item"><a href="LoginController?action=irAdministracion"><i class="icono"></i> Administrador</a></div>
                <div class="menu-item"><a href="#"><i class="icono"></i> Productos</a></div>
            </aside>

            <!-- Contenedor adicional -->
            <div class="additional-container">
                <div class="rectangle">
                    <p>BIENVENIDO!!!</p>
                </div>
                <div class="cuadrados_container">

                    <div class="square">
                        <div class="square-info">
                            <div class="panel-heading">
                                <h2 class="panel-title"> ${param.nombre} </h3>
                            </div>
                            <div class="panel-heading">
                                <figure>
                                    <img src="img/user_icon_149851.png" alt="Foto del Usuario">
                                </figure>
                            </div>
                            <div class="panel-heading">
                                <h3><b>Folio: ${param.fol} </b> </h3>
                                <h3><b>Inicio: ${param.inp} </b> </h3>
                                <h3><b>Vence: ${param.fip} </b> </h3>                                                           
                            </div>
                        </div> 
                    </div>

                    <div class="square">
                        <div class="aquare-info">
                            <div class="panel-heading">
                                <figure>
                                    <img src="img/tiempo.jpg" alt="Foto del limite" class="tiempo">
                                </figure>
                            </div>
                            <div class="panel-heading">
                                <h3><b>Porcentaje del avance del plan</b> </h3>
                            </div>
                        </div> 
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>