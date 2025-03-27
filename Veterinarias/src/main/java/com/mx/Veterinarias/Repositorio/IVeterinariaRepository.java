package com.mx.Veterinarias.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Veterinarias.Modelo.Veterinaria;

@Repository
public interface IVeterinariaRepository extends JpaRepository<Veterinaria, Integer> {

}
