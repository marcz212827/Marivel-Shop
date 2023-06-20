package com.marivel.marivelshop.entidades.carrito;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "carrito")
public class CarritoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tokenSession;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fecha;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ItemCarrito> itemsCarrito = new TreeSet<>(
            (o1, o2) -> Integer.compare(o1.getProducto().getId(), o2.getProducto().getId()));

    public boolean isEmpty() {
        return itemsCarrito.isEmpty();
    }

    public Optional<ItemCarrito> BuscarItemCarritoConMismoProducto(ItemCarrito item) {
        return Optional.ofNullable(itemsCarrito)
                .map(Set::stream)
                .orElseGet(Stream::empty)
                .filter(item::EsMismoProducto)
                .findFirst();
    }

    public double CalcularMonto() {
        return itemsCarrito.stream()
                .mapToDouble(ItemCarrito::CalcularMonto)
                .sum();
    }
}
