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
    private Federacao federacao;

    public ACMETrade() {
        redirecionaEntrada();    // Redireciona Entrada para arquivos
        redirecionaSaida();    // Redireciona Saida para arquivos
    }


    public void executar() {
        System.out.println("Seja bem-vindo(a) ao ACMETrade!" +
                "Escolha a opção desejada no menu. Para sair digite -1");

        int opcao = 0;
        while (opcao != -1) {
            menu();
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    cadastrarPais();
                    break;
                case 2:
                    cadastrarAcordo();
                    break;
                case 3:
                    consultarPaisPorSigla();
                    break;
                case 4:
                    consultarAcordoPeloCodigo();
                    break;
                case 5:
                    consultarPaisSiglaComprador();
                    break;
                case 6:
                    listarPeixesCompradosPorCliente();
                    break;
                case 7:
                    removerAcordoPaisComprador();
                    break;
                case 8:
                    listarTodosAcordos();
                    break;
                case 9:
                    listarTodosPaisesCompradores();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void menu() {
        System.out.println("Menu de opcoes");
        System.out.println("[-1] Sair");
        System.out.println("[1] Cadastrar um país");
        System.out.println("[2] Cadastrar um acordo");
        System.out.println("[3] Consultar país por sigla");
        System.out.println("[4] Consultar acordo pelo código");
        System.out.println("[5] Consultar acordo pela sigla do comprador");
        System.out.println("[6] Alterar nome de um país");
        System.out.println("[7] Remover acordo de um país comprador");
        System.out.println("[8] Listar todos os acordos");
        System.out.println("[9] Listar todos os países compradores");
    }

    private void cadastrarPais() {
        String nome = input.nextLine();
        String sigla = input.nextLine();
        Pais pais = new Pais(sigla, nome);

        boolean resultado = federacao.adicionarPais(pais);
        if (resultado)
            System.out.println("1:" + sigla + ";" + nome);
        else
            System.out.println("1:erro-sigla repetida");

    }

    private void cadastrarAcordo() {
        int codigo = input.nextInt();
        String produto = input.nextLine();
        double taxa = input.nextDouble();


    }

    private void consultarPaisPorSigla() {
        String sigla = input.nextLine();
        Pais pais = federacao.consultarPaisPelaSigla(sigla);
        if (pais == null)
            System.out.println("3:erro-sigla inexistente.");
        else
            System.out.println("3:" + sigla + ";" + pais.getNome());
    }

    private void consultarAcordoPeloCodigo() {

    }

    private void consultarPaisSiglaComprador() {

    }

    private void listarPeixesCompradosPorCliente() {

    }

    private void removerAcordoPaisComprador() {

    }

    private void listarTodosAcordos() {

    }

    private void listarTodosPaisesCompradores() {

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
