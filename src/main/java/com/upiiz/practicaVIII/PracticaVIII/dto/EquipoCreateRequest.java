package com.upiiz.practicaVIII.PracticaVIII.dto;

public class EquipoCreateRequest {

    private String nombre;
    private String ciudad;
    private Long ligaId; // opcional
    public EquipoCreateRequest(){}
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public String getCiudad(){ return ciudad; }
    public void setCiudad(String ciudad){ this.ciudad = ciudad; }
    public Long getLigaId(){ return ligaId; }
    public void setLigaId(Long ligaId){ this.ligaId = ligaId; }

}
