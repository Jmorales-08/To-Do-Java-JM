import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class GestorTareas {
    private List<Tarea> tareas;

    public GestorTareas() {
        this.tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            System.out.println("Lista de tareas:");
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i));
            }
        }
    }

    public void marcarTareaComoCompletada(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.get(indice).marcarComoCompletada();
            System.out.println("Tarea marcada como completada.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void eliminarTarea(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.remove(indice);
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("Índice inválido.");
        }
    }
    
    public void guardarEnArchivo(String nombreArchivo) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
        for (Tarea tarea : tareas) {
            writer.println(
                tarea.getTitulo() + ";" +
                tarea.getDescripcion() + ";" +
                tarea.getFechaLimite() + ";" +
                tarea.estaCompletada()
            );
        }
        System.out.println("Tareas guardadas en archivo.");
    } catch (IOException e) {
        System.out.println("Error al guardar el archivo: " + e.getMessage());
    }
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
    tareas.clear(); // Limpiamos la lista actual
    try (Scanner fileScanner = new Scanner(new File(nombreArchivo))) {
        while (fileScanner.hasNextLine()) {
            String linea = fileScanner.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                String titulo = partes[0];
                String descripcion = partes[1];
                LocalDate fecha = LocalDate.parse(partes[2]);
                boolean completada = Boolean.parseBoolean(partes[3]);

                Tarea tarea = new Tarea(titulo, descripcion, fecha);
                if (completada) {
                    tarea.marcarComoCompletada();
                }
                tareas.add(tarea);
            }
        }
        System.out.println("Tareas cargadas desde archivo.");
    } catch (FileNotFoundException e) {
        System.out.println("No se encontró el archivo, se empezará con una lista vacía.");
    } catch (Exception e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
    }

    public void mostrarTareasFiltradas(boolean completadas) {
    boolean hayResultados = false;
    for (int i = 0; i < tareas.size(); i++) {
        Tarea tarea = tareas.get(i);
        if (tarea.estaCompletada() == completadas) {
            System.out.println((i + 1) + ". " + tarea);
            hayResultados = true;
        }
    }
    if (!hayResultados) {
        System.out.println("No hay tareas " + (completadas ? "completadas" : "pendientes") + ".");
    }
    }
}
    

