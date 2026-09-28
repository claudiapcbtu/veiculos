package br.com.gsx.veiculos;

import br.com.gsx.veiculos.principal.MenuPrincipal;
import br.com.gsx.veiculos.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeiculosApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(VeiculosApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.exibirMenu();
    }
}
