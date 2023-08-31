package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.Cliente;
import com.unicaes.db.cobros.enity.DetallesPromocion;
import com.unicaes.db.cobros.enity.Producto;
import com.unicaes.db.cobros.enity.Transacciones;
import com.unicaes.db.cobros.repository.ClienteRepository;
import com.unicaes.db.cobros.repository.ProductoRepository;
import com.unicaes.db.cobros.repository.PromocionRepository;
import com.unicaes.db.cobros.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/transacciones")
public class TransaccionController {
    @Autowired
    TransaccionRepository repository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PromocionRepository promocionRepository;

    @GetMapping ("")
    public String listarTransaccion(Model model){
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        List<DetallesPromocion> promociones = (List<DetallesPromocion>) promocionRepository.findAll();
        List<Transacciones> transacciones = (List<Transacciones>) repository.findAll();
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("descuentos", promociones);
        model.addAttribute("transacciones", transacciones);
        model.addAttribute("clientes", clientes);
        return "transaccion";
    }

    @PostMapping("/agregar")
    public String crearTransaccion(
            @RequestParam(value = "idProducto") int idProducto,
            @RequestParam(value = "cantidad") int cantidad,
            @RequestParam(value = "idDescuento") int idDescuento,
            @RequestParam(value = "idCliente") int idCliente
    ) {
        Optional<Producto> producto = productoRepository.findById(idProducto);
        Optional<DetallesPromocion> promocion = promocionRepository.findById(idDescuento);
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if (producto.isPresent()) {
            Transacciones transacciones = new Transacciones();
            transacciones.setCantidadVendida(cantidad);
            transacciones.setProducto(producto.get());
            transacciones.setFechaHora(LocalDateTime.now());
            promocion.ifPresent(transacciones::setPromocion);
            cliente.ifPresent(transacciones::setCliente);
            repository.save(transacciones);
        }
        return "redirect:/transacciones";
    }

    @PostMapping("/consultarSaldo")
    public RedirectView consultarSaldoCliente(
            @RequestParam(value = "idCliente") int idCliente,
            RedirectAttributes redirectAttributes
    ){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        RedirectView redirect = new RedirectView();
        if(cliente.isPresent()){
            redirect.setContextRelative(true);
            redirect.setUrl("/transacciones");
            if(cliente.get().getTarjeta()!=null){
                redirectAttributes.addFlashAttribute("saldoCliente", cliente.get().getTarjeta().getSaldoActual());
                redirectAttributes.addFlashAttribute("puntosCliente", cliente.get().getTarjeta().getPuntosAcumulados());
                redirectAttributes.addFlashAttribute("idCliente", cliente.get().getIdCliente());
            }
        }

        return redirect;
    }
}