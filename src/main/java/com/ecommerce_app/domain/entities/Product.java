package com.ecommerce_app.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "uuid")

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String uuid = UUID.randomUUID().toString();
    @Column(nullable = false)
    @Size(max = 30)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    private String productImage;
    private String details;
    @ElementCollection
    private List<String> attributes;
    private BigInteger productEvaluation;
    private String unitStock;
}
