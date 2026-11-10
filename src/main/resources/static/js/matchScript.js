const playerSelect =
    document.getElementById("playerSelect");

const goalsInput =
    document.getElementById("goalsInput");

const yellowCardsInput =
    document.getElementById("yellowCardsInput");

const redCardInput =
    document.getElementById("redCardInput");

const addPlayerButton =
    document.getElementById("addPlayerButton");

const container =
    document.getElementById("playerStatsContainer");

const emptyStatsMessage =
    document.getElementById("emptyStatsMessage");

const statsCounter =
    document.getElementById("statsCounter");


/*
 * ============================================================
 * INITIALIZE
 * ============================================================
 */

document.addEventListener("DOMContentLoaded", function () {

    updateIndexes();

    updatePlayerOptions();

    updateCounter();

    updateEmptyMessage();

});


/*
 * ============================================================
 * ADD PLAYER
 * ============================================================
 */

addPlayerButton.addEventListener("click", function () {

    const playerId = playerSelect.value;

    if (!playerId) {

        alert("Selecciona un jugador.");

        return;
    }


    /*
     * Prevent duplicate players.
     */

    const existingPlayer =
        container.querySelector(
            `.player-stat[data-player-id="${playerId}"]`
        );

    if (existingPlayer) {

        alert("Este jugador ya está agregado al partido.");

        return;
    }


    const playerName =
        playerSelect.options[
            playerSelect.selectedIndex
            ].text;

    const goals =
        Number(goalsInput.value) || 0;

    const yellowCards =
        Number(yellowCardsInput.value) || 0;

    const redCard =
        redCardInput.checked;


    /*
     * Create the new row.
     */

    const row =
        document.createElement("div");

    row.classList.add(
        "player-stat",
        "border",
        "rounded-3",
        "p-3",
        "mb-3"
    );

    row.dataset.playerId = playerId;


    row.innerHTML = `

            <input type="hidden"
                   class="stat-player-id">

            <div class="row g-3 align-items-center">

                <div class="col-12 col-md-3">

                    <div class="fw-semibold">
                        ${escapeHtml(playerName)}
                    </div>

                </div>

                <div class="col-6 col-md-2">

                    <label class="form-label small text-muted">
                        Goles
                    </label>

                    <input type="number"
                           class="form-control stat-goals"
                           min="0"
                           max="20"
                           value="${goals}">

                </div>


                <div class="col-6 col-md-2">

                    <label class="form-label small text-muted">
                        Amarillas
                    </label>

                    <input type="number"
                           class="form-control stat-yellow"
                           min="0"
                           max="2"
                           value="${yellowCards}">

                </div>


                <div class="col-6 col-md-2">

                    <div class="form-check">

                        <input class="form-check-input stat-red"
                               type="checkbox"
                               ${redCard ? "checked" : ""}>

                        <label class="form-check-label">
                            Tarjeta roja
                        </label>

                    </div>

                </div>


                <div class="col-12 col-md-1 text-md-end">

                    <button type="button"
                            class="btn btn-outline-danger
                                   btn-sm remove-player"
                            title="Eliminar jugador">

                        <i class="bi bi-trash"></i>

                    </button>

                </div>

            </div>
        `;


    container.appendChild(row);


    /*
     * Give the new row its correct
     * playerStats[index] names.
     */

    updateIndexes();


    /*
     * Update the select so this player
     * cannot be selected again.
     */

    updatePlayerOptions();


    updateCounter();

    updateEmptyMessage();


    /*
     * Reset inputs.
     */

    playerSelect.value = "";

    goalsInput.value = 0;

    yellowCardsInput.value = 0;

    redCardInput.checked = false;

});


/*
 * ============================================================
 * REMOVE PLAYER
 * ============================================================
 */

container.addEventListener("click", function (event) {

    const button =
        event.target.closest(".remove-player");


    if (!button) {
        return;
    }


    const row =
        button.closest(".player-stat");


    if (!row) {
        return;
    }


    row.remove();


    /*
     * Rebuild indexes:
     *
     * playerStats[0]
     * playerStats[1]
     * playerStats[2]
     *
     * etc.
     */

    updateIndexes();


    updatePlayerOptions();

    updateCounter();

    updateEmptyMessage();

});


/*
 * ============================================================
 * UPDATE INPUT NAMES
 * ============================================================
 *
 * This is the important part for Spring MVC.
 *
 * The generated HTML becomes:
 *
 * playersStats[0].playerId
 * playersStats[0].minute
 * playersStats[0].goals
 * playersStats[0].yellowCards
 * playersStats[0].redCard
 *
 * playersStats[1].playerId
 * playersStats[1].minute
 * ...
 */

function updateIndexes() {

    const rows =
        container.querySelectorAll(".player-stat");


    rows.forEach(function (row, index) {

        const playerId =
            row.dataset.playerId;


        /*
         * PLAYER ID
         */

        const playerIdInput =
            row.querySelector(".stat-player-id")
            ||
            row.querySelector(
                'input[type="hidden"]'
            );


        if (playerIdInput) {

            playerIdInput.name =
                `playersStats[${index}].playerId`;

            playerIdInput.value =
                playerId;

        }

        /*
         * GOALS
         */

        const goals =
            row.querySelector(".stat-goals");

        if (goals) {

            goals.name =
                `playersStats[${index}].goals`;

        }


        /*
         * YELLOW CARDS
         */

        const yellow =
            row.querySelector(".stat-yellow");

        if (yellow) {

            yellow.name =
                `playersStats[${index}].yellowCards`;

        }


        /*
         * RED CARD
         */

        const red =
            row.querySelector(".stat-red");

        if (red) {

            red.name =
                `playersStats[${index}].redCard`;

        }

    });

}


/*
 * ============================================================
 * UPDATE PLAYER SELECT
 * ============================================================
 *
 * Disable players that have already been added.
 */

function updatePlayerOptions() {

    const rows =
        container.querySelectorAll(".player-stat");


    const selectedPlayers =
        new Set();


    rows.forEach(function (row) {

        selectedPlayers.add(
            row.dataset.playerId
        );

    });


    Array.from(
        playerSelect.options
    ).forEach(function (option) {

        if (!option.value) {
            return;
        }

        option.disabled =
            selectedPlayers.has(option.value);

    });

}


/*
 * ============================================================
 * COUNTER
 * ============================================================
 */

function updateCounter() {

    const count =
        container.querySelectorAll(
            ".player-stat"
        ).length;


    if (count === 1) {

        statsCounter.textContent =
            "1 jugador";

    } else {

        statsCounter.textContent =
            `${count} jugadores`;

    }

}


/*
 * ============================================================
 * EMPTY MESSAGE
 * ============================================================
 */

function updateEmptyMessage() {

    const count =
        container.querySelectorAll(
            ".player-stat"
        ).length;


    if (count === 0) {

        emptyStatsMessage.classList.remove(
            "d-none"
        );

    } else {

        emptyStatsMessage.classList.add(
            "d-none"
        );

    }

}


/*
 * ============================================================
 * ESCAPE HTML
 * ============================================================
 *
 * Prevents player names from being interpreted as HTML.
 */

function escapeHtml(value) {

    const div =
        document.createElement("div");

    div.textContent = value;

    return div.innerHTML;

}