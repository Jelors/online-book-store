package com.springm.store.service.impl;

import com.springm.store.dto.cart.AddShoppingCartRequestDto;
import com.springm.store.dto.cart.ShoppingCartResponseDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.ShoppingCartMapper;
import com.springm.store.model.Book;
import com.springm.store.model.CartItem;
import com.springm.store.model.ShoppingCart;
import com.springm.store.model.User;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.cart.ShoppingCartRepository;
import com.springm.store.repository.cart.item.CartRepository;
import com.springm.store.repository.user.UserRepository;
import com.springm.store.service.ShoppingCartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ShoppingCartResponseDto getCart() {
        ShoppingCart cart = getCurrentUserCart();
        return shoppingCartMapper.toResponse(cart);
    }

    @Override
    public ShoppingCartResponseDto addItem(AddShoppingCartRequestDto requestDto) {
        ShoppingCart cart = getCurrentUserCart();

        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(book.getId()))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get()
                    .setQuantity(
                            existingItem.get().getQuantity() + requestDto.getQuantity()
                    );
        } else {
            CartItem item = new CartItem();
            item.setBook(book);
            item.setQuantity(requestDto.getQuantity());
            item.setShoppingCart(cart);
            cart.getCartItems().add(item);
        }
        shoppingCartRepository.save(cart);

        return shoppingCartMapper.toResponse(cart);
    }

    @Override
    public ShoppingCartResponseDto updateCart(
            Long cartItemId,
            AddShoppingCartRequestDto requestDto
    ) {
        CartItem item = cartRepository
                .findByIdAndShoppingCartId(cartItemId, getCurrentUserCart().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Item with id " + cartItemId + " not found!"
                ));

        item.setQuantity(requestDto.getQuantity());
        shoppingCartRepository.save(item.getShoppingCart());
        return shoppingCartMapper.toResponse(item.getShoppingCart());
    }

    @Override
    public void removeItem(Long id) {
        CartItem item = cartRepository.findByIdAndShoppingCartId(id, getCurrentUserCart().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Item with id " + id + " not found!"
                ));

        item.getShoppingCart().getCartItems().remove(item);
        shoppingCartRepository.save(item.getShoppingCart());
    }

    @Override
    public void createCartAndSetUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart getCurrentUserCart() {
        Long userId = userDetailsService.getCurrentUserId();
        return shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() ->
                                    new EntityNotFoundException("User not found with id " + userId)
                            );

                    ShoppingCart cart = new ShoppingCart();
                    cart.setUser(user);
                    return shoppingCartRepository.save(cart);
                });

    }
}
