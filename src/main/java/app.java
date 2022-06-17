import connection.ConnectionFactory;
import dao.EmoticonsDAO;
import dao.ProdutoDAO;
import dao.validation;
import model.Emoticons;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class app {
    public static void main(String[] args) throws ParseException, SQLException {
        menu();
    }


    public static void menu() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - SISTEMA DE PROMOÇÕES DE PRODUTOS.");
        System.out.println("2 - DEIXE A SUA FRASE HOJE.");
        System.out.println("3 - SAIR");
        try {
            int opção = sc.nextInt();
            switch (opção) {
                case 1: admProdutos();break;
                case 2: emoticons();break;
                case 3: System.out.println("PROGRAMA FINALIZADO!!");break;
                default:
            }
        }catch (InputMismatchException ex){
            System.out.println("FAVOR DIGITAR UMA DAS OPÇÕES E SOMENTE O NUMERO");
            menu();
        }

    }


    public static void admProdutos() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===================== XPTO SYSTEM =====================");
        System.out.println("1 - CADASTRO DE OFERTA.");
        System.out.println("2 - ATUALIZAR OFERTA.");
        System.out.println("3 - EXCLUIR OFERTA.");
        System.out.println("4 - LISTA OFERTA POR NOME.");
        System.out.println("5 - VOLTA MENU ANTERIOR.");
        System.out.println("6 - SAIR");
        try {
            int opcao = sc.nextInt();
            limparTela();
            switch (opcao) {
                case 1: cadasPromocao();break;
                case 2: atualizarPromocao();break;
                case 3: excluirOferta();break;
                case 4: listarPorNome();break;
                case 5: menu();break;
                case 6: System.out.println("PROGRAMA FINALIZADO!!");break;
            }
        }catch (InputMismatchException iex){
            System.out.println("FAVOR DIGITAR UMA DAS OPÇÕES E SOMENTE O NUMERO");
            pausa(sc);
            admProdutos();
        }

    }

    public static void cadasPromocao() throws ParseException, SQLException {
        Scanner sc = new Scanner(System.in);
        Locale formato = new Locale("pt", "BR");
        System.out.println("===================== CADASTRO DE OFERTA =====================");
        try {
            System.out.println("NOME DA OFERTA: ");
            String nome = validarCampoVazio(sc.nextLine().trim());
            System.out.println("DESCRIÇÃO: ");
            String descricao = validarCampoVazio(sc.nextLine().trim());
            System.out.println("DESCONTO: ");
            double desconto = sc.useLocale(formato).nextDouble();
            System.out.println("PREÇO: ");
            double preço = sc.useLocale(formato).nextDouble();
            System.out.println("DATA INICIAL: ");
            sc.nextLine();
            String dataInicial = validarCampoVazio(sc.nextLine().trim());
            Produto produto = new Produto(nome, descricao, desconto, preço, dataInicial);
            try (Connection connection = new ConnectionFactory().initConexao()) {
                ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                produtoDAO.salvar(produto);
                System.out.println("CADASTRO REALIZADO COM SUCESSO!!");
                pausa(sc);
                admProdutos();
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITAR SOMENTE NUMERAIS NO PRADÃO 'PT'");
            cadasPromocao();
        } catch (ParseException pex) {
            System.out.println("FAVOR DIGITAR A DATA NO PADRÃO 'DD/MM/AAAA', VAMOS TENTAR DE NOVO.");
            cadasPromocao();
        }
        limparTela();
    }


    public static void atualizarPromocao() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Locale formato = new Locale("pt", "BR");
        System.out.println("===================== ATUALIZAR DE OFERTA =====================");
        try {
            System.out.println("DIGITE 'ID' DA OFERTA QUE SERA ATUALIZADA");
            int id = sc.nextInt();
            try (Connection connection = new ConnectionFactory().initConexao()) {
                ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                produtoDAO.verificar(id);
                System.out.println("NOME DA OFERTA: ");
                sc.nextLine();
                String nome = validarCampoVazio(sc.nextLine().trim());
                System.out.println("DESCRIÇÃO: ");
                String descricao = validarCampoVazio(sc.nextLine().trim());
                System.out.println("DESCONTO: ");
                double desconto = sc.useLocale(formato).nextDouble();
                System.out.println("PREÇO: ");
                double preço = sc.useLocale(formato).nextDouble();
                System.out.println("DATA INICIAL: ");
                sc.nextLine();
                String dataInicial = validarCampoVazio(sc.nextLine().trim());
                Produto produto = new Produto(nome, descricao, desconto, preço, dataInicial);
                produtoDAO.atualizar(produto, id);
                System.out.println("ATUALIZAÇÃO REALIZADA COM SUCESSO!!");
                pausa(sc);
                admProdutos();
            }
        } catch (ParseException pex) {
            System.out.println("FAVOR DIGITAR A DATA NO PADRÃO 'DD/MM/AAAA', VAMOS TENTAR DE NOVO.");
            atualizarPromocao();
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITAR SOMENTE NUMERAIS NO PRADÃO 'PT'");
            atualizarPromocao();
        } catch (validation e) {
            e.getMessage();
            pausa(sc);
            cadasPromocao();
        }
        limparTela();
    }


    public static void excluirOferta() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====================  EXCLUSÃO DE OFERTA =====================");
        try {
            System.out.println("DIGITE 'ID' DA OFERTA QUE SERA EXCLUIDA");
            int id = sc.nextInt();
            try (Connection connection = new ConnectionFactory().initConexao()) {
                ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                produtoDAO.verificar(id);
                produtoDAO.deletar(id);
                System.out.println("EXCLUSÃO REALIZADA COM SUCESSO!!");
                pausa(sc);
                sc.nextLine();
                admProdutos();
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITAR SOMENTE NUMEROS INTEIROS");
            excluirOferta();
        } catch (validation e) {
            System.out.println(e.getMessage());
            cadasPromocao();
        }
        limparTela();
    }


    public static void listarPorNome() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===================== LISTAR POR NOME =====================");
        try {
            System.out.println("QUAL O NOME DA OFERTA QUE DESEJA FILTRAR: ");
            String nome = validarCampoVazio(sc.nextLine().trim());
            try (Connection connection = new ConnectionFactory().initConexao()) {
                ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                produtoDAO.listarPorNome(nome).stream()
                        .forEach(System.out::println);
                pausa(sc);
                admProdutos();
            }
        } catch (NullPointerException ex) {
            System.out.println("LISTA VAZIA!!");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public static void emoticons() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("DIGITE A FRASE ");
        String frase = validarCampoVazio(sc.nextLine());
        Emoticons emoticons = new Emoticons(frase);
        try (Connection connection = new ConnectionFactory().initConexao()) {
            EmoticonsDAO emoticonsDAO = new EmoticonsDAO(connection);
            emoticonsDAO.salvar(emoticons);
            System.out.println(emoticons);
            pausa(sc);
            menu();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static String validarCampoVazio(String campo) {
        Scanner sc = new Scanner(System.in);
        if (campo.equals("")) {
            System.out.println("FAVOR PREENCHER O COMPO QUE SE ENCONTRA VAZIO");
            return validarCampoVazio(sc.nextLine());
        } else {
            return campo;
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 33; i++) {
            System.out.println("");
        }
    }


    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

}
