package com.pragma.restaurant.domain.model;

import com.pragma.restaurant.domain.exception.DomainException;
import lombok.Data;

@Data
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private String nit;

    public void setPhone(String phone) {
        String regex = "^\\+?\\d+$";
        if (!phone.matches(regex)) {
            throw new DomainException("Formato de teléfono invalido");
        }
        if (phone.length() > 13) {
            throw new DomainException("Tamaño máximo de teléfono superado");
        }
        this.phone = phone;
    }

    public void setNit(String nit) {
        String regex = "^\\d+$";
        if (!nit.matches(regex)) {
            throw new DomainException("El nit solo debe ser número");
        }
        this.nit = nit;
    }

    public void setName(String name) {
        String regex = "^\\d+$";
        if (name.matches(regex)) {
            throw new DomainException("El nombre no puede contener solo números");
        }
        this.name = name;
    }
}
