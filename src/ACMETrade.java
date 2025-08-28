import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMETrade {
    private Scanner input = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saida de dados
    private Federacao federacao = new Federacao();
    private Convencao convencao = new Convencao();


    public ACMETrade() {
        redirecionaEntrada();    // Redireciona Entrada para arquivos
        redirecionaSaida();    // Redireciona Saida para arquivos
    }


    public void executar() {
        cadastrarPais();
        //     cadastrarAcordo();
        //       consultarPaisPorSigla();

//        consultarAcordoPeloCodigo();
//        consultarPaisSiglaComprador();
//        mudarNomePais();
//        removerAcordoPaisComprador();
//        listarTodosAcordos();
//        listarTodosPaisesCompradores();
    }

    //1
    private void cadastrarPais() {

        String sigla;
        do {
            sigla = input.nextLine();

            if (!sigla.equals("-1")) {
                String nome = input.nextLine();

                Pais pais = new Pais(sigla, nome);

                boolean resultado = federacao.adicionarPais(pais);

                if (resultado)
                    System.out.println("1:" + sigla + ";" + nome);
                else
                    System.out.println("1:erro-sigla repetida");
            }
        } while (!sigla.equals("-1"));
    }

    //2
    private void cadastrarAcordo() {

        int codigo = 0;

        do {
            codigo = input.nextInt();

            if (codigo != -1) {
                String produto = input.nextLine();
                double taxa = input.nextDouble();
                String siglaComprador = input.nextLine();
                String siglaVendedor = input.nextLine();

                Pais paisComprador = this.federacao.consultarPaisPelaSigla(siglaComprador);
                Pais paisVendedor = this.federacao.consultarPaisPelaSigla(siglaVendedor);

                if (paisVendedor == null) {
                    System.out.println("2:erro-vendedor inexistente");
                }
                if (paisComprador == null) {
                    System.out.println("2:erro-vendedor inexistente");
                }
                if (convencao.consultarAcordoPeloCodigo(codigo) == null) {
                    System.out.println("2:erro-codigo repetido");
                }
                Acordo acordo = new Acordo(codigo, produto, taxa, paisComprador, paisVendedor);

                boolean resultado = convencao.adicionarAcordo(acordo);

                if (resultado) {
                    System.out.println("2:" + codigo + ";" + produto + ";" + taxa + ";"
                            + siglaComprador + ";" + siglaVendedor);
                }
            }
        } while (codigo != -1);

    }

    //3
    private void consultarPaisPorSigla() {
        String sigla = input.nextLine();
        Pais pais = federacao.consultarPaisPelaSigla(sigla);
        if (pais == null)
            System.out.println("3:erro-sigla inexistente.");
        else
            System.out.println("3:" + sigla + ";" + pais.getNome());
    }

    //4
    private void consultarAcordoPeloCodigo() {
        int codigo = input.nextInt();
        Acordo acordo = convencao.consultarAcordoPeloCodigo(codigo);
        if (acordo == null) {
            System.out.println("4:erro-codigo inexistente");
        } else {
            System.out.println("4:" + codigo + ";" + acordo.getProduto() + ";" + acordo.getTaxa() + ";"
                    + acordo.getComprador() + ";" + acordo.getVendedor());
        }
    }

    private void consultarPaisSiglaComprador() {
        //TODO
    }

    private void mudarNomePais() {
        //TODO
    }

    private void removerAcordoPaisComprador() {
        //TODO
    }

    private void listarTodosAcordos() {
        //TODO
    }

    private void listarTodosPaisesCompradores() {
        //TODO
    }


    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            input = new Scanner(streamEntrada);   // Usa como entrada um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        input.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    // Redireciona Saida de dados para arquivos em vez da tela (terminal)
    // Chame este metodo para redirecionar a escrita de dados para arquivos
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    }

    // Restaura Entrada padrao para o teclado
    // Chame este metodo para retornar a leitura de dados para o teclado
    private void restauraEntrada() {
        input = new Scanner(System.in);
    }

    // Restaura Saida padrao para a tela (terminal)
    // Chame este metodo para retornar a escrita de dados para a tela
    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }
}
