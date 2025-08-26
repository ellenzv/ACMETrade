public class Acordo {

    private int codigo;
    private String produto;
    private double taxa;

    public int getCodigo() {
        return codigo;
    }

    public String getProduto() {
        return produto;
    }

    public double getTaxa() {
        return taxa;
    }

    public Acordo(int codigo, String produto, double taxa, Pais comprador, Pais vendedor) {
        this.codigo = codigo;
        this.produto = produto;
        this.taxa = taxa;
    }
}
