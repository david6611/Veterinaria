package com.mx.Responsables.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Responsables.Modelo.Responsable;

@Repository
public interface IResponsableRepository extends JpaRepository<Responsable, Integer>{
	List<Responsable> findByVeterinariaId(int veterinariaId);

}
