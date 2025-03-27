package com.mx.Cliente.Servicio;

import java.util.List;

import com.mx.Cliente.Modelo.Cliente;

public interface IClientesServicio {
	List<Cliente> listar();

    void guardar(Cliente cliente);

    Cliente buscar(int idCliente);

    void editar(Cliente cliente);

    void eliminar(int idCliente);

}
