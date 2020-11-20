package pe.edu.upc.delivery.services;

import java.util.Optional;

import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.utils.EstadoPedido;

public interface PedidoService extends CrudService<Pedido, Integer> {
	Optional<Pedido> findByClienteAndEstadoPedido(Integer id, EstadoPedido estadoPedido) throws Exception;
}
