package com.mx.Cliente.Control;

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
import com.mx.Cliente.Modelo.Cliente;
import com.mx.Cliente.Servicio.ClientesServiceImp;

@RestController
@RequestMapping(path="/C")
@CrossOrigin
public class ClienteWS {
	 @Autowired
	    private ClientesServiceImp service;

	  @GetMapping
	    public ResponseEntity<?> listar() {
	        List<Cliente> clientes = service.listar();
	        if (clientes.isEmpty()) {
	            ApiResponse apiResponse = new ApiResponse("No existen registros en esta base de datos.", false);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	        } else {
	            return ResponseEntity.ok(clientes);
	        }
	    }
	    
	    
	    //Buscar cliente por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<?> buscar(@PathVariable int id) {
	        Cliente cliente = service.buscar(id);
	        if (cliente == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(cliente);
	    }

	    @PostMapping
	    public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {
	        service.guardar(cliente);
	        // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
	        ApiResponse response = new ApiResponse("Se agregó exitosamente.", true);
	        return ResponseEntity.ok(response); // Esto enviará un JSON válido
	    }

	    @PutMapping
	    public ResponseEntity<?> editar(@RequestBody Cliente cliente) {
	        Cliente clt = service.buscar(cliente.getIdCliente());
	        if (clt != null) {
	            service.editar(cliente);
	            // Cambié la respuesta para devolver un objeto JSON en lugar de solo un string
	            ApiResponse response = new ApiResponse("El registro se editó con éxito.", true);
	            return ResponseEntity.ok(response); // Esto enviará un JSON válido
	        }
	        // Si el cliente no existe, devolvemos un objeto JSON con el mensaje y éxito como falso
	        ApiResponse response = new ApiResponse("El registro no existe.", false);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    @DeleteMapping("/{idCliente}")
	    public ResponseEntity<?> eliminar(@PathVariable("idCliente") int idCliente) {
	        Cliente clt = service.buscar(idCliente);
	        if (clt != null) {
	            service.eliminar(idCliente);
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