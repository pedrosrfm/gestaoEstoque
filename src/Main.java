import db.InicializadorBanco;
import telas.TelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        InicializadorBanco.inicializarBancoDeDados();
        SwingUtilities.invokeLater(TelaPrincipal::new);

    }
}