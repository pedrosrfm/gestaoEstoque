package telas;

import controlador.ControladorCadastro;
import controlador.ControladorEstoque;
import entidades.Produto;
import entidades.enums.TipoProduto;

import javax.swing.*;
import java.awt.*;

public class TelaAdicionar extends JFrame {

    private ControladorEstoque controladorEstoque = ControladorEstoque.getInstanceControladorEstoque();
    private ControladorCadastro controladorCadastro = ControladorCadastro.getInstance();
    private AtualizacaoListener listener;

    public TelaAdicionar(AtualizacaoListener listener) {
        setTitle("Adicionar produto");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10)); // Margens

        // Painel para os campos
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes
        JLabel produtoLabel = new JLabel("Nome do produto:");
        JComboBox<Produto> produtoComboBox = new JComboBox<>(controladorCadastro.getProdutosCadastro().toArray(new Produto[0]));
        produtoComboBox.setPreferredSize(new Dimension(200, 25)); // Ajusta a largura e altura do ComboBox

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Modelo numérico
        quantidadeSpinner.setPreferredSize(new Dimension(100, 25)); // Ajusta a largura e altura do Spinner

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCampos.add(produtoLabel, gbc);

        gbc.gridx = 1;
        painelCampos.add(produtoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCampos.add(quantidadeLabel, gbc);

        gbc.gridx = 1;
        painelCampos.add(quantidadeSpinner, gbc);

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoCancelar = new JButton("Cancelar");

        botaoAdicionar.addActionListener(e -> {
            Produto produto = (Produto) produtoComboBox.getSelectedItem();
            int quantidade = (int) quantidadeSpinner.getValue();

            try {
                controladorEstoque.adicionar(produto, quantidade);
                JOptionPane.showMessageDialog(null, "Produto adicionado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

            if (listener != null) {
                listener.onAtualizar();
            }

        });

        botaoCancelar.addActionListener(e -> {
            if (listener != null) {
                listener.onAtualizar();
            }
            dispose();
        });

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoCancelar);

        // Adicionar painéis ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Configurar o JFrame
        add(painelPrincipal);
        setVisible(true);
    }
}
