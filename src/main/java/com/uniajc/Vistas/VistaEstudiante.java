package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaEstudiante {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenuEstudiantes() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE ESTUDIANTES ---");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Listar Estudiantes");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("-> Aquí pedirás nombre, apellido y email del estudiante.");
                    break;
                case 2:
                    System.out.println("-> Aquí se mostrará la lista de estudiantes.");
                    break;
                case 3:
                    System.out.println("Saliendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }
}