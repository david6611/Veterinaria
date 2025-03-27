package com.mx.Cliente.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Cliente.Modelo.Cliente;
import com.mx.Cliente.Repositorio.IClientesRepository;

@Service
public class ClientesServiceImp implements IClientesServicio{
	@Autowired
    private IClientesRepository dao;

    @Override
    public List<Cliente> listar() {
        return dao.findAll(Sort.by(Sort.Direction.ASC, "idCliente"));
    }

    @Override
    public void guardar(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    public Cliente buscar(int idCliente) {
        return dao.findById(idCliente).orElse(null);
    }

    @Override
    public void editar(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    public void eliminar(int idCliente) {
        dao.deleteById(idCliente);
    }
}
