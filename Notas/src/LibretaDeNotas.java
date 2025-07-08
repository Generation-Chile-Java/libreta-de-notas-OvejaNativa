import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibretaDeNotas {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); //para que se ingresen datos en la consola

        HashMap<String, ArrayList<Integer>> Calificaciones = new HashMap<>();

        //solicitamos numero de estudiantes
        System.out.println("Digite cantidad de estudiantes");
        while(!teclado.hasNextInt()){
            System.out.println("número inválido, ingrese otro numero");
            teclado.next();
        }

        int NumeroDeEstudiantes = teclado.nextInt();
        teclado.nextLine();

        //Validación del número de alumnos sea positivo
        while (NumeroDeEstudiantes <= 0) {
            System.out.println("El número debe ser mayor que 0. Ingrese un número válido");
            while(!teclado.hasNextInt()){
                System.out.println("número inválido, ingrese un número mayor a 0");
                teclado.next();
            }
            NumeroDeEstudiantes = teclado.nextInt();
            teclado.nextLine();
        }
        System.out.println("Número total de estudiantes: " + NumeroDeEstudiantes);

        //Pedir cantidad de notas por estudiante
        System.out.println("ingrese cantidad de notas por estudiante");
        while(!teclado.hasNextInt()){
            System.out.println("número inválido, intente de nuevo");
            teclado.next();
        }
        int CantidadDeNotas = teclado.nextInt();
            teclado.nextLine();

            //validar que sea positivo
        while (CantidadDeNotas <= 0) {
            System.out.println("El número debe ser mayor que 0. Ingrese un número válido");
            while(!teclado.hasNextInt()){
                System.out.println("número inválido, ingrese un número mayor a 0");
                teclado.next();
            }
           CantidadDeNotas = teclado.nextInt();
            teclado.nextLine();
        }
        System.out.println("Número total de estudiantes es: " + NumeroDeEstudiantes + ". El número de notas por estudiante es: " + CantidadDeNotas);
        System.out.println("===============");

        //solicitar cantidad de estudiantes
        for (int i=0; i < NumeroDeEstudiantes; i++) {
            System.out.println("Ingrese el nombre de estudiante #" + (i + 1) + ":");
            String nombre = teclado.nextLine();
            ArrayList<Integer> ListaDeNotas = new ArrayList<>();

            for (int j = 0; j < CantidadDeNotas; j++) {
                System.out.println("Ingrese la nota #" + (j + 1) + " para " + nombre + " (0-7):");
                while(!teclado.hasNextInt()){
                    System.out.println("número no válido");
                    teclado.next();
                }
                int notaEstudiante = teclado.nextInt();
                teclado.nextLine();

                // Validación: nota entre 0 y 100
                while (notaEstudiante < 0 || notaEstudiante > 7) {
                    System.out.println("Nota inválida. Ingrese una nota entre 0 y 7:");
                    notaEstudiante = teclado.nextInt();
                    teclado.nextLine();
                }

                ListaDeNotas.add(notaEstudiante);
            }

            // Guardar en el HashMap
            Calificaciones.put(nombre, ListaDeNotas);
        }

        System.out.println("\nDatos registrados correctamente.\n");

        System.out.println("\n=== Resumen de notas ===");

// HashMap para guardar promedios
        HashMap<String, Double> Promedios = new HashMap<>();
        double sumaGeneral = 0;
        int totalNotas = 0;

        for (String nombreEstudiante : Calificaciones.keySet()) {
            ArrayList<Integer> notasDelEstudiante = Calificaciones.get(nombreEstudiante);

            int suma = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int nota : notasDelEstudiante) {
                suma += nota;

                if (nota > max) {
                    max = nota;
                }

                if (nota < min) {
                    min = nota;
                }
            }

            double promedio = (double) suma / notasDelEstudiante.size();

            Promedios.put(nombreEstudiante, promedio);

            System.out.println("Estudiante: " + nombreEstudiante);
            System.out.println("  Promedio: " + promedio);
            System.out.println("  Nota Máxima: " + max);
            System.out.println("  Nota Mínima: " + min);
            System.out.println("----------------------------");
        }
        double promedioCurso = sumaGeneral / totalNotas;
        System.out.println("\nPromedio general del curso: " + promedioCurso);

        // === 5. Menú interactivo ===
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Mostrar promedios por estudiante");
            System.out.println("2. Verificar si una nota es aprobatoria");
            System.out.println("3. Comparar nota con promedio del curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!teclado.hasNextInt()) {
                System.out.println("Opción inválida, intente de nuevo:");
                teclado.next();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Promedios ---");
                    for (String nombre : Promedios.keySet()) {
                        System.out.println(nombre + ": " + Promedios.get(nombre));
                    }
                    break;

                case 2:
                    System.out.println("Ingrese nombre del estudiante:");
                    String nombreCheck = teclado.nextLine();

                    if (!Calificaciones.containsKey(nombreCheck)) {
                        System.out.println("Estudiante no encontrado.");
                        break;
                    }

                    System.out.println("Ingrese nota a verificar:");
                    while (!teclado.hasNextInt()) {
                        System.out.println("Inválido. Intente de nuevo:");
                        teclado.next();
                    }
                    int notaVerificar = teclado.nextInt();
                    teclado.nextLine();

                    if (notaVerificar >= 4) {
                        System.out.println("La nota es APROBATORIA");
                    } else {
                        System.out.println("La nota es REPROBATORIA");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese nombre del estudiante:");
                    String nombreComparar = teclado.nextLine();

                    if (!Calificaciones.containsKey(nombreComparar)) {
                        System.out.println("Estudiante no encontrado.");
                        break;
                    }

                    System.out.println("Ingrese nota a comparar:");
                    while (!teclado.hasNextInt()) {
                        System.out.println("Inválido. Intente de nuevo:");
                        teclado.next();
                    }
                    int notaComparar = teclado.nextInt();
                    teclado.nextLine();

                    if (notaComparar > promedioCurso) {
                        System.out.println("La nota está SOBRE el promedio del curso");
                    } else {
                        System.out.println("La nota está DEBAJO del promedio del curso");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        System.out.println("Programa finalizado.");
        }


    }
