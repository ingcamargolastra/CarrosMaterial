package com.edu.unimagdalena.carros;

class Carro {
    private String id;
    private int foto;
    private String placa;
    private String marca;
    private String modelo;
    private String traccion;

    public Carro() {
    }

    public Carro(String id, int foto, String placa, String marca, String modelo, String traccion) {
        this.id = id;
        this.foto = foto;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.traccion = traccion;
    }

    public String getId() {
        return id;
    }

    public int getFoto() {
        return foto;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTraccion(){
        return traccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTraccion(String traccion){
        this.traccion = traccion;
    }

    public void guardar(){
        Datos.agregar(this);
    }

    public void editar(){
        Datos.editar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
