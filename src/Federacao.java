import java.util.ArrayList;

public class Federacao {
    private ArrayList<Pais> paises;
    private ArrayList<Pais> vendedores;

    public Federacao() {
        paises = new ArrayList<>();
        vendedores = new ArrayList<>();
    }

    public boolean adicionarPais(Pais pais) {
        String sigla = pais.getSigla();
        Pais aux = consultarPaisPelaSigla(sigla);
        if (aux == null) {
            paises.add(pais);
            return true;
        } else
            return false;

    }

    public Pais consultarPaisPelaSigla(String sigla) {
        for (Pais pais : paises) {
            if (pais.getSigla().equalsIgnoreCase(sigla))
                return pais;
        }
        return null;
    }

    public void adicionarPaisVendedor(Pais paisVendedor) {

        for (Pais pais : vendedores) {
            if (pais.getSigla().equalsIgnoreCase(paisVendedor.getSigla())) {
                return;
            }
        }
        vendedores.add(paisVendedor);
    }

    public ArrayList<Pais> listarPaisesNaoVendedores() {
        ArrayList<Pais> paisesNaoVendedores = new ArrayList<>();

        for (Pais pais : paises) {
            if (!vendedores.contains(pais)) {
                paisesNaoVendedores.add(pais);
            }
        }
        return paisesNaoVendedores;
    }

}


