class Materia{

    constructor(id, nombre, NRC){
        this.id = id;
        this.nombre = nombre;
        this.NRC = NRC;
        Object.seal(this);
    }

    //2. A json
    toJson(){
        return JSON.stringify(this);
    }

}