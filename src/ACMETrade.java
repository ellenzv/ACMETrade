import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMETrade {
    private Scanner input = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
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
        cadastrarAcordo();
        consultarPaisPorSigla();
        consultarAcordoPeloCodigo();
        consultarAcordoSiglaComprador();
        mudarNomePais();
        removerAcordoPaisComprador();
        listarTodosAcordos();
        listarPaisesNaoVendedores();
        vendedorMaiorQuantidadeAcordos();
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
            input.nextLine();

            if (codigo != -1) {
                String produto = input.nextLine();
                double taxa = input.nextDouble();
                input.nextLine();
                String siglaComprador = input.nextLine();
                String siglaVendedor = input.nextLine();

                Pais paisComprador = this.federacao.consultarPaisPelaSigla(siglaComprador);
                Pais paisVendedor = this.federacao.consultarPaisPelaSigla(siglaVendedor);

                if (paisVendedor == null) {
                    System.out.println("2:erro-vendedor inexistente");
                    continue;
                }
                if (paisComprador == null) {
                    System.out.println("2:erro-comprador inexistente");
                    continue;
                }

                Acordo acordo = new Acordo(codigo, produto, taxa, paisComprador, paisVendedor);

                boolean resultado = convencao.adicionarAcordo(acordo);

                federacao.adicionarPaisVendedor(paisVendedor);

                if (resultado) {
                    System.out.println("2:" + codigo + ";" + produto + ";" + taxa + ";"
                            + siglaComprador + ";" + siglaVendedor);
                } else {
                    System.out.println("2:erro-codigo repetido");
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
        input.nextLine();
        Acordo acordo = convencao.consultarAcordo(codigo);
        if (acordo == null) {
            System.out.println("4:erro-codigo inexistente");
        } else {
            System.out.println("4:" + codigo + ";" + acordo.getProduto() + ";" + acordo.getTaxa() + ";"
                    + acordo.getComprador().getSigla() + ";" + acordo.getVendedor().getSigla());
        }
    }

    //5
    private void consultarAcordoSiglaComprador() {
        String sigla = input.nextLine();
        Acordo acordo = convencao.consultarAcordo(sigla);
        if (acordo == null)
            System.out.println("5:erro-sigla inexistente");
        else
            System.out.println("5:" + acordo.getCodigo() + ";" + acordo.getProduto() + ";" + acordo.getTaxa() +
                    ";" + acordo.getComprador().getSigla() + ";" + acordo.getVendedor().getSigla());
    }

    //6
    private void mudarNomePais() {
        String sigla = input.nextLine();
        String novoNome = input.nextLine();
        Pais pais = federacao.consultarPaisPelaSigla(sigla);

        if (pais == null)
            System.out.println("6:erro-sigla inexistente");
        else {
            pais.setNome(novoNome);
            System.out.println("6:" + sigla + ";" + novoNome);
        }
    }

    //7
    private void removerAcordoPaisComprador() {
        String sigla = input.nextLine();
        Acordo acordo = convencao.consultarAcordo(sigla);
        if (acordo == null)
            System.out.println("7:erro-nenhum acordo encontrado");
        else {
            convencao.removerAcordoPaisComprador(sigla);
            System.out.println("7:acordos removidos");
        }

    }

    //8
    private void listarTodosAcordos() {
        ArrayList<Acordo> acordos = convencao.listarTodosAcordos();
        if (acordos.isEmpty())
            System.out.println("8:erro-nenhum acordo cadastrado");
        else {
            for (int i = 0; i < acordos.size(); i++) {
                Acordo acordo = acordos.get(i);
                System.out.println("8:" + acordo.getCodigo() + ";"
                        + acordo.getProduto() + ";"
                        + acordo.getTaxa() + ";"
                        + acordo.getComprador().getSigla() + ";"
                        + acordo.getVendedor().getSigla());
            }
        }
    }

    //9
    private void listarPaisesNaoVendedores() {
        ArrayList<Pais> paisesCompradores = federacao.listarPaisesNaoVendedores();

        if (paisesCompradores.isEmpty())
            System.out.println("9:erro-nenhum país encontrado");
        else {
            for (Pais pais : paisesCompradores) {
                System.out.println("9:" + pais.getSigla() + ";" + pais.getNome());
            }
        }
    }

    //10
    private void vendedorMaiorQuantidadeAcordos() {
        Pais pais = convencao.maiorQuantidadeAcordosVendedor();
        if (pais == null)
            System.out.println("10:erro-nenhum país encontrado.");
        else {
            int qtdAcordo = convencao.quantidadeAcordoVendedor(pais);
            System.out.println("10:" + pais.getSigla() + ";" + pais.getNome() + ";" + qtdAcordo);
        }
    }

    //Redirecionamento de entrada e saída

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

    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    }
}
