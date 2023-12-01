<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Styles/homePage.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
        <title>BlackGYM</title>
    </head>
    <body>

        <!-- Barra de navegaci�n moderna -->
        <nav class="navbar">
            <div class="navbar-left">
                <img src="img/LogoBlackGym-removebg-preview.png" alt="Logo de tu empresa" class="logo">
            </div>
            <div class="navbar-right">
                <input type="text" placeholder="Buscar..." id="searchInput">
            </div>
            <!-- Agregamos el bot�n de hamburguesa para dispositivos m�viles -->
            <div class="burger-menu" onclick="toggleSidebar()">
                <div class="bar"></div>
                <div class="bar"></div>
                <div class="bar"></div>
            </div>
        </nav>

        <!-- Contenido principal -->
        <div class="main-container">
            <aside class="sidebar">
                <div class="menu-item"><a href="#"><i class="icono"></i> Planes</a></div>
                <div class="menu-item submenu">
                    <a href="#"><i class="icono"></i> Socios</a>
                    <div class="submenu-content">
                        <a href="#"><i class="icono"></i> Agregar</a>
                        <a href="#"><i class="icono"></i> Ver Socios</a>
                    </div>
                </div>
                <div class="menu-item"><a href="#"><i class="icono"></i> Ventas</a></div>
                <div class="menu-item"><a href="#"><i class="icono"></i> Reportes</a></div>
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
                        <div class="aquare-info">
                            <div class="panel-heading">
                                <!-- <h3 class="panel-title"> Numero de Vacante: ${vacante.id}</h3> -->
                                <h2 class="panel-title"> Nombre Cliente</h3>
                            </div>
                            <div class="panel-heading">
                                <figure>
                                    <img src="img/user_icon_149851.png" alt="Foto del Usuario">
                                </figure>
                            </div>
                            <div class="panel-heading">
                                <h3><b>Inicio: 22/04/2022</b> </h3>
                                <h3><b>Vence: 22/05/2022</b> </h3>                             
                                <h3><b>Paquete: Mensual</b> </h3> <br>
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

        <!-- Tu contenido aqu� -->

        <script src="script.js"></script>
    </body>
</html>
