import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String cidade;
    private int idade;
    private List<Evento> eventosConfirmados;

    // 🔹 Construtor
    public Usuario(String nome, String email, String cidade, int idade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.idade = idade;
        this.eventosConfirmados = new ArrayList<>();
    }

    // 🔹 Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }
    public int getIdade() { return idade; }

    // 🔹 Participar de evento
    public void participarEvento(Evento evento) {
        if (!eventosConfirmados.contains(evento)) {
            eventosConfirmados.add(evento);
        }
    }

    // 🔹 Cancelar participação
    public void cancelarParticipacao(Evento evento) {
        eventosConfirmados.remove(evento);
    }

    // 🔹 Listar eventos confirmados
    public void listarEventosConfirmados() {
        if (eventosConfirmados.isEmpty()) {
            System.out.println("Nenhum evento confirmado.");
            return;
        }
        System.out.println("=== Eventos confirmados por " + nome + " ===");
        for (int i = 0; i < eventosConfirmados.size(); i++) {
            System.out.println("[" + i + "] " + eventosConfirmados.get(i).getNome());
        }
    }

    // 🔹 Obter evento confirmado pelo índice
    public Evento getEventoConfirmado(int indice) {
        if (indice >= 0 && indice < eventosConfirmados.size()) {
            return eventosConfirmados.get(indice);
        }
        return null;
    }
}