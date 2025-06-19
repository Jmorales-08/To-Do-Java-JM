import java.time.LocalDate;

public class Tarea {
    private String titulo;
    private String descripcion;
    private boolean completada;
    private LocalDate fechaLimite;

    public Tarea(String titulo, String descripcion, LocalDate fechaLimite) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completada = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }

    @Override
    public String toString() {
        return (completada ? "[X] " : "[ ] ") + titulo + " - " + descripcion + " (Fecha límite: " + fechaLimite + ")";
    }
}
