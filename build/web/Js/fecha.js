// fecha.js

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('fecha').addEventListener('change', calcularFechaSalida);
});

function calcularFechaSalida() {
    var fechaEntrada = new Date(document.getElementById('fecha').value);
    var numPlan = document.getElementById('NumPlan').value;

    // Lógica para calcular la fecha de salida según el tipo de plan
    var fechaSalida = new Date(fechaEntrada);
    if (numPlan === '1') { // Anualidad
        fechaSalida.setFullYear(fechaSalida.getFullYear() + 1);
    } else if (numPlan === '2') { // Mensual
        fechaSalida.setMonth(fechaSalida.getMonth() + 1);
    } else if (numPlan === '3') { // Trimestral
        fechaSalida.setMonth(fechaSalida.getMonth() + 3);
    } // Agrega más condiciones según sea necesario

    // Formatea la fecha de salida a formato dd/mm/aaaa
    var dia = ("0" + fechaSalida.getDate()).slice(-2);
    var mes = ("0" + (fechaSalida.getMonth() + 1)).slice(-2);
    var anio = fechaSalida.getFullYear();
    var fechaSalidaFormatted = dia + "/" + mes + "/" + anio;
    
    document.getElementById('fechaOut').value = fechaSalidaFormatted;
}


