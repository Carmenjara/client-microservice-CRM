package com.devops.microservicesdocker.models.service;

import java.util.List;

import com.devops.microservicesdocker.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente findById(String id);
	
	public Cliente save(Cliente cliente);
	
	public void deleteById(String id);
	
	//Obtener cliente específico según el vendedor
	public Cliente findOneByIdVendedor (String id, String vendedor_id);
	
	//Obtener todos los clientes de un vendedor
	public List<Cliente> findAllByIdVendedor (String vendedor_id);
	
	//Obtener Cliente por DNI para que no se repita registro
	public Cliente findByDni(String dni);
	
	//Obtener Cliente por Correo para que no se repita registro
	public Cliente findByCorreo(String correo);
}
