package com.devops.microservicesdocker.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devops.microservicesdocker.models.entity.Cliente;

@Repository("clientRepository")
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	//Obtener un cliente específico, según el vendedor
	@Query(value="SELECT * FROM `cliente` WHERE id=:id AND vendedor_id=:vendedor_id", nativeQuery = true)
	public Cliente findOneByIdVendedor (String id, String vendedor_id);
	
	//Obtener todos los clientes de un vendedor
	@Query(value="SELECT * FROM `cliente` WHERE vendedor_id=:vendedor_id", nativeQuery = true)
	public List<Cliente> findAllByIdVendedor (String vendedor_id);
	
	public Cliente findByDni(String dni);
	
	public Cliente findByCorreo(String correo);
	
	
}
