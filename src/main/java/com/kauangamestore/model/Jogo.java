//Classe de Kauan Batista Silveira

package com.kauangamestore.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "text")
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;


    @ManyToMany
    @JoinTable(
        name = "jogo_categoria",
        joinColumns = @JoinColumn(name = "id_jogo"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @ManyToMany
    @JoinTable(
        name = "jogo_plataforma",
        joinColumns = @JoinColumn(name = "id_jogo"),
        inverseJoinColumns = @JoinColumn(name = "id_plataforma")
    )
    private List<Plataforma> plataformas;

    public Jogo() {}

    public Jogo(String titulo, String descricao, Double preco, LocalDate dataLancamento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.dataLancamento = dataLancamento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public List<Plataforma> getPlataformas() { return plataformas; }
    public void setPlataformas(List<Plataforma> plataformas) { this.plataformas = plataformas; }
}
