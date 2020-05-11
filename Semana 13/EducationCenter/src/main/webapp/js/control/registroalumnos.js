const titulo = document.getElementById("titulo");
const idIT = document.getElementById("idIT");
const nombreIT = document.getElementById("nombreIT");
const codigoIT = document.getElementById("codigoIT");
const registroBTN = document.getElementById("registroBTN");
const alumnosContenedor = document.getElementById("alumnosContenedor");

const localStorage = window.localStorage;

var alumnos = [];
var usuario = {};


registroBTN.addEventListener("click", registrar);


function registrar() {

    var id = idIT.value;
    var nombre = nombreIT.value;
    var codigo = codigoIT.value;


    //3. Uso de JSON
    var alumno = new Alumno(id, nombre, codigo);
    var json = alumno.toJson();
    console.log(json);

    //4. Enviar datos
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function(response){
        reloadList();
    };
    xhr.open("POST", "api/estudiante/insert");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(json);

}

//5. Load list
function realoadList(){
    alumnosContenedor.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        console.log(xhr.responseText);
        //7. parsear json
        var objects = JSON.parse(xhr.responseText);
        console.log(objects);
        for(let i=0 ; i<objects.length ; i++){
            var nAlumno = objects[i];
            createLinkAlumno(nAlumno);
        }
    };
    xhr.open("GET","api/estudiante/getall");
    xhr.send();
}

//6. Add listener al inicio
document.addEventListener("DOMContentLoaded",function () {
    realoadList();
});

//8. Crear funcion
function createLinkAlumno(nAlumno) {
    var nParrafo = document.createElement("p");
    var nEnlace = document.createElement("a");
    nEnlace.href = "#";
    nEnlace.id = nAlumno.id;
    nEnlace.innerHTML = nAlumno.nombre;
    nParrafo.appendChild(nEnlace);
    alumnosContenedor.appendChild(nParrafo);

    //9. Agregar listener
    var HTMLenlace = document.getElementById(nAlumno.id);
    HTMLenlace.addEventListener("click", function (event) {
        event.preventDefault();

        localStorage.setItem("id", nAlumno.id);
        window.location.href = "alumnoIndex.html";
    });

}