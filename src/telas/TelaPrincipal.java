package telas;

import controlador.ControladorCadastro;
import controlador.ControladorEstoque;
import entidades.Produto;
import entidades.enums.TipoProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.awt.Color.GRAY;
import static java.awt.Color.LIGHT_GRAY;

public class TelaPrincipal extends JFrame implements AtualizacaoListener {

    private ControladorEstoque controladorEstoque = ControladorEstoque.getInstanceControladorEstoque();
    private ControladorCadastro controladorCadastro = ControladorCadastro.getInstance();

    private static DefaultTableModel tabela;

    public TelaPrincipal() {

        setTitle("Gerenciamento de Estoque");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(painelPrincipal());
        setVisible(true);
    }

    private JPanel painelPrincipal(){
        JPanel painelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelPrincipal.setBackground(GRAY);

        JLabel cabecalho = new JLabel("Estoque");
        cabecalho.setHorizontalAlignment(0);
        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
        JScrollPane scroll = criarScroll();
        painelPrincipal.add(cabecalho);
        painelPrincipal.add(criarScroll(), BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes());

        return painelPrincipal;
    }

    public void atualizarTabela(DefaultTableModel tabela) {
        tabela.setRowCount(0);
        for (Produto produto : controladorEstoque.getProdutosEstoque()) {
            Object[] linha = {produto.getNome(), produto.getQuantidade()};
            tabela.addRow(linha);
        }
    }

    private JScrollPane criarScroll() {
        String[] colunas = {"Produto", "Quantidade"};
        Object[][] dadosVazios = {};
        tabela = new DefaultTableModel(dadosVazios, colunas);
        atualizarTabela(tabela);
        JTable tabelaEstoque = new JTable(tabela);
        tabelaEstoque.setBackground(LIGHT_GRAY);
        return new JScrollPane(tabelaEstoque);
    }

    private JPanel painelBotoes() {
        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 130, 2));
        painelBotoes.setBackground(GRAY);
        JPanel painelBotoesEsquerda = new JPanel();
        JPanel painelBotoesDireita = new JPanel();
        painelBotoesEsquerda.setLayout(new BoxLayout(painelBotoesEsquerda, BoxLayout.Y_AXIS));
        painelBotoesDireita.setLayout(new BoxLayout(painelBotoesDireita, BoxLayout.Y_AXIS));
        Dimension tamanhoBotao = new Dimension(250, 40);

        painelBotoesEsquerda.setBackground(GRAY);
        JButton botaoAdicionar = new JButton("Adicionar ao estoque");
        JButton botaoRemover = new JButton("Remover do estoque");
        botaoAdicionar.setMaximumSize(tamanhoBotao);
        botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoRemover.setMaximumSize(tamanhoBotao);
        botaoRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesEsquerda.add(Box.createVerticalGlue());
        painelBotoesEsquerda.add(botaoAdicionar);
        painelBotoesEsquerda.add(Box.createVerticalStrut(10));
        painelBotoesEsquerda.add(botaoRemover);


        painelBotoesDireita.setBackground(GRAY);
        JButton botaoRelatorio = new JButton("Gerar relatório");
        JButton botaoCadastrar = new JButton("Cadastrar novo produto");
        botaoRelatorio.setMaximumSize(tamanhoBotao);
        botaoRelatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCadastrar.setMaximumSize(tamanhoBotao);
        botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesDireita.add(Box.createVerticalGlue());
        painelBotoesDireita.add(botaoCadastrar);
        painelBotoesDireita.add(Box.createVerticalStrut(10));
        painelBotoesDireita.add(botaoRelatorio);

        botaoAdicionar.addActionListener(e -> {
            TelaAdicionar telaAdicionar = new TelaAdicionar(this);
            telaAdicionar.setVisible(true);
            atualizarTabela(tabela);
        });

        botaoRemover.addActionListener(e -> {
            TelaRemover telaRemover = new TelaRemover(this);
            telaRemover.setVisible(true);
            atualizarTabela(tabela);
        });

        botaoCadastrar.addActionListener(e -> {
            TelaCadastrar telaCadastro = new TelaCadastrar();
            telaCadastro.setVisible(true);
        });

        botaoRelatorio.addActionListener(e -> {
            TelaRelatorio telaRelatorio = new TelaRelatorio();
            telaRelatorio.setVisible(true);
        });

        painelBotoes.add(painelBotoesEsquerda);
        painelBotoes.add(painelBotoesDireita);

        return painelBotoes;
    }

    public void adicionarDadosFalsos(){
        Produto[] produtosFalsos = {
                new Produto(1, "Arroz", TipoProduto.ALIMENTO, 30),
                new Produto(2, "Refrigerante", TipoProduto.BEBIDA, 40),
                new Produto(3, "Detergente", TipoProduto.LIMPEZA, 20),
                new Produto(4, "Feijão", TipoProduto.ALIMENTO, 50),
                new Produto(5, "Suco de Laranja", TipoProduto.BEBIDA, 10),
                new Produto(6, "Pão Francês", TipoProduto.ALIMENTO, 25),
                new Produto(7, "Sabonete", TipoProduto.LIMPEZA, 15),
                new Produto(8, "Café", TipoProduto.BEBIDA, 20),
                new Produto(9, "Macarrão", TipoProduto.ALIMENTO, 30),
                new Produto(10, "Desinfetante", TipoProduto.LIMPEZA, 10),
                new Produto(11, "Água Mineral", TipoProduto.BEBIDA, 40),
                new Produto(12, "Arroz Integral", TipoProduto.ALIMENTO, 35),
                new Produto(13, "Esponja", TipoProduto.LIMPEZA, 5),
                new Produto(14, "Suco de Abacate", TipoProduto.BEBIDA, 25),
                new Produto(15, "Bolinho de Chocolate", TipoProduto.ALIMENTO, 20),
                new Produto(16, "Pão de Queijo", TipoProduto.ALIMENTO, 25),
                new Produto(17, "Shampoo", TipoProduto.LIMPEZA, 15),
                new Produto(18, "Suco de Mango", TipoProduto.BEBIDA, 30),
                new Produto(19, "Feijão Preto", TipoProduto.ALIMENTO, 20),
                new Produto(20, "Desinfetante para Superfícies", TipoProduto.LIMPEZA, 10),
                new Produto(21, "Água Tônica", TipoProduto.BEBIDA, 40),
                new Produto(22, "Macarrão ao Queijo", TipoProduto.ALIMENTO, 35),
                new Produto(23, "Sabonete Líquido", TipoProduto.LIMPEZA, 5),
                new Produto(24, "Refrigerante de Limão", TipoProduto.BEBIDA, 25),
                new Produto(25, "Bolo de Chocolate", TipoProduto.ALIMENTO, 20),
                new Produto(26, "Detergente para Roupa", TipoProduto.LIMPEZA, 15),
                new Produto(27, "Suco de Abacaxi", TipoProduto.BEBIDA, 30),
                new Produto(28, "Arroz Branco", TipoProduto.ALIMENTO, 25),
                new Produto(29, "Esponja para Pratos", TipoProduto.LIMPEZA, 10),
                new Produto(30, "Café Expresso", TipoProduto.BEBIDA, 20),
                new Produto(31, "Pão Francês Integral", TipoProduto.ALIMENTO, 30),
                new Produto(32, "Desinfetante para Mãos", TipoProduto.LIMPEZA, 5),
                new Produto(33, "Suco de Laranja com Gengibre", TipoProduto.BEBIDA, 25),
                new Produto(34, "Feijão Branco", TipoProduto.ALIMENTO, 20),
                new Produto(35, "Detergente para Vidros", TipoProduto.LIMPEZA,15),
               };

        for (Produto produtoFalso : produtosFalsos){
//            controladorEstoque.adicionar(produtoFalso);
        }
    }

    @Override
    public void onAtualizar() {
        atualizarTabela(tabela);
    }
}
