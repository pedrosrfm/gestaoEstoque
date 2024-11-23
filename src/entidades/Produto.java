package entidades;

import controlador.ControladorCadastro;
import controlador.ControladorEstoque;
import entidades.enums.TipoProduto;

import java.util.Objects;

public class Produto {

    // ATRIBUTOS
    private int id;
    private String nome;
    private TipoProduto tipoProduto;
    private int quantidade;

    // CONSTRUTOR
    public Produto(int id, String nome, TipoProduto tipoProduto, int quantidade){
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.quantidade = quantidade;
    }

    public Produto(int id, String nome, TipoProduto tipoProduto){
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.quantidade = 0;
    }

    public Produto(Produto produto, int quantidade){
        this.id=produto.getId();
        this.nome=produto.getNome();
        this.tipoProduto=produto.getTipoProduto();
        this.quantidade=quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoProdutoToString() {
        return tipoProduto.toString();
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return getNome();
    }
}
