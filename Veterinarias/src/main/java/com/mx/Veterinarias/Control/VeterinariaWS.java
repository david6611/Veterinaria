package com.mx.Veterinarias.Control;

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
import org.springframework.web.bind.annotation.RestController;
import com.mx.Veterinarias.Modelo.Veterinaria;
import com.mx.Veterinarias.Servicio.VeterinariaServiceImp;

@RestController
@RequestMapping(path="/V") //http://localhost:8000/V
@CrossOrigin
public class VeterinariaWS {
	@Autowired
    private VeterinariaServiceImp service;

	 @GetMapping
	    public ResponseEntity<?> listar() {
	        List<Veterinaria> veterinarias = service.listar();
	        if (veterinarias.isEmpty()) {
	            ApiResponse apiResponse = new ApiResponse("No existen registros en esta base de datos.", false);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	        } else {
	            return ResponseEntity.ok(veterinarias);
	        }
	    }
    
    //Buscar Veterinaria por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Veterinaria veterinaria = service.buscar(id);
        if (veterinaria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(veterinaria);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Veterinaria veterinaria) {
        service.guardar(veterinaria);
        // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
        ApiResponse response = new ApiResponse("Se agregó exitosamente.", true);
        return ResponseEntity.ok(response); // Esto enviará un JSON válido
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Veterinaria veterinaria) {
        Veterinaria vet = service.buscar(veterinaria.getIdVeterinaria());
        if (vet != null) {
            service.editar(veterinaria);
            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
            ApiResponse response = new ApiResponse("El registro se editó con éxito.", true);
            return ResponseEntity.ok(response); // Esto enviará un JSON válido
        }
        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
        ApiResponse response = new ApiResponse("El registro no existe.", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{idVeterinaria}")
    public ResponseEntity<?> eliminar(@PathVariable("idVeterinaria") int idVeterinaria) {
        Veterinaria vet = service.buscar(idVeterinaria);
        if (vet != null) {
            service.eliminar(idVeterinaria);
            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
            ApiResponse response = new ApiResponse("El registro se eliminó con éxito.", true);
            return ResponseEntity.ok(response); // Esto enviará un JSON válido
        }
        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
        ApiResponse response = new ApiResponse("El registro no existe.", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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


