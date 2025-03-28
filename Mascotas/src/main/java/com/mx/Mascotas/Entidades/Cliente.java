package com.mx.Mascotas.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CLIENTES")
@Data
public class Cliente {
	@Id
    @Column(name = "ID_CLIENTE", columnDefinition = "INT")
    private int idCliente;

    @Column(name = "NOMBRE", columnDefinition = "NVARCHAR(50)")
    private String nombre;

    @Column(name = "DIRECCION", columnDefinition = "NVARCHAR(100)")
    private String direccion;

    @Column(name = "CONTACTO", columnDefinition = "NVARCHAR(20)")
    private String contacto;

    public Cliente() {
    }
}
