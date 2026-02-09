document.addEventListener("DOMContentLoaded", () => {

    const btnMenu = document.getElementById("btn-menu");
    const flowItems = document.querySelectorAll("#menu .flow");

    const btnCalendar = document.getElementById("btn-up-down");
    const menuUpDown = document.getElementById("menu-up-down");
    const imgUpDown = document.getElementById("img-up-down");

    /* ---------- helper function ---------- */
    function isVisible(el) {
        return window.getComputedStyle(el).display !== "none";
    }

    function showElement(el) {
        el.style.display = "flex"; // само display, без max-height
    }

    function hideElement(el) {
        el.style.display = "none";
    }

    /* ---------- MOBILE MAIN MENU ---------- */
    if (btnMenu) {
        btnMenu.addEventListener("click", e => {
            e.preventDefault();

            flowItems.forEach(item => {
                if (isVisible(item)) {
                    hideElement(item);
                } else {
                    showElement(item);
                }
            });

            // ❌ Ако подменюто е отворено, да се затвори автоматично
            if (menuUpDown && isVisible(menuUpDown)) {
                hideElement(menuUpDown);
                imgUpDown.src = "/pictures/nav/down.png";
            }
        });
    }

    /* ---------- CALENDAR SUBMENU (DESKTOP + MOBILE) ---------- */
    if (btnCalendar && menuUpDown) {
        btnCalendar.addEventListener("click", e => {
            e.preventDefault();

            if (isVisible(menuUpDown)) {
                hideElement(menuUpDown);
                imgUpDown.src = "/pictures/nav/down.png";
            } else {
                showElement(menuUpDown);
                imgUpDown.src = "/pictures/nav/up.png";
            }
        });
    }

    /* ---------- RESIZE SYNC ---------- */
    window.addEventListener("resize", () => {
        if (window.innerWidth >= 950) {
            // десктоп: показваме всички елементи
            flowItems.forEach(item => {
                showElement(item);
            });
            hideElement(menuUpDown);
            imgUpDown.src = "/pictures/nav/down.png";
        } else {
            // мобилно: скриваме елементите по подразбиране
            flowItems.forEach(item => {
                hideElement(item);
            });
            hideElement(menuUpDown);
            imgUpDown.src = "/pictures/nav/down.png";
        }
    });
});
