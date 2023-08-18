package com.bootcamp.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "no_invoice", nullable = false)
    @Size(min = 10, max = 100)
    private String noInvoice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "id_product", nullable = false)
    private Long productId;

    @Column(name = "title_product", nullable = false)
    @Size(min = 3, max = 100)
    private String productTitle;

    @Column(name = "image_product", nullable = true)
    @Size(max = 255)
    private String productImages;

    @Column(name = "product_description", nullable = true)
    @Size(max = 255)
    private String productDescription;

    @Column(name = "qty", nullable = false)
    @Size(max = 3)
    private Integer qty;

    @Column(nullable = false)
    @Size(max = 10)
    private Long price;
}
