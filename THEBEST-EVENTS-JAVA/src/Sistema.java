import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Sistema {
    private static Usuario usuario;
    private static GerenciadorEventos gerenciador = new GerenciadorEventos();
    private static Scanner scanner = new Scanner(System.in);

    // 🔹 Códigos ANSI para cores
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String CYAN = "\033[36m";
    public static final String RED = "\033[31m";

    public static void main(String[] args) {
        gerenciador.carregarEventos(); // carrega eventos do arquivo
        menuPrincipal();
        gerenciador.salvarEventos();   // salva ao sair
    }

    // 🔹 Método para limpar a tela
    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // 🔹 Método auxiliar para validar campos obrigatórios
    private static String lerCampoObrigatorio(String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.equals("-1")) {
                return "-1"; // permite abortar
            }
            if (entrada.isEmpty()) {
                System.out.println("O campo não pode ficar vazio. Tente novamente.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    // 🔹 Método auxiliar para idade
    private static int lerIdadeObrigatoria(String mensagem) {
        String entrada;
        int idade = -1;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.equals("-1")) {
                return -1; // aborta cadastro
            }
            try {
                idade = Integer.parseInt(entrada);
                if (idade <= 0) {
                    System.out.println("A idade deve ser um número positivo.");
                    idade = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números para a idade.");
            }
        } while (idade <= 0);
        return idade;
    }

    // 🔹 Método auxiliar para data/hora
    private static LocalDateTime lerDataHoraObrigatoria(String mensagem) {
        String entrada;
        LocalDateTime dataHora = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.equals("-1")) {
                return null; // aborta cadastro
            }
            try {
                dataHora = LocalDateTime.parse(entrada, formatter);
            } catch (Exception e) {
                System.out.println("Formato inválido. Use dd/MM/yyyy HH:mm (ex: 25/12/2026 20:30).");
            }
        } while (dataHora == null);
        return dataHora;
    }

    // 🔹 Método auxiliar para categoria por índice
    private static Categoria lerCategoriaObrigatoria() {
        int opcao = -1;
        do {
            System.out.println("Escolha a categoria do evento:");
            System.out.println("1 - FESTA");
            System.out.println("2 - ESPORTE");
            System.out.println("3 - SHOW");
            System.out.println("4 - CULTURAL");
            System.out.println("5 - OUTROS");
            System.out.print("Digite o número correspondente ou -1 para voltar: ");

            String entrada = scanner.nextLine().trim();
            if (entrada.equals("-1")) {
                return null; // aborta cadastro
            }

            try {
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1: return Categoria.FESTA;
                    case 2: return Categoria.ESPORTE;
                    case 3: return Categoria.SHOW;
                    case 4: return Categoria.CULTURAL;
                    case 5: return Categoria.OUTROS;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        opcao = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
            }
        } while (opcao == -1);

        return null;
    }

    private static void menuPrincipal() {
        int opcao;
        do {
            limparTela(); // limpa a tela antes de mostrar o menu
            System.out.println(BLUE + "\n==== MENU PRINCIPAL ====" + RESET);
            System.out.println(GREEN + "1 - Cadastrar Usuário" + RESET);
            System.out.println(GREEN + "2 - Cadastrar Evento" + RESET);
            System.out.println(CYAN + "3 - Listar Eventos" + RESET);
            System.out.println(CYAN + "4 - Participar de Evento" + RESET);
            System.out.println(CYAN + "5 - Cancelar Participação" + RESET);
            System.out.println(YELLOW + "6 - Consultar Eventos Confirmados" + RESET);
            System.out.println(YELLOW + "7 - Eventos Ocorrendo Agora" + RESET);
            System.out.println(YELLOW + "8 - Eventos Passados" + RESET);
            System.out.println(RED + "0 - Sair" + RESET);
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarEvento();
                case 3 -> gerenciador.listarEventos();
                case 4 -> participarEvento();
                case 5 -> cancelarParticipacao();
                case 6 -> {
                    if (usuario != null) usuario.listarEventosConfirmados();
                    else System.out.println("Nenhum usuário cadastrado.");
                }
                case 7 -> gerenciador.eventosOcorrendoAgora();
                case 8 -> gerenciador.eventosPassados();
                case 0 -> System.out.println("Saindo... Até logo!");
                default -> System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine(); // pausa antes de voltar ao menu

        } while (opcao != 0);
    }

    private static void cadastrarUsuario() {
        System.out.println("Aperte ENTER para iniciar o cadastro ou digite V para voltar.");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("V")) {
            System.out.println("Cadastro abortado. Voltando ao menu...");
            return;
        }

        System.out.println("Digite -1 em qualquer campo para voltar sem cadastrar.");

        String nome = lerCampoObrigatorio("Nome: ");
        if (nome.equals("-1")) return;

        String email = lerCampoObrigatorio("Email: ");
        if (email.equals("-1")) return;

        String cidade = lerCampoObrigatorio("Cidade: ");
        if (cidade.equals("-1")) return;

        int idade = lerIdadeObrigatoria("Idade: ");
        if (idade == -1) return;

        usuario = new Usuario(nome, email, cidade, idade);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarEvento() {
        System.out.println("Aperte ENTER para iniciar o cadastro de evento ou digite V para voltar.");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("V")) {
            System.out.println("Cadastro de evento abortado. Voltando ao menu...");
            return;
        }

        System.out.println("Digite -1 em qualquer campo para voltar sem cadastrar.");

        String nome = lerCampoObrigatorio("Nome do evento: ");
        if (nome.equals("-1")) return;

        String endereco = lerCampoObrigatorio("Endereço: ");
        if (endereco.equals("-1")) return;

        Categoria categoria = lerCategoriaObrigatoria();
        if (categoria == null) return;

        LocalDateTime horario = lerDataHoraObrigatoria("Horário (dd/MM/yyyy HH:mm): ");
        if (horario == null) return;

        String descricao = lerCampoObrigatorio("Descrição: ");
        if (descricao.equals("-1")) return;

        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        gerenciador.cadastrarEvento(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }
    private static void participarEvento() {
        if (usuario == null) {
            System.out.println("Cadastre um usuário primeiro!");
            return;
        }
        gerenciador.listarEventos();
        System.out.println("Digite o índice do evento para participar ou -1 para voltar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice == -1) {
            System.out.println("Participação abortada. Voltando ao menu...");
            return;
        }

        Evento e = gerenciador.getEvento(indice);
        if (e != null) {
            usuario.participarEvento(e);
            System.out.println("Participação confirmada!");
        } else {
            System.out.println("Evento inválido.");
        }
    }
    private static void cancelarParticipacao() {
        if (usuario == null) {
            System.out.println("Cadastre um usuário primeiro!");
            return;
        }
        usuario.listarEventosConfirmados();
        System.out.println("Digite o índice do evento para cancelar ou -1 para voltar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice == -1) {
            System.out.println("Cancelamento abortado. Voltando ao menu...");
            return;
        }

        Evento e = usuario.getEventoConfirmado(indice);
        if (e != null) {
            usuario.cancelarParticipacao(e);
            System.out.println("Participação cancelada!");
        } else {
            System.out.println("Evento inválido.");
        }
    }
}