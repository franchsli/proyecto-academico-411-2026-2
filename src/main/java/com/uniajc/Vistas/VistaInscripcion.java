package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaInscripcion {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenuInscripciones() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE INSCRIPCIONES Y NOTAS ---");
            System.out.println("1. Inscribir Estudiante a Grupo");
            System.out.println("2. Registrar / Actualizar Nota");
            System.out.println("3. Listar Inscripciones");
            System.out.println("4. Volver al Menú Principal");
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
                    System.out.println("\n--- NUEVA INSCRIPCIÓN ---");
                    System.out.print("Ingrese el documento del estudiante: ");
                    String documentoEstudiante = scanner.nextLine();
                    
                    System.out.print("Ingrese el código del grupo a inscribir: ");
                    String codigoGrupo = scanner.nextLine();
                    
                    System.out.println("¡Estudiante " + documentoEstudiante + " inscrito con éxito en el grupo " + codigoGrupo + "!");
                    break;
                    
                case 2:
                    System.out.println("\n--- REGISTRAR NOTA ---");
                    System.out.print("Ingrese el ID de la inscripción o matrícula: ");
                    String idInscripcion = scanner.nextLine();
                    
                    System.out.print("Ingrese la calificación definitiva: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Por favor, ingrese un número válido para la nota.");
                        scanner.next();
                        System.out.print("Ingrese la calificación definitiva: ");
                    }
                    double nota = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer
                    
                    System.out.println("¡Nota de " + nota + " registrada correctamente para la inscripción " + idInscripcion + "!");
                    break;
                    
                case 3:
                    System.out.println("\n--- LISTA DE INSCRIPCIONES ---");
                    System.out.println("-> (Aquí se mostrarán las inscripciones y notas registradas en el sistema)");
                    break;
                    
                case 4:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                    
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
}