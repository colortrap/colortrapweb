document.addEventListener("DOMContentLoaded", () => {

    const btnMenu = document.getElementById("btn-menu");
    const flowItems = document.querySelectorAll("#menu .flow");

    const btnCalendar = document.getElementById("btn-up-down");
    const menuUpDown = document.getElementById("menu-up-down");
    const imgUpDown = document.getElementById("img-up-down");

    /* ---------- helper functions ---------- */
    function slideDown(el) {
        el.style.display = "flex";
        el.style.overflow = "hidden";
        el.style.maxHeight = el.scrollHeight + "px";
        el.style.transition = "max-height 0.3s ease";
    }

    function slideUp(el) {
        el.style.overflow = "hidden";
        el.style.maxHeight = "0";
        el.style.transition = "max-height 0.3s ease";
        setTimeout(() => {
            el.style.display = "none";
        }, 300);
    }

    function isVisible(el) {
        return window.getComputedStyle(el).display !== "none";
    }

    /* ---------- MOBILE MAIN MENU ---------- */
    if (btnMenu) {
        btnMenu.addEventListener("click", e => {
            e.preventDefault();

            flowItems.forEach(item => {
                if (isVisible(item)) {
                    slideUp(item);
                } else {
                    slideDown(item);
                }
            });
        });
    }

    /* ---------- CALENDAR SUBMENU (DESKTOP + MOBILE) ---------- */
    if (btnCalendar && menuUpDown) {
        btnCalendar.addEventListener("click", e => {
            e.preventDefault();

            if (isVisible(menuUpDown)) {
                slideUp(menuUpDown);
                imgUpDown.src = "/pictures/nav/down.png";
            } else {
                slideDown(menuUpDown);
                imgUpDown.src = "/pictures/nav/up.png";
            }
        });
    }

    /* ---------- RESIZE SYNC ---------- */
    window.addEventListener("resize", () => {
        if (window.innerWidth >= 950) {
            flowItems.forEach(item => {
                item.style.display = "flex";
                item.style.maxHeight = "none";
            });
            slideUp(menuUpDown);
            imgUpDown.src = "/pictures/nav/down.png";
        } else {
            flowItems.forEach(item => {
                item.style.display = "none";
                item.style.maxHeight = "0";
            });
            slideUp(menuUpDown);
            imgUpDown.src = "/pictures/nav/down.png";
        }
    });
});
