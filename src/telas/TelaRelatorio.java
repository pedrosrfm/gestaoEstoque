package telas;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorio extends JFrame {

    public TelaRelatorio() {
        setTitle("Gerar Relatório");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com um BoxLayout para organizar verticalmente
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Pergunta centralizada
        JLabel pergunta = new JLabel("Como deseja gerar o relatório?");
        pergunta.setFont(new Font("Arial", Font.BOLD, 14));
        pergunta.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel de botões com espaço uniforme
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Botões
        JButton imprimir = new JButton("Imprimir");
        JButton txt = new JButton("Arquivo .txt");
        JButton cancelar = new JButton("Cancelar");
        Dimension botaoTamanho = new Dimension(120, 30);
        imprimir.setPreferredSize(botaoTamanho);
        txt.setPreferredSize(botaoTamanho);
        cancelar.setPreferredSize(botaoTamanho);

        // Adicionando eventos aos botões
        imprimir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Função de impressão não implementada.");
        });

        txt.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Função de criação de arquivo .txt não implementada.");
        });

        cancelar.addActionListener(e -> dispose());

        // Adicionando botões ao painel
        painelBotoes.add(imprimir);
        painelBotoes.add(txt);
        painelBotoes.add(cancelar);

        // Adicionando componentes ao painel principal
        painelPrincipal.add(pergunta);
        painelPrincipal.add(Box.createVerticalStrut(20)); // Espaçamento entre a pergunta e os botões
        painelPrincipal.add(painelBotoes);

        // Adicionando o painel principal à janela
        add(painelPrincipal);
        setVisible(true);
    }
}
