package com.pragma.restaurant.infrastructure.out.jpa.entity;

import com.pragma.restaurant.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha")
    private Date date;
    @Column(name = "estado")
    private Order.OrderState state;
    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurant;
    @Column(name = "id_cliente")
    private Long idClient;
    @Column(name = "id_chef")
    private Long idChef;
    @Column(name = "codigo_seguridad")
    private String securityCode;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDishEntity> orderDishes;
}
