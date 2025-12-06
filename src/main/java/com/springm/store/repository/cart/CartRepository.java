package com.springm.store.repository.cart;

import com.springm.store.model.CartItem;
import com.springm.store.model.ShoppingCart;
import com.springm.store.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {
    Optional<ShoppingCart> findByUser(User user);
    Optional<CartItem> findCartItemById(Long cartItemId);
}
