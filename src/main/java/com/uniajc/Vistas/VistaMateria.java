package com.uniajc.Vistas;

import java.util.Scanner;

public class VistaMateria {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenuMaterias() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE MATERIAS ---");
            System.out.println("1. Registrar Materia");
            System.out.println("2. Listar Materias");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            // Validación básica para evitar que el programa se rompa si escriben letras en vez de números
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
                System.out.print("Seleccione una opción: ");
            }
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea pendiente del buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVA MATERIA ---");
                    System.out.print("Ingrese el nombre de la materia: ");
                    String nombreMateria = scanner.nextLine();
                    
                    System.out.print("Ingrese el número de créditos: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Debe ingresar un número entero para los créditos.");
                        scanner.next();
                        System.out.print("Ingrese el número de créditos: ");
                    }
                    int creditos = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer otra vez
                    
                    System.out.println("¡Materia registrada con éxito: " + nombreMateria + " (" + creditos + " créditos)!");
                    break;
                    
                case 2:
                    System.out.println("\n--- LISTA DE MATERIAS ---");
                    System.out.println("-> (Aquí se mostrarán las materias consultadas de la base de datos)");
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