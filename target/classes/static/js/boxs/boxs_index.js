function toggleDropdownMonth() {
    const itemsElement = document.getElementById("monthItems");
    itemsElement.classList.toggle("hidden");
}

function onSelectedMonth(val, id) {
    const monthNameSelectedElement = document.getElementById("monthNameSelected");
    const itemsElement = document.getElementById("monthItems");
    const radioElement = document.getElementById(id);

    monthNameSelectedElement.innerText = val;
    radioElement.checked = true;
    itemsElement.classList.toggle("hidden");
}

function toggleDropdownYear() {
    const itemsElement = document.getElementById("yearItems");
    itemsElement.classList.toggle("hidden");
}

function onSelectedYear(val, id) {
    const yearNameSelectedElement = document.getElementById("yearNameSelected");
    const itemsElement = document.getElementById("yearItems");
    const radioElement = document.getElementById(id);

    yearNameSelectedElement.innerText = val;
    radioElement.checked = true;
    itemsElement.classList.toggle("hidden");
}