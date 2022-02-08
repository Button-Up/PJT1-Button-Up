package com.ssafy.buttonup.domain.model.entity.product;

import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * 상품 엔티티
 *
 * @author jeongyeon woo
 * created on 2022-02-07
 */

@Entity
@Table(name="products")
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_seq")
    private Long seq;

    @NotNull
    @Column(name="product_price")
    private int price;

    @Column(name="product_name")
    private String name;

    @NotNull
    @Column(name="product_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="product_type")
    private ProductType type;

    @Column(name="prodcut_purchase_link")
    private String purchaseLink;

    @Column(name="product_image")
    private String productImage;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    @Builder
    public Product(int price, String name, String description, ProductType type, String purchaseLink, String productImage, Parent parent){
        this.price = price;
        this.name = name;
        this.description = description;
        this.type = type;
        this.purchaseLink = purchaseLink;
        this.productImage = productImage;
        this.parent = parent;
    }
}
