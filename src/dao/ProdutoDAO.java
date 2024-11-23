package dao;

import controlador.ControladorCadastro;
import controlador.ControladorEstoque;
import entidades.Produto;
import entidades.enums.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/gestao_estoque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void cadastrarProduto(String nome, int tipoProduto){
        String sql = "INSERT INTO produtos_cadastrados (nome, tipo) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setInt(2, tipoProduto);
            statement.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public void adicionarEstoque(Produto produto, int quantidade){
        String sqlVerifica = "SELECT * FROM estoque WHERE id_produto = ?";
        String sqlInsert = "INSERT INTO estoque (id_produto, produto, tipo_produto, quantidade) VALUES (?, ?, ?, ?)";
        String sqlUpdate = "UPDATE estoque SET quantidade = quantidade + ? WHERE id_produto = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement verifica = connection.prepareStatement(sqlVerifica);
            verifica.setInt(1, produto.getId());
            ResultSet resultSet = verifica.executeQuery();

            if (resultSet.next()) {
                PreparedStatement update = connection.prepareStatement(sqlUpdate);
                update.setInt(1, quantidade);
                update.setInt(2, produto.getId());
                update.executeUpdate();
                System.out.println("Quantidade adicionada com sucesso!");
            } else {
                PreparedStatement insert = connection.prepareStatement(sqlInsert);
                insert.setInt(1, produto.getId());
                insert.setString(2, produto.getNome());
                insert.setInt(3, produto.getTipoProduto().ordinal());
                insert.setInt(4, quantidade);
                insert.executeUpdate();
                System.out.println("Produto adicionado ao estoque!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto ao estoque: " + e.getMessage());
        }
    }

    public void removerEstoque(int id, int quantidade) {
        String sql = "UPDATE estoque SET quantidade = quantidade - ? WHERE id_produto = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantidade);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantidade removida do estoque!");
            } else {
                System.out.println("Produto não encontrado no estoque ou quantidade insuficiente!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover produto do estoque: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutosCadastrados() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos_cadastrados";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int idProduto = resultSet.getInt("id_produto_cadastrado");
                String nomeProduto = resultSet.getString("nome");
                int tipoProdutoInt = resultSet.getInt("tipo");
                TipoProduto tipoProduto = TipoProduto.values()[tipoProdutoInt];
                Produto produto = new Produto(idProduto, nomeProduto, tipoProduto);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos cadastrados: " + e.getMessage());
        }
        return produtos;
    }

    public List<Produto> listarProdutosNoEstoque() {
        List<Produto> produtosEstoque = new ArrayList<>();
        String sql = "SELECT id_produto, produto, tipo_produto, quantidade FROM estoque";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int idProduto = resultSet.getInt("id_produto");
                String nomeProduto = resultSet.getString("produto");
                int tipoProdutoInt = resultSet.getInt("tipo_produto");
                int quantidade = resultSet.getInt("quantidade");

                // Cria o objeto Produto e adiciona à lista
                TipoProduto tipoProduto = TipoProduto.values()[tipoProdutoInt];
                Produto produto = new Produto(idProduto, nomeProduto, tipoProduto, quantidade);
                produtosEstoque.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos no estoque: " + e.getMessage());
        }
        return produtosEstoque;
    }

}
