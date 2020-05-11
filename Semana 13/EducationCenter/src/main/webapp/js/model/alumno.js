class Alumno{

    constructor(id, nombre, codigo){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        Object.seal(this);
    }

    //2. A json
    toJson(){
        return JSON.stringify(this);
    }

}