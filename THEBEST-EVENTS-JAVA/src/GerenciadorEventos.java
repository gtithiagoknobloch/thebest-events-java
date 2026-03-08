import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorEventos {
    private List<Evento> eventos = new ArrayList<>();
    private static final String ARQUIVO = "events.data";

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
    }

    public Evento getEvento(int indice) {
        if (indice >= 0 && indice < eventos.size()) {
            return eventos.get(indice);
        }
        return null;
    }

    public void listarEventosDetalhados(Scanner scanner) {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        // Mostra lista resumida
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println("[" + i + "] " + eventos.get(i).getNome());
        }

        // Pede índice para mostrar detalhes
        System.out.print("Digite o número do evento para ver detalhes ou -1 para voltar: ");
        String entrada = scanner.nextLine().trim();
        int indice;

        try {
            indice = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            return;
        }

        if (indice == -1) {
            System.out.println("Voltando ao menu...");
            return;
        }

        if (indice >= 0 && indice < eventos.size()) {
            System.out.println("\n=== Detalhes do Evento ===");
            System.out.println(eventos.get(indice)); // usa o toString() formatado
        } else {
            System.out.println("Número inválido.");
        }
    }



    public void eventosOcorrendoAgora() {
        boolean encontrou = false;
        for (Evento e : eventos) {
            if (e.estaAcontecendoAgora()) {
                System.out.println(e);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum evento está acontecendo neste momento.");
        }
    }

    public void eventosPassados() {
        LocalDateTime agora = LocalDateTime.now();
        boolean encontrou = false;
        for (Evento e : eventos) {
            if (agora.isAfter(e.getFim())) {
                System.out.println(e);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum evento passado encontrado.");
        }
    }

    // 🔹 Salvar eventos em arquivo
    public void salvarEventos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            for (Evento e : eventos) {
                writer.write(e.getNome() + ";" +
                             e.getEndereco() + ";" +
                             e.getCategoria() + ";" +
                             e.getInicio().format(formatter) + ";" +
                             e.getFim().format(formatter) + ";" +
                             e.getDescricao());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
        }
    }

    // 🔹 Carregar eventos do arquivo
    public void carregarEventos() {
        eventos.clear();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6) {
                    String nome = partes[0];
                    String endereco = partes[1];
                    Categoria categoria = Categoria.valueOf(partes[2]);
                    LocalDateTime inicio = LocalDateTime.parse(partes[3], formatter);
                    LocalDateTime fim = LocalDateTime.parse(partes[4], formatter);
                    String descricao = partes[5];

                    Evento e = new Evento(nome, endereco, categoria, inicio, fim, descricao);
                    eventos.add(e);
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar eventos: " + ex.getMessage());
        }
    }
}