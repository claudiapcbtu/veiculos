package br.com.gsx.veiculos.service;
import br.com.gsx.veiculos.model.Dados;
import br.com.gsx.veiculos.model.Modelos;
import br.com.gsx.veiculos.model.TabelaFipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ObterLista {

    public List<Dados> listaCompleta(String json, ConverteDados converteDados ){
        var lista = converteDados.obterLista(json, Dados.class);
        lista.stream()
                .sorted(Comparator.comparing(Dados::descricao));
        return lista;
    }

    public List<Dados> listaFiltrada(List<Dados> lista, String descricaoLista) {
        return lista.stream()
                    .filter(m -> m.descricao().toLowerCase().contains(descricaoLista.toLowerCase()))
                    .collect(Collectors.toList());
    }

    public void listaTabelaFipe(ConsumoAPI consumoApi, ConverteDados converteDados, List<Dados> listaAno, String urlFinal ){
        List<TabelaFipe> listaTabelaFipe = new ArrayList<>();
        for (int i = 0; i < listaAno.size(); i++) {
            var codAno = listaAno.get(i).cod();
            var json = consumoApi.obterDados(urlFinal + "/"+codAno);
            var tabelaFipe = converteDados.obterDados(json, TabelaFipe.class);
            listaTabelaFipe.add(tabelaFipe);
        }
        listaTabelaFipe.stream().sorted(Comparator.comparing(TabelaFipe::Valor))
                .forEach(System.out::println);
    }

    public void listaModelos(ConverteDados converteDados, String json, String nomeVeiculo){
        var modeloLista = converteDados.obterDados(json, Modelos.class);
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::descricao)) ;

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.descricao().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n Modelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);
    }
}
