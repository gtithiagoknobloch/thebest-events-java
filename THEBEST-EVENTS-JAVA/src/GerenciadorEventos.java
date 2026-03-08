import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorEventos {
    private List<Evento> eventos = new ArrayList<>();
    private static final String FILE_NAME = "events.data";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Cadastrar evento
    public void cadastrarEvento(Evento e) {
        eventos.add(e);
    }

    // Listar eventos
    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        System.out.println("\n==== LISTA DE EVENTOS ====");
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.printf("%d - %s (%s) em %s\n", i, e.getNome(), e.getCategoria(), e.getHorario().format(formatter));
        }
    }

    // Obter evento pelo índice
    public Evento getEvento(int indice) {
        if (indice >= 0 && indice < eventos.size()) {
            return eventos.get(indice);
        }
        return null;
    }

    // Ordenar por horário
    public void ordenarPorHorario() {
        eventos.sort(Comparator.comparing(Evento::getHorario));
    }

    // Eventos ocorrendo agora
    public void eventosOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("\n==== EVENTOS OCORRENDO AGORA ====");
        for (Evento e : eventos) {
            if (e.estaOcorrendoAgora()) {
                System.out.println(e.getNome() + " - " + e.getHorario().format(formatter));
            }
        }
    }

    // Eventos já ocorridos
    public void eventosPassados() {
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("\n==== EVENTOS JÁ OCORRIDOS ====");
        for (Evento e : eventos) {
            if (e.jaOcorreu()) {
                System.out.println(e.getNome() + " - " + e.getHorario().format(formatter));
            }
        }
    }

    // 🔹 Persistência em arquivo
    public void salvarEventos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Evento e : eventos) {
                writer.write(e.getNome() + ";" + e.getEndereco() + ";" + e.getCategoria() + ";" +
                        e.getHorario().format(formatter) + ";" + e.getDescricao());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
        }
    }

    public void carregarEventos() {
        eventos.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String nome = partes[0];
                    String endereco = partes[1];
                    Categoria categoria = Categoria.valueOf(partes[2]);
                    LocalDateTime horario = LocalDateTime.parse(partes[3], formatter);
                    String descricao = partes[4];
                    eventos.add(new Evento(nome, endereco, categoria, horario, descricao));
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar eventos: " + ex.getMessage());
        }
    }
}

