package br.com.gsx.veiculos.model;

import org.springframework.boot.Banner;

public record TabelaFipe(String TipoVeiculo,
                         String Valor,
                         String Marca,
                         String Modelo,
                         String AnoModelo,
                         String Combustivel,
                         String CodigoFipe,
                         String MesReferencia,
                         String SiglaCombustivel) {
    @Override
    public String toString() {
        return "Marca: " + Marca + "\n"+
               "Modelo: " + Modelo + "\n"+
               "Ano/Modelo: " + AnoModelo + "\n"+
               "Combustível: " + Combustivel + "\n"+
               "Valor: " + Valor;
    }
}
