package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private VistaEstudiante vistaEstudiante = new VistaEstudiante();
    private VistaDocente vistaDocente = new VistaDocente();

    public void mostrarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA - UNIAJC");
            System.out.println("========================================");
            System.out.println("1. Gestión de Estudiantes");
            System.out.println("2. Gestión de Docentes");
            System.out.println("3. Gestión de Materias (Próximamente)");
            System.out.println("4. Gestión de Grupos (Próximamente)");
            System.out.println("5. Inscripciones y Notas (Próximamente)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    vistaEstudiante.mostrarMenuEstudiantes();
                    break;
                case 2:
                    vistaDocente.mostrarMenuDocentes();
                    break;
                case 3:
                    System.out.println("\n[En desarrollo] Módulo de Materias.");
                    break;
                case 4:
                    System.out.println("\n[En desarrollo] Módulo de Grupos.");
                    break;
                case 5:
                    System.out.println("\n[En desarrollo] Módulo de Inscripciones.");
                    break;
                case 6:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }
}