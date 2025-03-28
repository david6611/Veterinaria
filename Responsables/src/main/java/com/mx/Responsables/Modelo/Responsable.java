package com.mx.Responsables.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "RESPONSABLES")
@Data
public class Responsable {

    @Id
    @Column(name = "ID_RESPONSABLE")
    private int idResponsable; // Cambiado a Integer para manejar nulos si es necesario

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "CONTRATO", length = 50) // Cambiado a String porque es NVARCHAR2 en la base de datos
    private String contrato;

    @Column(name = "VETERINARIA_ID")
    private int veterinariaId; // Cambiado a Integer para manejar nulos si es necesario

    public Responsable() {
    }
}


