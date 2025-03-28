package com.mx.Responsables.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Responsables.Modelo.Responsable;
import com.mx.Responsables.Repositorio.IResponsableRepository;

@Service
public class ResponsableServiceImp implements IResponsableServicio{
	@Autowired
    private IResponsableRepository dao;

    @Override
    public List<Responsable> listar() {
        return dao.findAll(Sort.by(Sort.Direction.ASC, "idResponsable"));
    }

    @Override
    public void guardar(Responsable responsable) {
        dao.save(responsable);
    }

    @Override
    public Responsable buscar(int idResponsable) {
        return dao.findById(idResponsable).orElse(null);
    }

    @Override
    public void editar(Responsable responsable) {
        dao.save(responsable);
    }

    @Override
    public void eliminar(int idResponsable) {
        dao.deleteById(idResponsable);
    }

    @Override
    public List<Responsable> buscarPorVeterinaria(int veterinariaId) {
        return dao.findByVeterinariaId(veterinariaId);
    }

}
