package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaGrupo {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenuGrupos() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE GRUPOS ---");
            System.out.println("1. Registrar Grupo");
            System.out.println("2. Listar Grupos");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO GRUPO ---");
                    System.out.print("Ingrese el código o nombre del grupo (ej. Grupo A): ");
                    String nombreGrupo = scanner.nextLine();
                    
                    System.out.print("Ingrese el ID de la materia asociada: ");
                    String idMateria = scanner.nextLine();
                    
                    System.out.print("Ingrese el ID del docente asignado: ");
                    String idDocente = scanner.nextLine();
                    
                    System.out.println("¡Grupo " + nombreGrupo + " registrado con éxito para la materia " + idMateria + "!");
                    break;
                    
                case 2:
                    System.out.println("\n--- LISTA DE GRUPOS ---");
                    System.out.println("-> (Aquí se mostrarán los grupos registrados y sus detalles)");
                    break;
                    
                case 3:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                    
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }
}
