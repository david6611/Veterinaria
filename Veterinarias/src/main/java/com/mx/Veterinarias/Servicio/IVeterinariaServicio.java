package com.mx.Veterinarias.Servicio;

import java.util.List;

import com.mx.Veterinarias.Modelo.Veterinaria;

public interface IVeterinariaServicio {
	 List<Veterinaria> listar();

	    void guardar(Veterinaria veterinaria);

	    Veterinaria buscar(int idVeterinaria);

	    void editar(Veterinaria veterinaria);

	    void eliminar(int idVeterinaria);

}
