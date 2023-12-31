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
        Double total = 0.0;
        model.addAttribute("productos", productos);
        model.addAttribute("descuentos", promociones);
        model.addAttribute("transacciones", transacciones);
        model.addAttribute("clientes", clientes);
        for (Transacciones transaccion: transacciones) {
            total += (transaccion.getPromocion()==null) ? (transaccion.getProducto().getPrecioUnitario() * transaccion.getCantidadVendida()) :
                    (
                            (transaccion.getProducto().getPrecioUnitario() * transaccion.getCantidadVendida()) -
                                    (transaccion.getProducto().getPrecioUnitario() * transaccion.getCantidadVendida() * transaccion.getPromocion().getDescuentoAplicado() / 100)
                    )
                ;
        }
        model.addAttribute("total", total);
        return "transaccion";
    }

    @PostMapping("/agregar")
    public String crearTransaccion(
            @RequestParam(value = "idProducto") int idProducto,
            @RequestParam(value = "cantidad") int cantidad,
            @RequestParam(value = "idDescuento") int idDescuento,
            @RequestParam(value = "idCliente", defaultValue = "0") int idCliente
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
            @RequestParam(value = "idCliente", defaultValue = "0") int idCliente,
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

    @PostMapping("/pagar")
    public RedirectView pagarCuenta(
            @RequestParam(value = "idCliente", defaultValue = "0") int idCliente,
            @RequestParam(value = "total", defaultValue = "0") Double total,
            @RequestParam(value = "formaPago") int formaPago,
            RedirectAttributes redirectAttributes
    ){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        RedirectView redirect = new RedirectView();
        redirect.setContextRelative(true);
        redirect.setUrl("/transacciones");
        String formaDePago =" Efectivo ";

        if(cliente.isPresent()){

            switch (formaPago) {
                case 2:
                    if(cliente.get().getTarjeta()!=null){
                        cliente.get().getTarjeta().setSaldoActual(cliente.get().getTarjeta().getSaldoActual() - total);
                    }
                    formaDePago = " Tarjeta ";
                    break;
                case 3:
                    if(cliente.get().getTarjeta()!=null){
                        cliente.get().getTarjeta().setPuntosAcumulados(cliente.get().getTarjeta().getPuntosAcumulados() - total);
                    }
                    formaDePago = " Puntos ";
                    break;
                default:
                    break;
            }

            clienteRepository.save(cliente.get());

            if(cliente.get().getTarjeta()!=null){
                redirectAttributes.addFlashAttribute("saldoCliente", cliente.get().getTarjeta().getSaldoActual());
                redirectAttributes.addFlashAttribute("puntosCliente", cliente.get().getTarjeta().getPuntosAcumulados());
                redirectAttributes.addFlashAttribute("idCliente", cliente.get().getIdCliente());
            }
        }
        redirectAttributes.addFlashAttribute("pagado", "Total pagado: $ " + total + " con " + formaDePago);
        return redirect;
    }
}