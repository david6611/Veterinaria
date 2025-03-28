package com.mx.Mascotas.Control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Mascotas.Entidades.Cliente;
import com.mx.Mascotas.Entidades.Responsable;
import com.mx.Mascotas.Entidades.Veterinaria;
import com.mx.Mascotas.Modelo.Mascota;
import com.mx.Mascotas.Service.MascotaServiceImp;


@RestController
@RequestMapping(path="/M") //http://localhost:8003/M
@CrossOrigin
public class MascotaWS {
	 @Autowired
	    private MascotaServiceImp service;

	 @GetMapping
	    public ResponseEntity<?> listar() {
	        List<Mascota> mascotas = service.listar();
	        if (mascotas.isEmpty()) {
	            ApiResponse apiResponse = new ApiResponse("No existen registros en esta base de datos.", false);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	        } else {
	            return ResponseEntity.ok(mascotas);
	        }
	    }

	 @PostMapping
	    public ResponseEntity<?> guardar(@RequestBody Mascota mascota) {
	        service.guardar(mascota);
	        // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
	        ApiResponse response = new ApiResponse("Se agregó exitosamente.", true);
	        return ResponseEntity.ok(response); // Esto enviará un JSON válido
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Mascota> buscar(@PathVariable int id) {
	        Mascota mascota = service.buscar(id);
	        if (mascota == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(mascota);
	    }

	    @PutMapping
	    public ResponseEntity<?> editar(@RequestBody Mascota mascota) {
	        Mascota masc = service.buscar(mascota.getIdMascota());
	        if (masc != null) {
	            service.editar(mascota);
	            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
	            ApiResponse response = new ApiResponse("El registro se editó con éxito.", true);
	            return ResponseEntity.ok(response); // Esto enviará un JSON válido
	        }
	        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
	        ApiResponse response = new ApiResponse("El registro no existe.", false);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    @DeleteMapping("/{idMascota}")
	    public ResponseEntity<?> eliminar(@PathVariable("idMascota") int idMascota) {
	        Mascota masc = service.buscar(idMascota);
	        if (masc != null) {
	            service.eliminar(idMascota);
	            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
	            ApiResponse response = new ApiResponse("El registro se eliminó con éxito.", true);
	            return ResponseEntity.ok(response); // Esto enviará un JSON válido
	        }
	        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
	        ApiResponse response = new ApiResponse("El registro no existe.", false);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	    
	    //Cliente con RestTemplate 
	  //endpoint para listar Cliente
	    @GetMapping("/cliente/{id}")
	    public ResponseEntity<?> obtenerCliente(@PathVariable int id) {
	        try {
	            // Llamar al servicio para obtener el cliente con el ID proporcionado
	            Cliente cliente = service.obtenerCliente(id);

	            // Verificar si el cliente fue encontrado
	            if (cliente == null) {
	                return ResponseEntity.notFound().build();  // Retornar 404 si no se encuentra el cliente
	            }

	            return ResponseEntity.ok(cliente);  // Retornar el cliente con un código 200

	        } catch (Exception e) {
	            // Si ocurre algún error, devolver un mensaje de error
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Ocurrió un error al procesar la solicitud: " + e.getMessage());
	        }
	    }
	        
	        //Responsable con RestTemplate
	      //endpoint para listar Responsable
		    @GetMapping("/responsable/{id}")
		    public ResponseEntity<?> obtenerResponsable(@PathVariable int id) {
		        try {
		            // Llamar al servicio para obtener el cliente con el ID proporcionado
		            Responsable responsable = service.obtenerResponsable(id);

		            // Verificar si el cliente fue encontrado
		            if (responsable == null) {
		                return ResponseEntity.notFound().build();  // Retornar 404 si no se encuentra el cliente
		            }

		            return ResponseEntity.ok(responsable);  // Retornar el cliente con un código 200

		        } catch (Exception e) {
		            // Si ocurre algún error, devolver un mensaje de error
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                    .body("Ocurrió un error al procesar la solicitud: " + e.getMessage());
		        }
	    
	    }
		    
		    //Veterinaria RestTemplate
		    @GetMapping("/veterinaria/{id}")
		    public ResponseEntity<?> obtenerVeterinaria(@PathVariable int id) {
		        try {
		            // Llamar al servicio para obtener el cliente con el ID proporcionado
		            Veterinaria veterinaria = service.obtenerVeterinaria(id);

		            // Verificar si el cliente fue encontrado
		            if (veterinaria == null) {
		                return ResponseEntity.notFound().build();  // Retornar 404 si no se encuentra el cliente
		            }

		            return ResponseEntity.ok(veterinaria);  // Retornar el cliente con un código 200

		        } catch (Exception e) {
		            // Si ocurre algún error, devolver un mensaje de error
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                    .body("Ocurrió un error al procesar la solicitud: " + e.getMessage());
		        }
	    
	    }


	    
	    public class ApiResponse {
	        private String message;
	        private boolean success;

	        public ApiResponse(String message, boolean success) {
	            this.message = message;
	            this.success = success;
	        }

	        public String getMessage() {
	            return message;
	        }

	        public void setMessage(String message) {
	            this.message = message;
	        }

	        public boolean isSuccess() {
	            return success;
	        }

	        public void setSuccess(boolean success) {
	            this.success = success;
	        }
	    }
}
