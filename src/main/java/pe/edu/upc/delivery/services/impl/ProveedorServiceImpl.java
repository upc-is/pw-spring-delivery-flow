package pe.edu.upc.delivery.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.delivery.models.entities.Proveedor;
import pe.edu.upc.delivery.models.repositories.ProveedorRepository;
import pe.edu.upc.delivery.services.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProveedorRepository proveedorRepository; 

	@Transactional
	@Override
	public Proveedor save(Proveedor entity) throws Exception {
		return proveedorRepository.save(entity);
	}

	@Transactional
	@Override
	public Proveedor update(Proveedor entity) throws Exception {
		return proveedorRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		proveedorRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Proveedor> findAll() throws Exception {
		return proveedorRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Proveedor> findById(Integer id) throws Exception {
		return proveedorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Proveedor> findByNombre(String nombre) throws Exception {
		return proveedorRepository.findByNombreContaining(nombre);
	}

}
