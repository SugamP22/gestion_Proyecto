package gestionProyectos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Scanner sl = new Scanner(System.in);
    private static Empresa empresa = new Empresa();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            menu();
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea para evitar problemas con nextLine()
                switchMain(opcion);
            } catch (InputMismatchException e) {
                System.out.println("|| Entrada Inválida ||");
                sc.next(); // Limpiar buffer
            }
        } while (opcion != 6);
        System.out.println("Gracias por su participación!!");
    }

    private static void switchMain(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("*** Agregar Proyecto ***");
                agregarProyecto();
                break;
            case 2:
                System.out.println("*** Eliminar Proyecto ***");
                eliminarProyecto();
                break;
            case 3:
                System.out.println("*** Listar Proyectos ***");
                listarProyecto();
                break;
            case 4:
                System.out.println("*** Filtrar por Estado o Prioridad ***");
                filtrarProyectos();
                break;
            case 5:
                System.out.println("*** Actualizar Estado del Proyecto ***");
                actualizarEstadoProyecto();
                break;
            case 6:
                System.out.println("\033[46mSaliendo...\033[0m");
                break;
            default:
                System.out.println("Ingresa un número entre 1-6 ❤️❤️❤️");
                break;
        }
    }

    private static void listarProyecto() {
        empresa.listProyecto();
    }

    private static void eliminarProyecto() {
        System.out.print("Introduce el nombre del Proyecto: ");
        String nombre = sl.nextLine();
        if (empresa.eliminarProyecto(nombre)) {
            System.out.println("|| Proyecto eliminado con éxito ✌️✌️✌️ ||");
        } else {
            System.out.println("No existe ningún Proyecto con este nombre !! ");
        }
    }

    private static void filtrarProyectos() {
        boolean flag = false;
        System.out.println("=====================");
        System.out.println("Elige una opción");
        System.out.println("1. Estado");
        System.out.println("2. Prioridad");
        
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        if (opcion == 1) {
            while (!flag) {
                System.out.print("Introduce el estado (NO_INICIADO, ENPROGRESO, COMPLETADO): ");
                String estado = sl.nextLine().toUpperCase();
                try {
                    EstadoProyecto p = EstadoProyecto.valueOf(estado);
                    empresa.filtrarPorEstado(p);
                    flag = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("|| Introduce un valor válido (NO_INICIADO, ENPROGRESO, COMPLETADO) ||");
                }
            }
        } else {
            while (!flag) {
                System.out.print("Introduce la prioridad (BAJA, MEDIA, ALTA): ");
                String prioridad = sl.nextLine().toUpperCase();
                try {
                    Prioridad p = Prioridad.valueOf(prioridad);
                    empresa.filtrarPorPrioridad(p);
                    flag = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("|| Introduce un valor válido (BAJA, MEDIA, ALTA) ||");
                }
            }
        }
    }

    private static void agregarProyecto() {
        boolean flag = false;
        while (!flag) {
            System.out.print("Ingresa un nombre del Proyecto: ");
            String nombre = sl.nextLine();
            System.out.print("Ingresa Tipo de Prioridad (BAJA, MEDIA, ALTA): ");
            String prioridad = sl.nextLine().toUpperCase();
            try {
                Prioridad p1 = Prioridad.valueOf(prioridad);
                empresa.agregarProyectos(new Proyecto(nombre, p1));
                flag = true;
                System.out.println("Proyecto añadido con éxito 😊😊 !!");
            } catch (IllegalArgumentException e) {
                System.out.println("*********************************************");
                System.out.println("|| Valores válidos: Prioridad (BAJA, MEDIA, ALTA) ||");
                System.out.println("*********************************************");
            }
        }
    }

    private static void actualizarEstadoProyecto() {
        System.out.print("Introduce el nombre del Proyecto a actualizar: ");
        String nombre = sl.nextLine();
        Proyecto proyecto = empresa.buscarProyecto(nombre);
        if (proyecto != null) {
            proyecto.avanzarEstado();
            System.out.println("Estado actualizado con éxito! Nuevo estado: " + proyecto.getEstado());
        } else {
            System.out.println("No se encontró un proyecto con ese nombre.");
        }
    }

    private static void menu() {
        System.out.println(":::: Menú Principal ::::");
        System.out.println("1. Agregar Proyecto ");
        System.out.println("2. Eliminar Proyecto");
        System.out.println("3. Listar Proyectos");
        System.out.println("4. Filtrar Proyectos");
        System.out.println("5. Actualizar Estado del Proyecto");
        System.out.println("6. Salir");
        System.out.print(">>>>>>>>>> ");
    }
}
