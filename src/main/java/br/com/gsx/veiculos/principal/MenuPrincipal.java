package br.com.gsx.veiculos.principal;

import br.com.gsx.veiculos.model.Dados;
import br.com.gsx.veiculos.model.Modelos;
import br.com.gsx.veiculos.model.TabelaFipe;
import br.com.gsx.veiculos.service.ConsumoAPI;
import br.com.gsx.veiculos.service.ConverteDados;
import br.com.gsx.veiculos.service.ObterLista;

import java.util.*;
import java.util.stream.Collectors;

public class MenuPrincipal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    //declarando a url base como uma constante
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu(){
        String opcao;

        //valor do menu
        var menu = """
                
                Digite o tipo de veículo para retornar a marca:
                                
                Carro
                Moto
                Caminhão
                Sair
                                
                """;
        System.out.println(menu);
        opcao = leitura.nextLine();

        ObterLista obterLista = new ObterLista();
        while (!opcao.toLowerCase().contains("sa")) {
            try {
                String urlFinal = "";

                System.out.println("Informe a Marca!");
                var descMarca = leitura.nextLine();

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
                List<Dados> listaCompleta = obterLista.listaCompleta(json, converteDados);
                List<Dados> listaFiltrada = obterLista.listaFiltrada(listaCompleta, descMarca);

                System.out.println("\n Marcas: ");
                listaFiltrada.forEach(System.out::println);

                System.out.println("Informe o Código da Marca para retornar os Modelos!");
                var codMarca = leitura.nextLine();

                System.out.println("Informe o modelo desejado!");
                var nomeVeiculo = leitura.nextLine();

                urlFinal = urlFinal + "/" + codMarca + "/modelos";
                json = consumoApi.obterDados(urlFinal);
                obterLista.listaModelos(converteDados, json, nomeVeiculo);

                System.out.println("Informe o Código do Modelo para retornar os Anos!");
                var codModelo = leitura.nextLine();
                urlFinal = urlFinal + "/" + codModelo + "/anos";
                json = consumoApi.obterDados(urlFinal);
                var listaAno = obterLista.listaCompleta(json, converteDados);
                obterLista.listaTabelaFipe(consumoApi, converteDados, listaAno, urlFinal);

                System.out.println(menu);
                opcao = leitura.nextLine();
            } catch (Exception e) {
                throw new RuntimeException("Problema com aplicação! Erro original: " + e);
            }
        }
    }
}
