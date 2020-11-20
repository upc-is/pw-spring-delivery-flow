package pe.edu.upc.delivery.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.utils.EstadoPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query("select p from Pedido p where p.cliente.id = ?1 and p.estadoPedido = ?2")
	Optional<Pedido> findByClienteAndEstadoPedido(Integer id, EstadoPedido estadoPedido) throws Exception;
}
