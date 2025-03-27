package com.mx.Veterinarias.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Veterinarias.Modelo.Veterinaria;
import com.mx.Veterinarias.Repositorio.IVeterinariaRepository;
@Service
public class VeterinariaServiceImp implements IVeterinariaServicio{
	
	@Autowired
	private IVeterinariaRepository dao;

	@Override
	public List<Veterinaria> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idVeterinaria"));
	}

	@Override
	public void guardar(Veterinaria veterinaria) {
		dao.save(veterinaria);
		
	}

	@Override
	public Veterinaria buscar(int idVeterinaria) {
		return dao.findById(idVeterinaria).orElse(null);
	}

	@Override
	public void editar(Veterinaria veterinaria) {
		dao.save(veterinaria);
		
	}

	@Override
	public void eliminar(int idVeterinaria) {
		dao.deleteById(idVeterinaria);
		
	}

}
