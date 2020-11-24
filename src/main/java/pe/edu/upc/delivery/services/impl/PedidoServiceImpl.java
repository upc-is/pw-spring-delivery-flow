package pe.edu.upc.delivery.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.models.repositories.PedidoRepository;
import pe.edu.upc.delivery.services.PedidoService;
import pe.edu.upc.delivery.utils.EstadoPedido;

@Service
public class PedidoServiceImpl implements PedidoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	@Override
	public Pedido save(Pedido entity) throws Exception {
		return pedidoRepository.save(entity);
	}

	@Transactional
	@Override
	public Pedido update(Pedido entity) throws Exception {
		return pedidoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		pedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pedido> findAll() throws Exception {
		return pedidoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pedido> findById(Integer id) throws Exception {
		return pedidoRepository.findById(id);
	}

	@Override
	public List<Pedido> findByClienteAndEstadoPedido(Integer id, EstadoPedido estadoPedido) throws Exception {
		return pedidoRepository.findByClienteAndEstadoPedido(id, estadoPedido);
	}
	
}
