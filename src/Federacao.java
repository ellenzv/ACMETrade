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

    public ArrayList<Pais> listarPaisesVendedores(){
        ArrayList<Pais> aux = new ArrayList<>();

        for (int i = 0; i < vendedores.size(); i++) {
            Pais paisVendedor = vendedores.get(i);
            aux.add(paisVendedor);
        }
        return aux;
    }

}
