// Evita seleccionar el mismo equipo como local y visitante.
const homeSelect = document.getElementById('homeTeamId');
const awaySelect = document.getElementById('awayTeamId');

function updateTeamOptions(changedSelect = null) {
    // Si el formulario llega con ambos equipos iguales, conserva el último
    // cambio del usuario y limpia la selección del otro selector.
    if (homeSelect.value !== '' && homeSelect.value === awaySelect.value) {
        if (changedSelect === homeSelect) {
            awaySelect.value = '';
        } else {
            homeSelect.value = '';
        }
    }

    const homeId = homeSelect.value;
    const awayId = awaySelect.value;

    // Deshabilita únicamente opciones de equipos. El placeholder vacío
    // permanece deshabilitado y nunca se modifica.
    Array.from(homeSelect.options).forEach(option => {
        if (option.value !== '') {
            option.disabled = option.value === awayId;
        }
    });

    Array.from(awaySelect.options).forEach(option => {
        if (option.value !== '') {
            option.disabled = option.value === homeId;
        }
    });
}

homeSelect.addEventListener('change', () => updateTeamOptions(homeSelect));
awaySelect.addEventListener('change', () => updateTeamOptions(awaySelect));
updateTeamOptions();