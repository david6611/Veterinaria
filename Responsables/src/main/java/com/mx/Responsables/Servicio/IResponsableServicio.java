package com.mx.Responsables.Servicio;

import java.util.List;

import com.mx.Responsables.Modelo.Responsable;

public interface IResponsableServicio {
	public List<Responsable> listar();

    public void guardar(Responsable responsable);

    public Responsable buscar(int idResponsable);

    public void editar(Responsable responsable);

    public void eliminar(int idResponsable);

    public List<Responsable> buscarPorVeterinaria(int veterinariaId);

}
