const titulo = document.getElementById("titulo");
const alumnoNombre = document.getElementById("alumnoNombre");

const matid = document.getElementById("matid");
const matnombre = document.getElementById("matnombre");
const matnrc = document.getElementById("matnrc");
const registrarBTN = document.getElementById("registrarBTN");
const materiasContenedor = document.getElementById("materiasContenedor");
const agregarBTN = document.getElementById("agregarBTN");
const matriculasContenedor = document.getElementById("matriculasContenedor");

const localStorage = window.localStorage;

//Recuperar info
const estid = localStorage.getItem("id");

document.addEventListener("DOMContentLoaded", init);

//10. Consultar usuario por ID
function init() {
    var xhr1 = new XMLHttpRequest();
    xhr1.onloadend = function () {
        console.log(xhr1.responseText);
        var object = JSON.parse(xhr1.responseText);
        console.log(object);
        titulo.innerHTML = object.nombre;
        alumnoNombre.innerHTML = object.nombre;
        loadMaterias();
    };
    xhr1.open("GET", "api/estudiante/byid/" + estid);
    xhr1.send();

}

//11. Load materias
function loadMaterias() {
    materiasContenedor.innerHTML = "";

    var xhr2 = new XMLHttpRequest();
    xhr2.onloadend = function () {
        console.log(xhr2.responseText);
        var objects = JSON.parse(xhr2.responseText);
        console.log(objects);
        //12. Create Options
        for (let i = 0; i < objects.length; i++) {
            let nMateria = objects[i];
            let option = document.createElement("option");
            option.value = nMateria.id;
            option.innerHTML = nMateria.nombre;
            materiasContenedor.appendChild(option);
        }

        loadMatriculas();

    };
    xhr2.open("GET", "api/materias/getall");
    xhr2.send();
}

//13. Registrar materias
registrarBTN.addEventListener("click", function () {
    var id = matid.value;
    var nombre = matnombre.value;
    var nrc = matnrc.value;
    var materia = new Materia(id, nombre, nrc);

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function (response) {
        loadMaterias();
    };
    xhr.open("POST", "api/materias/insert");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(materia.toJson());
});

//14. agregarBTN
agregarBTN.addEventListener("click", function () {
    var matID = materiasContenedor.value;
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function (response) {
        loadMatriculas();
    };
    xhr.open("POST", "api/materias/register/" + matID + "/" + estid);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
});

function loadMatriculas(){
    matriculasContenedor.innerHTML = "";

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        console.log(xhr.responseText);
        var objects = JSON.parse(xhr.responseText);
        console.log(objects);
        //12. Create Options
        for (let i = 0; i < objects.length; i++) {
            let nMateria = objects[i];
            let li = document.createElement("li");
            li.innerHTML = nMateria.nombre;
            matriculasContenedor.appendChild(li);
        }
    };
    xhr.open("GET", "api/estudiante/list/"+estid);
    xhr.send();
}