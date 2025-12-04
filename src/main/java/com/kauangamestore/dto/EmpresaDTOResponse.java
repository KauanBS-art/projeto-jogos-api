//Classe de Kauan Batista

package com.kauangamestore.dto;

public class EmpresaDTOResponse {

    private Long id;
    private String nome;
    private String paisOrigem;
    private String descricao;
    private int totalJogos;

    public EmpresaDTOResponse() {}

    public EmpresaDTOResponse(Long id, String nome, String paisOrigem, String descricao, int totalJogos) {
        this.id = id;
        this.nome = nome;
        this.paisOrigem = paisOrigem;
        this.descricao = descricao;
        this.totalJogos = totalJogos;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getPaisOrigem() { return paisOrigem; }
    public String getDescricao() { return descricao; }
    public int getTotalJogos() { return totalJogos; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setTotalJogos(int totalJogos) { this.totalJogos = totalJogos; }
}
