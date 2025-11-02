//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

public class PlataformaDTOResponse {
    private Long id;
    private String nome;
    private int quantidadeJogos;

    public PlataformaDTOResponse() {}

    public PlataformaDTOResponse(Long id, String nome, int quantidadeJogos) {
        this.id = id;
        this.nome = nome;
        this.quantidadeJogos = quantidadeJogos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidadeJogos() { return quantidadeJogos; }
    public void setQuantidadeJogos(int quantidadeJogos) { this.quantidadeJogos = quantidadeJogos; }
}