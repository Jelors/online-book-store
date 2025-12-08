package com.springm.store.controller;

import com.springm.store.dto.shoppingCart.AddShoppingCartRequestDto;
import com.springm.store.dto.shoppingCart.ShoppingCartDto;
import com.springm.store.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing carts")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    private final CartService cartService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(
            summary = "Add book to cart",
            description = "Add book to cart"
    )
    public ResponseEntity<ShoppingCartDto> addBook(
            @RequestBody @Valid AddShoppingCartRequestDto requestDto
    ) {
        return new ResponseEntity<ShoppingCartDto>(
                cartService.addItem(requestDto),
                HttpStatus.OK);
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(
            summary = "Update already added book quantity",
            description = "Update already added book quantity"
    )
    public ResponseEntity<ShoppingCartDto> updateCart(@PathVariable Long id,
                                                      @RequestBody @Valid AddShoppingCartRequestDto requestDto
    ) {
        return new ResponseEntity<ShoppingCartDto>(
                cartService.updateCart(id, requestDto),
                HttpStatus.NO_CONTENT
        );
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(
            summary = "Delete item from cart by ID",
            description = "Deletes item with specified ID from cart"
    )
    public void deleteItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }


}
