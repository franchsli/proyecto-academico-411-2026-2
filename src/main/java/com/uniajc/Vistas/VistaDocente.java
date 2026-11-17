package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaDocente {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenuDocentes() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE DOCENTES ---");
            System.out.println("1. Registrar Docente");
            System.out.println("2. Listar Docentes");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO DOCENTE ---");
                    System.out.print("Ingrese el nombre del docente: ");
                    String nombre = scanner.nextLine();
                    
                    System.out.print("Ingrese la especialidad del docente: ");
                    String especialidad = scanner.nextLine();
                    
                    System.out.println("¡Docente registrado con éxito: " + nombre + " (" + especialidad + ")!");
                    break;
                    
                case 2:
                    System.out.println("\n--- LISTA DE DOCENTES ---");
                    System.out.println("-> (Aquí se mostrarán los docentes consultados de la base de datos)");
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