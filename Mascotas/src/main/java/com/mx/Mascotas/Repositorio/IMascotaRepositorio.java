package com.mx.Mascotas.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Mascotas.Modelo.Mascota;

public interface IMascotaRepositorio extends JpaRepository<Mascota, Integer>{
	List<Mascota> findByClienteIdAndResponsableIdAndVeterinariaId(int clienteId, int responsableId, int veterinariaId);
}
