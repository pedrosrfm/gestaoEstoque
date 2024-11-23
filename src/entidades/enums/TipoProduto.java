package entidades.enums;

public enum TipoProduto {

    ALIMENTO(0),
    BEBIDA(1),
    LIMPEZA(2),
    OUTRO(3);

    private int codigo;
    private TipoProduto(int codigo){
        this.codigo=codigo;
    }

    public int getCodigo(){
        return codigo;
    }
    public static TipoProduto valor(int codigo){
        for (TipoProduto valor : TipoProduto.values()){
            if (valor.getCodigo() == codigo){
                return valor;
            }
        }
        throw new IllegalArgumentException("Código de produto inválido");
    }

    @Override
    public String toString() {
        String nome = name().toLowerCase();
        return nome.substring(0, 1).toUpperCase() + nome.substring(1);
    }
}
