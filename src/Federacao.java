import java.util.ArrayList;

public class Federacao {
    private ArrayList<Pais> paises;

    public Federacao(){
        paises = new ArrayList<>();
    }

    public boolean adicionarPais(Pais pais){
        String sigla = pais.getSigla();
        Pais aux = consultarPaisPelaSigla(sigla);
        if (aux == null) {
            paises.add(pais);
            return true;
        }
        else
            return false;

    }
    public Pais consultarPaisPelaSigla(String sigla) {
        for (Pais pais : paises) {
            if (pais.getSigla().equalsIgnoreCase(sigla))
                return pais;
        }
        return null;
    }



}
