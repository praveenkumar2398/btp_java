package com.howtodoinjava.app.repositories.order;

import com.howtodoinjava.app.model.order.Order;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT c.*, o.*, p.* " + " from Customer c, CustomerOrder o ,Product p "
			+ " where c.id=o.customer_id " + " and o.id=p.customerOrder_id " + " and c.id=?1 ", nativeQuery = true)
	List<Map<String, Object>> findByCustomer(Long id);
}
