package com.nieve.backend.service;

import com.nieve.backend.model.Carrito;
import com.nieve.backend.model.CarritoId;
import com.nieve.backend.model.Pedido;
import com.nieve.backend.model.Producto;
import com.nieve.backend.repository.CarritoRepository;
import com.nieve.backend.repository.PedidoRepository;
import com.nieve.backend.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired 
    private PedidoRepository pedidoRepository; 
    @Autowired
    private ProductoRepository productoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> findById(CarritoId id) {
        return carritoRepository.findById(id);
    }

    public Carrito save(Carrito carrito ) {
        Long nroPedido = carrito.getId().getNroPedido();
        Long idProducto = carrito.getId().getIdProducto();

        Pedido pedido = pedidoRepository.findById(nroPedido)
        .orElseThrow(() -> new NoSuchElementException("El Pedido con ID " + nroPedido + " no existe."));
        Producto producto = productoRepository.findById(idProducto)
        .orElseThrow(() -> new NoSuchElementException("El Producto con ID " + idProducto + " no existe."));;
        carrito.setPedido(pedido);
        carrito.setProducto(producto);
        return carritoRepository.save(carrito);
    }

    public void delete(CarritoId id) {
        carritoRepository.deleteById(id);
    }

}
