document.addEventListener( "DOMContentLoaded", init);

const messageBox = document.getElementById("messageBox");
const messageBtn = document.getElementById("messageBtn");
const messageContainer = document.getElementById("messageContainer");
const socket = new WebSocket("ws://localhost:8080/EducationCenter/server");

function init(){

    socket.onopen = function(){
        socket.send("Nuevo usuario conectado");
    };

    socket.onmessage = function( event ){
        let recibido = event.data.toString();
        messageContainer.innerHTML += "<p>" + recibido + "</p>";
    };

    socket.onclose = function(){

    };

    messageBtn.addEventListener("click", ()=>{
        let msg = messageBox.value;
        messageBox.value = "";
        socket.send(msg);
    });

    window.onbeforeunload = function(){
        socket.close();
    };

}