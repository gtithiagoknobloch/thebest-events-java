import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes = new ArrayList<>();

    public Evento(String nome, String endereco, Categoria categoria,
                  LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    // Métodos de participação
    public void adicionarParticipante(Usuario u) {
        participantes.add(u);
    }

    public void removerParticipante(Usuario u) {
        participantes.remove(u);
    }

    // Métodos de verificação de horário
    public boolean estaOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isEqual(agora) || (horario.isBefore(agora) && horario.plusHours(2).isAfter(agora));
    }

    public boolean jaOcorreu() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isBefore(agora);
    }

    // Getters
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public Categoria getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public List<Usuario> getParticipantes() { return participantes; }
    

}
