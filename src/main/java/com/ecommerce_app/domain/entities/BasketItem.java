package com.ecommerce_app.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "uuid")

@Entity
@Table(name = "basketItems")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String uuid = UUID.randomUUID().toString();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private BigDecimal price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id", nullable = false)
    @JsonBackReference
    private Basket basket;

}
