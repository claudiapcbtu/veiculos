package br.com.gsx.veiculos.principal;

import br.com.gsx.veiculos.model.Dados;
import br.com.gsx.veiculos.model.Modelos;
import br.com.gsx.veiculos.model.TabelaFipe;
import br.com.gsx.veiculos.service.ConsumoAPI;
import br.com.gsx.veiculos.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    //declarando a url base como uma constante
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu(){
        var opcao = "Entrar";

        //valor do menu
        var menu = """
                
                Digite o tipo de veículo para retornar a marca:
                                
                Carro
                Moto
                Caminhão
                Sair
                                
                """;

        while (!opcao.equals("Sair")) {
            //apresentando a opção de menu
            System.out.println(opcao);
            //gardando na variável, a opção digitada
            System.out.println(menu);
            opcao = leitura.nextLine();

            String urlFinal = "";

            if (opcao.toLowerCase().contains("car")) {
                urlFinal = URL_BASE + "carros/marcas";
            } else if (opcao.toLowerCase().contains("mo")) {
                urlFinal = URL_BASE + "motos/marcas";
            } else if (opcao.toLowerCase().contains("cami")) {
                urlFinal = URL_BASE + "caminhoes/marcas";
            } else {
                break;
            }

            var json = consumoApi.obterDados(urlFinal);
            var marca = converteDados.obterLista(json, Dados.class);
            marca.stream()
                    .sorted(Comparator.comparing(Dados::descricao))
                    .forEach(System.out::println);

            System.out.println("Informe o Código da Marca para retornar os Modelos!");
            var codMarca = leitura.nextLine();
            urlFinal = urlFinal+"/"+codMarca+"/modelos";
            json = consumoApi.obterDados(urlFinal);

            var modeloLista = converteDados.obterDados(json, Modelos.class);
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::descricao))
                    .forEach(System.out::println) ;


            System.out.println("Informe o Código do Modelo para retornar os Anos!");
            var codModelo = leitura.nextLine();
            urlFinal = urlFinal+"/"+codModelo+"/anos";
            json = consumoApi.obterDados(urlFinal);
            var ano = converteDados.obterLista(json, Dados.class);
            ano.stream()
                    .sorted(Comparator.comparing(Dados::cod))
                    .forEach(System.out::println);

            System.out.println("Informe o Ano para retornar a tabela Fipe do veículo!");
            var codAno = leitura.nextLine();
            json = consumoApi.obterDados(urlFinal + "/"+codAno);
            var tabelaFipe = converteDados.obterDados(json, TabelaFipe.class);
            System.out.println(tabelaFipe.toString());
        }
    }
}
