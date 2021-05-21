package com.devops.microservicesdocker.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devops.microservicesdocker.models.entity.Cliente;
import com.devops.microservicesdocker.models.service.ClienteServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/devops/microservice/clients")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ClienteRestController {

	// http://localhost:5000/swagger-ui.html#/cliente-rest-controller/getClientsUsingGET

	@Autowired
	private ClienteServiceImpl service;

	private Cliente objWithDni;
	private Cliente objWithCorreo;
	private Cliente objCli;

	// Listar todos los clientes, independiente del vendedor
	@GetMapping("/list")
	@ApiOperation("Get all clients, without vendor dependency")
	public List<Cliente> getClients() {
		return service.findAll();
	}

	// Obtener cliente por id, general
	@GetMapping("/{id}")
	@ApiOperation("Get a client by id, without vendor dependency")
	public ResponseEntity<Cliente> findById(@PathVariable(value = "id") String id) {
		Cliente client = service.findById(id);
		if (client == null) {
			return new ResponseEntity<Cliente>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}

	// Obtener cliente por id, de acuerdo al id de vendedor
	@GetMapping("/myClient/{id}/{vendedor_id}")
	@ApiOperation("Get a client by id, with vendor dependency")
	public ResponseEntity<Cliente> findOneByIdVendedor(@PathVariable(value = "id") String id,
			@PathVariable(value = "vendedor_id") String vendedor_id) {
		Cliente client = service.findOneByIdVendedor(id, vendedor_id);
		if (client == null) {
			return new ResponseEntity<Cliente>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}

	// Obtener todos los clientes de un vendedor
	@GetMapping("/myClients/{vendedor_id}")
	@ApiOperation("Get all clients, with vendor dependency")
	public List<Cliente> findAllByIdVendedor(@PathVariable(value = "vendedor_id") String vendedor_id) {
		return service.findAllByIdVendedor(vendedor_id);

	}

	// Dar de alta cliente
	@PostMapping("/save")
	@ApiOperation("Create new client")
	public ResponseEntity<?> saveClient(@RequestBody Cliente client) {

		objWithDni = service.findByDni(client.getDni());
		objWithCorreo = service.findByCorreo(client.getCorreo());

		if (objWithDni == null && objWithCorreo == null) {

			client.setCreado(new Date());
			service.save(client);
			return new ResponseEntity<Cliente>(client, HttpStatus.OK);
		}
		
		System.out.println("Dni o Correo ya registrados!");
		return new ResponseEntity<>("Dni o Correo ya registrados!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Actualizar cliente
	@PutMapping("/update/{id}")
	@ApiOperation("Update client by id")
	public ResponseEntity<?> update(@PathVariable String id, @Validated @RequestBody Cliente client) {
		objCli = service.findById(client.getId());

		objWithDni = service.findByDni(client.getDni());

		objWithCorreo = service.findByCorreo(client.getCorreo());

		client.setCreado(new Date());

		/*
		 * if(objWithDni==null) { throw new
		 * Error("El DNI "+client.getDni()+" no está registrado en la base de datos"); }
		 * 
		 * if(objWithCorreo==null) { throw new Error("El correo "+client.getCorreo()
		 * +" no está registrado en la base de datos"); }
		 */

		System.out.println(objCli.getDni());
		// System.out.println(objWithDni.getDni());
		System.out.println(objWithCorreo);
		System.out.println(objWithDni);

		if (segundaEvaluacion(client) == true) {
			return new ResponseEntity<Cliente>(client, HttpStatus.OK);
		} else if (primeraEvaluacion(client) == true) {
			return new ResponseEntity<Cliente>(client, HttpStatus.OK);
		}

		System.out.println("Dni o Correo ya registrados! No se puede actualizar");
		return new ResponseEntity<>("Dni o Correo ya registrados! No se puede actualizar",
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public boolean primeraEvaluacion(Cliente client) {
		// no funciona
		if ((objCli.getDni() == objWithDni.getDni()) && objWithCorreo == null) {
			service.save(client);
			return true;
			// funciona
		} else if (objCli.getDni() == objWithDni.getDni() && objCli.getCorreo() == objWithCorreo.getCorreo()) {
			service.save(client);
			return true;

		}
		return false;
	}

	public boolean segundaEvaluacion(Cliente client) {
		// funciona
		if (objWithDni == null && objWithCorreo == null) {
			service.save(client);
			return true;
			// funciona
		} else if ((objCli.getCorreo() == objWithCorreo.getCorreo()) && objWithDni == null) {
			service.save(client);
			return true;

		}
		return false;
	}

	// Dar de baja un cliente
	@DeleteMapping("/delete/{id}")
	@ApiOperation("Delete client by id")
	public void deleteClient(@PathVariable String id) {
		Cliente obj = service.findById(id);
		if (obj != null) {
			service.deleteById(id);
		} else {
			throw new Error("El Id " + id + " no existe");
		}
	}
}
