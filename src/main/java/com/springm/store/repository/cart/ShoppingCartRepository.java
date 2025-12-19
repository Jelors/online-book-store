package com.springm.store.repository.cart;

import com.springm.store.model.ShoppingCart;
import com.springm.store.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(Long userId);

    Optional<ShoppingCart> findByUser(User user);
}
