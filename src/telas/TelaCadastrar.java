package telas;

import controlador.ControladorCadastro;
import controlador.ControladorEstoque;
import entidades.Produto;
import entidades.enums.TipoProduto;

import javax.swing.*;
import java.awt.*;

public class TelaCadastrar extends JFrame {

    private ControladorCadastro controladorCadastro = ControladorCadastro.getInstance();

    public TelaCadastrar() {
        setTitle("Cadastrar produto");
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
        JLabel nomeLabel = new JLabel("Nome do produto:");
        JTextField nomeTextField = new JTextField();
        nomeTextField.setPreferredSize(new Dimension(200, 25)); // Ajusta a largura e altura do TextField

        JLabel tipoLabel = new JLabel("Tipo:");
        JComboBox<TipoProduto> tipoComboBox = new JComboBox<>(TipoProduto.values());
        tipoComboBox.setPreferredSize(new Dimension(100, 25)); // Ajusta a largura e altura do Spinner

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCampos.add(nomeLabel, gbc);

        gbc.gridx = 1;
        painelCampos.add(nomeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCampos.add(tipoLabel, gbc);

        gbc.gridx = 1;
        painelCampos.add(tipoComboBox, gbc);

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoCancelar = new JButton("Cancelar");

        botaoCadastrar.addActionListener(e -> {
            String nomeProduto = nomeTextField.getText().trim();
            TipoProduto tipoProdutoString = (TipoProduto) tipoComboBox.getSelectedItem();
            int tipoProduto = tipoProdutoString.ordinal();

            if (nomeProduto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor insira o nome do produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                controladorCadastro.cadastrar(nomeProduto, tipoProduto);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });

        botaoCancelar.addActionListener(e -> {
            dispose();
        });

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoCancelar);

        // Adicionar painéis ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Configurar o JFrame
        add(painelPrincipal);
        setVisible(true);
    }
}
