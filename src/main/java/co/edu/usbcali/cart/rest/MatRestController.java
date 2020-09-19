package co.edu.usbcali.cart.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mat")
public class MatRestController {

    @GetMapping("/sum/{n1}/{n2}")
    public Resultado sum(@PathVariable Integer n1, @PathVariable Integer n2) {
        return new Resultado(n1 + n2);
    }

    @PostMapping("/res")
    public Resultado res(@RequestBody Operador operador) {
        return new Resultado(operador.getN1()-operador.getN2());
    }


}
