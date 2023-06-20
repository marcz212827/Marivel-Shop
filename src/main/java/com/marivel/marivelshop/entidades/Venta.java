package com.marivel.marivelshop.entidades;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Date fecha;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER)
    private Set<DetalleVenta> detalleVenta = new TreeSet<>(
            (o1, o2) -> Integer.compare(o1.getProducto().getId(), o2.getProducto().getId()));

    public double CalcularMonto() {
        return detalleVenta.stream()
                .mapToDouble(DetalleVenta::CalcularMonto)
                .sum();
    }
}
