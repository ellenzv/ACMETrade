import java.util.ArrayList;

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


}
