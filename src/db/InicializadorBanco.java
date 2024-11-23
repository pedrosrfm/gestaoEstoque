package db;

import com.mysql.cj.conf.ConnectionPropertiesTransform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InicializadorBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void inicializarBancoDeDados(){
        StringBuilder sqlBuilder = new StringBuilder();
        String caminhoArquivo = "src/db/gestao_estoque.sql";

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                sqlBuilder.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de script SQL: " + e.getMessage());
            return;
        }

        String scriptSQL = sqlBuilder.toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            for (String sql : scriptSQL.split(";")) {
                if (sql.trim().isEmpty()) {
                    statement.execute(sql.trim());
                }
            }

            System.out.println("Banco de dados e tabelas criados com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }

    }
}
