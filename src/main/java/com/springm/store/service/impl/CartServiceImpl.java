package com.springm.store.service.impl;

import com.springm.store.dto.shoppingCart.AddShoppingCartRequestDto;
import com.springm.store.dto.shoppingCart.ShoppingCartDto;
import com.springm.store.dto.shoppingCart.UpdateShoppingCartRequestDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.ShoppingCartMapper;
import com.springm.store.model.Book;
import com.springm.store.model.CartItem;
import com.springm.store.model.ShoppingCart;
import com.springm.store.model.User;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.cart.CartRepository;
import com.springm.store.repository.user.UserRepository;
import com.springm.store.service.CartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartMapper cartMapper;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    public ShoppingCartDto addItem(AddShoppingCartRequestDto requestDto) {
        Long userId = userDetailsService.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        ShoppingCart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setUser(user);
                    return newCart;
                });

        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(book.getId()))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + requestDto.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setBook(book);
            item.setQuantity(requestDto.getQuantity());
            item.setShoppingCart(cart);
            cart.getCartItems().add(item);
        }
        ShoppingCart savedCart = cartRepository.save(cart);

        return cartMapper.toDto(savedCart);
    }

    @Override
    public ShoppingCartDto updateCart(Long cartItemId, UpdateShoppingCartRequestDto requestDto) {
        CartItem item = cartRepository.findCartItemById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found!"));

        item.setQuantity(requestDto.getQuantity());
        cartRepository.save(item.getShoppingCart());
        return cartMapper.toDto(item.getShoppingCart());
    }

    @Override
    public void removeItem(Long id) {
        CartItem item = cartRepository.findCartItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found!"));

        item.getShoppingCart().getCartItems().remove(item);
        cartRepository.save(item.getShoppingCart());
    }
}
