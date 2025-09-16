package com.pragma.restaurant.infrastructure.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "plato")
@Data
@NoArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "precio")
    private Integer price;
    @Column(name = "url_imagen")
    private String urlImage;
    @Column(name = "activo")
    private Boolean active = true;
    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurant;
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoryEntity category;
}
