package controlador;

import dao.ProdutoDAO;
import entidades.Produto;

import java.util.ArrayList;
import java.util.List;

public class ControladorEstoque {

    private static ControladorEstoque controladorEstoque;
    private static ControladorCadastro controladorCadastro;
    private static ProdutoDAO produtoDAO;
    private static List<Produto> produtosEstoque;

    public ControladorEstoque() {
        produtoDAO = new ProdutoDAO();
    }

    public static ControladorEstoque getInstanceControladorEstoque(){
        if (controladorEstoque == null) {
            controladorEstoque = new ControladorEstoque();
        }
        return controladorEstoque;
    }

    public static ControladorCadastro getInstanceControladorCadastro(){
        if (controladorCadastro == null) {
            controladorCadastro = new ControladorCadastro();
        }
        return controladorCadastro;
    }

    public List<Produto> getProdutosEstoque(){

        return produtoDAO.listarProdutosNoEstoque();
    }

    public List<Produto> getProdutosComboBoxAdicionar(){
//        Lista estoque, lista cadastro, verificiar se tem iguais, adicionar em uma nova lista, retornar a lista.

        List<Produto> cadastrados = controladorCadastro.getProdutosCadastro();
        List<Produto> estoque = controladorEstoque.getProdutosEstoque();
        List<Produto> comboBox = new ArrayList<>();
        for (Produto produto : estoque) {
            comboBox.add(produto);
        }
        for (Produto produto : cadastrados) {
            for (Produto produtoComboBox : comboBox) {
                if (produto.getId() != produtoComboBox.getId()) {
                    comboBox.add(produto);
                }

            }
        }
        return comboBox;
    }

    public void remover(Produto produto, int quantidade) {

        produtoDAO.removerEstoque(produto.getId(), quantidade);

//        for (Produto produtoExistente: produtosEstoque) {
//            if (produto.getId() == produtoExistente.getId()){
//                produtoExistente.setQuantidade(produtoExistente.getQuantidade() - produto.getQuantidade());
//                if (produtoExistente.getQuantidade() < 0) {
//                    produtoExistente.setQuantidade(0);
//                }
//            } else {
//                System.out.println("Produto não está em estoque");
//            }
//        }
    }
    public void adicionar(Produto produto, int quantidade){

        produtoDAO.adicionarEstoque(produto, quantidade);

//        for (Produto produtoExistente : produtosEstoque){
//            if (produto.getId() == produtoExistente.getId()){
//                produtoExistente.setQuantidade(produtoExistente.getQuantidade() + produto.getQuantidade());
//            } else {
//                produtosEstoque.add(produto);
//            }
        }
    }

