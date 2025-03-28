package com.mx.Mascotas.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.Mascotas.Entidades.Cliente;
import com.mx.Mascotas.Entidades.Responsable;
import com.mx.Mascotas.Entidades.Veterinaria;
import com.mx.Mascotas.Modelo.Mascota;
import com.mx.Mascotas.Repositorio.IMascotaRepositorio;

@Service
public class MascotaServiceImp implements IMascotaService {
	@Autowired
	private IMascotaRepositorio dao;
	 @Autowired
	 

	    @Override
	    public List<Mascota> listar() {
	        return dao.findAll();
	    }

	    @Override
	    public void guardar(Mascota mascota) {
	        dao.save(mascota);
	    }

	    @Override
	    public Mascota buscar(int idMascota) {
	        return dao.findById(idMascota).orElse(null);
	    }

	    @Override
	    public void editar(Mascota mascota) {
	        dao.save(mascota);
	    }

	    @Override
	    public void eliminar(int idMascota) {
	        dao.deleteById(idMascota);
	    }
	    
	        @Autowired
	        private IMascotaRepositorio repository;

	        public List<Mascota> listarPorParametros(int clienteId, int responsableId, int veterinariaId) {
	            return repository.findByClienteIdAndResponsableIdAndVeterinariaId(clienteId, responsableId, veterinariaId);
	        }
	        
	        
	        
	        //Metodo para cliente con RestTemplate 
	        @Autowired
	        private RestTemplate restTemplate;
	        public Cliente obtenerCliente(int clienteId) {String url = "http://localhost:8001/C/{id}";  
	            Cliente cliente = restTemplate.getForObject(url, Cliente.class, clienteId);
	            return cliente; 
	        }
	        
	        
	        //Metodo para Responsable 
	        @Autowired
	        private RestTemplate restTemplate1;
	        public Responsable obtenerResponsable(int responsableId) {String url="http://localhost:8002/R/{id}";
	             Responsable responsable = restTemplate1.getForObject(url, Responsable.class, responsableId);
	             return responsable;
	        }
	        
	        //Metodo para Veterinaria
	        @Autowired
	        private RestTemplate restTemplate2;
	        public Veterinaria obtenerVeterinaria(int veterinariaId) {String url="http://localhost:8000/V/{id}";
	             Veterinaria veterinaria = restTemplate1.getForObject(url, Veterinaria.class, veterinariaId);
	             return veterinaria;
	        }


	    }

	


