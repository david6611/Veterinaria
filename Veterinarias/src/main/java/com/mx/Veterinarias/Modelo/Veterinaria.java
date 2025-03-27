package com.mx.Veterinarias.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Veterinarias")
@Data
public class Veterinaria {
	    @Id
	    @Column(name = "ID_VETERINARIA", columnDefinition = "INT")
	    private int idVeterinaria;

	    @Column(name = "NOMBRE", columnDefinition = "NVARCHAR(50)")
	    private String nombre;

	    @Column(name = "DIRECCION", columnDefinition = "NVARCHAR(100)")
	    private String direccion;

	    @Column(name = "TELEFONO", columnDefinition = "NVARCHAR(20)")
	    private String telefono;

	    public Veterinaria() {

	    }
}
