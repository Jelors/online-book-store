package com.springm.store.repository.order;

import com.springm.store.model.Order;
import com.springm.store.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
