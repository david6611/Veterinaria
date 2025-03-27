package com.mx.Cliente.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Cliente.Modelo.Cliente;

public interface IClientesRepository extends JpaRepository<Cliente, Integer>{

}
