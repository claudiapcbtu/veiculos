package br.com.gsx.veiculos.model;

import br.com.gsx.veiculos.service.ConsumoAPI;
import br.com.gsx.veiculos.service.ConverteDados;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

import java.util.Comparator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TabelaFipe(String Valor,
                         String Marca,
                         String Modelo,
                         String AnoModelo,
                         String Combustivel,
                         String CodigoFipe,
                         String MesReferencia,
                         String SiglaCombustivel) {

    @Override
    public String toString() {
        return "Valor: R$ " + Valor +
               " - Ano/Modelo: " + AnoModelo +
               " - Marca: " + Marca +
               " - Modelo: " + Modelo +
               " - Mês Referência: " + MesReferencia + "\n";
    }
}
