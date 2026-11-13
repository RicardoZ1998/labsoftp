document.addEventListener("DOMContentLoaded", function () {

    // =========================
    // MENÚ LATERAL
    // =========================

    const menuToggle = document.getElementById("menuToggle");
    const sidebar = document.getElementById("sidebar");

    if (menuToggle && sidebar) {

        menuToggle.addEventListener("click", function () {

            sidebar.classList.toggle("show");

        });

    }


    // =========================
    // BUSCADOR DE USUARIOS
    // =========================

    const buscar = document.getElementById("buscarUsuario");

    const tabla = document.getElementById("tablaUsuarios");

    const contador = document.getElementById("usuariosVisibles");

    if (buscar && tabla) {

        buscar.addEventListener("input", function () {

            const texto = buscar.value.toLowerCase();

            const filas = tabla.querySelectorAll("tbody tr");

            let visibles = 0;

            filas.forEach(function (fila) {

                const contenido = fila.innerText.toLowerCase();

                if (contenido.includes(texto)) {

                    fila.style.display = "";

                    visibles++;

                } else {

                    fila.style.display = "none";

                }

            });

            if (contador) {
                contador.textContent = visibles;
            }

        });

    }


    // =========================
    // CONTADOR INICIAL
    // =========================

    if (tabla && contador) {

        const total = tabla.querySelectorAll("tbody tr").length;

        contador.textContent = total;

    }


    // =========================
    // CONFIRMAR ELIMINACIÓN
    // =========================

    const formularios = document.querySelectorAll(".form-delete");

    formularios.forEach(function (formulario) {

        formulario.addEventListener("submit", function (evento) {

            const confirmar = confirm(
                "¿Está seguro de eliminar este usuario?"
            );

            if (!confirmar) {

                evento.preventDefault();

            }

        });

    });

});

// =================================
// MOSTRAR Y OCULTAR CONTRASEÑA
// =================================

document.addEventListener("DOMContentLoaded", function () {

    const botones = document.querySelectorAll(".toggle-password");

    botones.forEach(function (boton) {

        boton.addEventListener("click", function () {

            const idCampo = boton.getAttribute("data-target");

            const campo = document.getElementById(idCampo);

            if (!campo) {
                return;
            }

            if (campo.type === "password") {

                campo.type = "text";

                boton.textContent = "🙈";

            } else {

                campo.type = "password";

                boton.textContent = "👁️";

            }

        });

    });

});