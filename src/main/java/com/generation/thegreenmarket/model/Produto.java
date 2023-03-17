package com.generation.thegreenmarket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotBlank(message = "Atibuto nome do produto é obrigatório")
    @Size(min = 5, max = 100, message = "Atributo nome do produto deve conter no mínimo 5 caracteres e no máximo 100 caracteres")
    private String nomeProduto;

    @NotNull(message = "Atibuto descrição é obrigatório")
    private String descricaoProduto;

    @Positive
    private Integer quantidadeProduto;

    @Positive
    @Digits(integer=6,fraction=2,message="O preço é obrigatorio")
    private float valorProduto;

}