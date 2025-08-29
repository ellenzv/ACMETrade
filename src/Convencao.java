import javax.accessibility.AccessibleRole;
import java.util.ArrayList;
import java.util.List;

public class Convencao {
    private ArrayList<Acordo> acordos;
    private Acordo acordo;


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

    public ArrayList<Acordo> listarTodosAcordos() {
        ArrayList<Acordo> aux = new ArrayList<>();

        for(int i=0; i< acordos.size(); i++) {
            Acordo acordo = acordos.get(i);
            aux.add(acordo);
        }
        return aux;
    }

}
