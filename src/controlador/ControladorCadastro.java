package controlador;

import dao.ProdutoDAO;
import entidades.Produto;
import entidades.enums.TipoProduto;

import java.util.List;

public class ControladorCadastro {

    private static ControladorCadastro controladorCadastro;
    private static ProdutoDAO produtoDAO;
    private List<Produto> produtosCadastro;

    ControladorCadastro(){
        produtoDAO = new ProdutoDAO();
    }

    public static ControladorCadastro getInstance(){
        if (controladorCadastro == null) {
            controladorCadastro = new ControladorCadastro();
        }
        return controladorCadastro;
    }

    public List<Produto> getProdutosCadastro(){
        return produtoDAO.listarProdutosCadastrados();
    }


    public void cadastrar(String nome, int tipoProduto) throws Exception{
        produtoDAO.cadastrarProduto(nome, tipoProduto);

//        for (Produto produtoExistente : produtosCadastro){
//            if (produto.getId() == produtoExistente.getId()){
//                System.out.println("Produto já está cadastrado");
//            } else {
//                produtosCadastro.add(produto);
//            }
//        }
    }
}
