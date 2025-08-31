import java.util.ArrayList;

public class Convencao {
    private ArrayList<Acordo> acordos;

    public Convencao() {
        acordos = new ArrayList<>();
    }

    public boolean adicionarAcordo(Acordo acordo) {
        int codigo = acordo.getCodigo();
        Acordo aux = consultarAcordoPeloCodigo(codigo);
        if (aux == null) {
            acordos.add(acordo);
            return true;
        } else
            return false;

    }

    public Acordo consultarAcordoPeloCodigo(int codigo) {
        for (Acordo acordo : acordos) {
            if (acordo.getCodigo() == codigo)
                return acordo;
        }
        return null;
    }

    public Acordo consultarAcordoSiglaComprador(String sigla) {
        for (Acordo acordo : acordos)
            if (acordo.getComprador().getSigla().equals(sigla)) {
                return acordo;
            }
        return null;
    }
    
    public Acordo consultarAcordoSiglaVendedor(String sigla) {
        for (Acordo acordo : acordos)
            if (acordo.getVendedor().getSigla().equals(sigla)) {
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

    public ArrayList<Pais> listarPaisesVendedores() {
        ArrayList<Pais> vendedores = new ArrayList<>();
        for (Acordo acordo : acordos) {
            Pais vendedor = acordo.getVendedor();
            boolean jaAdicionado = false;
            for (Pais pais : vendedores) {
                if (pais.getSigla().equalsIgnoreCase(vendedor.getSigla())) {
                    jaAdicionado = true;
                    break;
                }
            }
            if (!jaAdicionado) {
                vendedores.add(vendedor);
            }
        }
        return vendedores;
    }
}





