package com.devops.microservicesdocker.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	
	@Id // Columna primaria de la base de datos
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
	private String id;

	@Column(name = "dni", nullable = false, length = 10)
	private String dni;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 45)
	private String apellido;
	
	@Column(name = "direccion", nullable = false, length = 150)
	private String direccion;
	
	@Column(name = "correo", nullable = false, length = 45)
	private String correo;
	
	@Column(name = "vendedor_id", nullable = false, columnDefinition = "VARCHAR(255)")
	private String vendedor_id;
	
	@Column(name="creado",nullable = false,length = 45)
    private Date creado;
	
	public Cliente() {
		
	}

	public Cliente(String id, String dni, String nombre, String apellido, String direccion, String correo,
			String vendedor_id,Date creado) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.vendedor_id = vendedor_id;
		this.creado = creado;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getVendedor_id() {
		return vendedor_id;
	}

	public void setVendedor_id(String vendedor_id) {
		this.vendedor_id = vendedor_id;
	}

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}



	private static final long serialVersionUID = 1L;
}
