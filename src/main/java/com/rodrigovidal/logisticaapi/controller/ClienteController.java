package com.rodrigovidal.logisticaapi.controller;

import com.rodrigovidal.logisticaapi.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(UUID.fromString("66d09818-138c-4119-a4b2-f27a061a8ca9"));
        cliente1.setNome("Rodrigo");
        cliente1.setTelefone("21982823135");
        cliente1.setEmail("rodrigo@gmail.com");

        var cliente2 = new Cliente();
        cliente2.setId(UUID.fromString("b2443537-666d-4a9f-9414-4480c6e5a786"));
        cliente2.setNome("Ananda");
        cliente2.setTelefone("2199597940");
        cliente2.setEmail("ananda@gmail.com");
        return Arrays.asList(cliente1, cliente2);
    }

}
