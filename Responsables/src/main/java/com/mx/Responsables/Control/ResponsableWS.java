package com.mx.Responsables.Control;

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
import com.mx.Responsables.Modelo.Responsable;
import com.mx.Responsables.Servicio.ResponsableServiceImp;

@RestController
@RequestMapping(path= "/R") //http://localhost:8002/R
@CrossOrigin
public class ResponsableWS {
	@Autowired
    private ResponsableServiceImp service;

	@GetMapping
    public ResponseEntity<?> listar() {
        List<Responsable> responsable = service.listar();
        if (responsable.isEmpty()) {
            ApiResponse apiResponse = new ApiResponse("No existen registros en esta base de datos.", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } else {
            return ResponseEntity.ok(responsable);
        }
    }

	@PostMapping
    public ResponseEntity<?> guardar(@RequestBody Responsable responsable) {
        service.guardar(responsable);
        // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
        ApiResponse response = new ApiResponse("Se agregó exitosamente.", true);
        return ResponseEntity.ok(response); // Esto enviará un JSON válido
    }
//Responsabe por Id
    @GetMapping("/{id}")
    public ResponseEntity<Responsable> buscar(@PathVariable int id) {
        Responsable responsable = service.buscar(id);
        if (responsable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responsable);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Responsable responsable) {
        Responsable resp = service.buscar(responsable.getIdResponsable());
        if (resp != null) {
            service.editar(responsable);
            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
            ApiResponse response = new ApiResponse("El registro se editó con éxito.", true);
            return ResponseEntity.ok(response); // Esto enviará un JSON válido
        }
        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
        ApiResponse response = new ApiResponse("El registro no existe.", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{idResponsable}")
    public ResponseEntity<?> eliminar(@PathVariable("idResponsable") int idResponsable) {
        Responsable resp = service.buscar(idResponsable);
        if (resp != null) {
            service.eliminar(idResponsable);
            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
            ApiResponse response = new ApiResponse("El registro se eliminó con éxito.", true);
            return ResponseEntity.ok(response); // Esto enviará un JSON válido
        }
        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
        ApiResponse response = new ApiResponse("El registro no existe.", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/veterinaria/{veterinariaId}")
    public ResponseEntity<List<Responsable>> buscarPorVeterinaria(@PathVariable int veterinariaId) {
        List<Responsable> responsables = service.buscarPorVeterinaria(veterinariaId);
        if (responsables.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responsables);
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
