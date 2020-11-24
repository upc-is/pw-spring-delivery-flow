package pe.edu.upc.delivery.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.utils.EstadoPedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
	@Query("select d from DetallePedido d where d.proveedor.id = ?1 and d.pedido.estadoPedido <> ?2")
	List<DetallePedido> findByProveedorAndNotEstadoPedido(Integer id, EstadoPedido estadoPedido) throws Exception;
}
