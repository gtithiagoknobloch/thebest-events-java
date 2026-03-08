import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String descricao;

    // 🔹 Construtor com início e fim
    public Evento(String nome, String endereco, Categoria categoria,
                  LocalDateTime inicio, LocalDateTime fim, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.inicio = inicio;
        this.fim = fim;
        this.descricao = descricao;
    }

    // 🔹 Getters
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public Categoria getCategoria() { return categoria; }
    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFim() { return fim; }
    public String getDescricao() { return descricao; }

    // 🔹 Método para verificar se está acontecendo agora
    public boolean estaAcontecendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return !agora.isBefore(inicio) && !agora.isAfter(fim);
    }

    // 🔹 Representação textual do evento
    @Override
public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return String.format(
        "Evento: %s\n" +
        "Endereço: %s\n" +
        "Categoria: %s\n" +
        "Início: %s\n" +
        "Fim: %s\n" +
        "Descrição: %s\n",
        nome,
        endereco,
        categoria,
        inicio.format(formatter),
        fim.format(formatter),
        descricao
    );
}

}
