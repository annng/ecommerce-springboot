package com.bootcamp.ecommerce.repository;

import com.bootcamp.ecommerce.entity.Cart;
import com.bootcamp.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "select * from cart where user_id = :userId ", nativeQuery = true)
    public List<Cart> getCartByUserId(Long userId);
}
