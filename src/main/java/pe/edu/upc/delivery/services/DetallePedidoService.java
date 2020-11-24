package pe.edu.upc.delivery.services;

import java.util.List;

import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.utils.EstadoPedido;

public interface DetallePedidoService extends CrudService<DetallePedido, Integer>{
	List<DetallePedido> findByProveedorAndNotEstadoPedido(Integer id, EstadoPedido estadoPedido) throws Exception;
}
