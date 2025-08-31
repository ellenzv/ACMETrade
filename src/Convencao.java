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
        for (Acordo acordo : new ArrayList<>(acordos)) {
            if (acordo.getComprador().getSigla().equalsIgnoreCase(sigla)) {
                acordos.remove(acordo);
            }
        }
    }

    public ArrayList<Acordo> listarTodosAcordos() {
        ArrayList<Acordo> aux = new ArrayList<>();

        for (int i = 0; i < acordos.size(); i++) {
            Acordo acordo = acordos.get(i);
            aux.add(acordo);
        }
        return aux;
    }

    public Pais maiorQuantidadeAcordosVendedor() {
        if (acordos.isEmpty()) {
            return null;
        }

        Pais maisVendedor = null;
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
                maisVendedor = vendedorAtual;
            }
        }

        return maisVendedor;
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







