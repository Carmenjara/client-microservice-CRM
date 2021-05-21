package com.devops.microservicesdocker.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devops.microservicesdocker.models.dao.ClienteRepository;
import com.devops.microservicesdocker.models.entity.Cliente;

@Service("clientServiceImpl")
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return repo.save(cliente);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOneByIdVendedor(String id, String vendedor_id) {
		return repo.findOneByIdVendedor(id, vendedor_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllByIdVendedor(String vendedor_id) {
		return repo.findAllByIdVendedor(vendedor_id);
	}

	@Override
	public Cliente findByDni(String dni) {
		return repo.findByDni(dni);
	}

	@Override
	public Cliente findByCorreo(String correo) {
		return repo.findByCorreo(correo);
	}

}
