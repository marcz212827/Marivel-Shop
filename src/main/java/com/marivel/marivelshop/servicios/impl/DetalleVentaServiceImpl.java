package com.marivel.marivelshop.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.DetalleVenta;
import com.marivel.marivelshop.repositorios.IDetalleVenta;
import com.marivel.marivelshop.servicios.IDetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements IDetalleVentaService {

    @Autowired
    private IDetalleVenta data;

    @Override
    public void Guardar(DetalleVenta detalleVenta) {
        data.save(detalleVenta);
    }
}
