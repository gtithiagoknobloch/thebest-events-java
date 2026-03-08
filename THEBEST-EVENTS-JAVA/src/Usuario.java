import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String cidade;
    private int idade;
    private List<Evento> eventosConfirmados = new ArrayList<>();

    // Construtor
    public Usuario(String nome, String email, String cidade, int idade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.idade = idade;
    }

    // Métodos de participação
    public void participarEvento(Evento e) {
        if (!eventosConfirmados.contains(e)) {
            eventosConfirmados.add(e);
            e.adicionarParticipante(this);
        }
    }

    public void cancelarParticipacao(Evento e) {
        if (eventosConfirmados.contains(e)) {
            eventosConfirmados.remove(e);
            e.removerParticipante(this);
        }
    }

    // Listar eventos confirmados
    public void listarEventosConfirmados() {
        if (eventosConfirmados.isEmpty()) {
            System.out.println("Nenhum evento confirmado.");
            return;
        }
        System.out.println("\n==== EVENTOS CONFIRMADOS ====");
        for (int i = 0; i < eventosConfirmados.size(); i++) {
            Evento e = eventosConfirmados.get(i);
            System.out.printf("%d - %s (%s)\n", i, e.getNome(), e.getHorario());
        }
    }

    public Evento getEventoConfirmado(int indice) {
        if (indice >= 0 && indice < eventosConfirmados.size()) {
            return eventosConfirmados.get(indice);
        }
        return null;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }
    public int getIdade() { return idade; }
}