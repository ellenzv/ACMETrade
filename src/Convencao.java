import java.lang.reflect.Array;
import java.util.ArrayList;

public class Convencao {
    private ArrayList<Acordo> acordos;

    public Convencao() {
        acordos = new ArrayList<>();
    }

    public boolean adicionarAcordo(Acordo acordo) {
        int codigo = acordo.getCodigo();
        Acordo aux = consultarAcordo(codigo);
        if (aux == null) {
            acordos.add(acordo);
            return true;
        } else
            return false;
    }

    public Acordo consultarAcordo(int codigo) {
        for (Acordo acordo : acordos) {
            if (acordo.getCodigo() == codigo)
                return acordo;
        }
        return null;
    }

    public Acordo consultarAcordo(String sigla) {
        for (Acordo acordo : acordos)
            if (acordo.getComprador().getSigla().equals(sigla)) {
                return acordo;
            }
        return null;
    }

    public void removerAcordoPaisComprador(String sigla) {
        acordos.removeIf(acordo -> acordo.getComprador().getSigla().equalsIgnoreCase(sigla));
    }

    public ArrayList<Acordo> listarTodosAcordos() {
        ArrayList<Acordo> aux = new ArrayList<>();

        aux.addAll(acordos);
        return aux;
    }

    public Pais maiorQuantidadeAcordosVendedor() {
        if (acordos.isEmpty()) {
            return null;
        }

        Pais paisMaiorVendas = null;
        int maiorQtde = 0;

        for (int i = 0; i < acordos.size(); i++) {
            Acordo acordoAtual = acordos.get(i);
            Pais vendedorAtual = acordoAtual.getVendedor();

            int contador = 0;

            for (int j = 0; j < acordos.size(); j++) {
                Acordo acordoComparado = acordos.get(j);
                Pais vendedorComparado = acordoComparado.getVendedor();

                if (vendedorAtual.getSigla().equalsIgnoreCase(vendedorComparado.getSigla())) {
                    contador++;
                }
            }

            if (contador > maiorQtde) {
                maiorQtde = contador;
                paisMaiorVendas = vendedorAtual;
            }
        }

        return paisMaiorVendas;
    }

    public int quantidadeAcordoVendedor(Pais vendedor) {
        int contador = 0;
        for (Acordo acordo : acordos) {
            if (acordo.getVendedor().getSigla().equalsIgnoreCase(vendedor.getSigla())) {
                contador++;
            }
        }
        return contador;
    }
}







