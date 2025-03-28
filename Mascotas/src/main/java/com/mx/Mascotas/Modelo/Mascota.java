package com.mx.Mascotas.Modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="MASCOTAS")
@Data
public class Mascota {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASCOTAS_SEQ")
    @SequenceGenerator(name = "MASCOTAS_SEQ", sequenceName = "MASCOTAS_SEQ", allocationSize = 1)
    @Column(name = "ID_MASCOTA")
    private int idMascota;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 15, message = "El nombre no puede tener más de 15 caracteres")
    @Column(name = "NOMBRE", length = 15, nullable = false)
    private String nombre;

    @NotBlank(message = "La raza no puede estar vacía")
    @Size(max = 15, message = "La raza no puede tener más de 15 caracteres")
    @Column(name = "RAZA", length = 15, nullable = false)
    private String raza;

    @NotNull(message = "La edad no puede estar vacía")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Column(name = "EDAD", nullable = false)
    private int edad;

    @NotNull(message = "La fecha de cita no puede estar vacía")
    @Column(name = "FECHA_CITA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCita;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "CLIENTE_ID", nullable = false)
    private int clienteId;

    @NotNull(message = "El ID del responsable es obligatorio")
    @Column(name = "RESPONSABLE_ID", nullable = false)
    private int responsableId;

    @NotNull(message = "El ID de la veterinaria es obligatorio")
    @Column(name = "VETERINARIA_ID", nullable = false)
    private int veterinariaId;

    public Mascota() {
    }
}
