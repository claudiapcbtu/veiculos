package br.com.gsx.veiculos.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Dados(@JsonAlias("codigo") String cod,
                    @JsonProperty("nome") String descricao) {
//@JsonProperty na serialização e na deserialização. Ao ler o json ele utilizará
// o Desc e ao montar um json ele também vai usar a Desc
}
