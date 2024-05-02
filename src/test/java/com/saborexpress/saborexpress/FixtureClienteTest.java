package com.saborexpress.saborexpress;

import com.saborexpress.saborexpress.domain.TipoDeCliente;
import com.saborexpress.saborexpress.model.Cliente;

import java.util.List;

public class FixtureClienteTest {
    public static List<Cliente> listaDeClientes() {
        return List.of(
                new Cliente(1,"Gabriel","israel@gmail.com",TipoDeCliente.PESSOAFISICA),
                new Cliente(2, "Jhenny", "jhenny@gmail.com", TipoDeCliente.PESSOAJURIDICA),
                new Cliente(3, "Israel", "israel@gmail.com", TipoDeCliente.PESSOAFISICA)
        );

    }

    public static Cliente cliente() {
        return new Cliente(1, "Gabriel", "gabriel@gmail.com", TipoDeCliente.PESSOAFISICA);
    }
}
