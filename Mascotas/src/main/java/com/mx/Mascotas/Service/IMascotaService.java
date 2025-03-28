package com.mx.Mascotas.Service;

import java.util.List;

import com.mx.Mascotas.Modelo.Mascota;

public interface IMascotaService {
	public List<Mascota> listar();

    public void guardar(Mascota mascota);

    public Mascota buscar(int idMascota);

    public void editar(Mascota mascota);

    public void eliminar(int idMascota);

}
