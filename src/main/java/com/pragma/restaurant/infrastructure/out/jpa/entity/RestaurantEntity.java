package com.pragma.restaurant.infrastructure.out.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "direccion")
    private String address;
    @Column(name = "id_propietario")
    private Long idOwner;
    @Column(name = "teleofono")
    private String phone;
    @Column(name = "url_logo")
    private String urlLogo;
    private String nit;
}
