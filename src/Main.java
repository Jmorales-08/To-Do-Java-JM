import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        String archivo = "tareas.txt";

        // 1. Cargar tareas desde archivo al iniciar
        gestor.cargarDesdeArchivo(archivo);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== GESTOR DE TAREAS =====");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Filtrar tareas (completadas o pendientes)");
            System.out.println("6. Salir y guardar tareas en un archivo .TXT");
            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();

                    LocalDate fechaLimite = null;
                    boolean fechaValida = false;

                    while (!fechaValida) {
                        try {
                            System.out.print("Fecha límite (formato AAAA-MM-DD): ");
                            String fechaInput = scanner.nextLine();
                            fechaLimite = LocalDate.parse(fechaInput);
                            fechaValida = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Intenta con AAAA-MM-DD.");
                        }
                    }

                    Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaLimite);
                    gestor.agregarTarea(nuevaTarea);
                    System.out.println("Tarea agregada correctamente.");
                    break;

                case "2":
                    gestor.mostrarTareas();
                    break;

                case "3":
                    System.out.print("Número de tarea a marcar como completada: ");
                    int indiceCompletar = Integer.parseInt(scanner.nextLine()) - 1;
                    gestor.marcarTareaComoCompletada(indiceCompletar);
                    break;

                case "4":
                    System.out.print("Número de tarea a eliminar: ");
                    int indiceEliminar = Integer.parseInt(scanner.nextLine()) - 1;
                    gestor.eliminarTarea(indiceEliminar);
                    break;
                
                case "5":
                    System.out.print("¿Qué tareas deseas ver? (c = completadas / p = pendientes): ");
                    String filtro = scanner.nextLine().toLowerCase();

                    if (filtro.equals("c")) {
                        gestor.mostrarTareasFiltradas(true);
                    } else if (filtro.equals("p")) {
                        gestor.mostrarTareasFiltradas(false);
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;
                case "6":
                    gestor.guardarEnArchivo(archivo);
                    salir = true;
                    System.out.println("Tareas guardadas. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
