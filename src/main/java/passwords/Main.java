package passwords;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;
// RandomStringUtils permite generar cadenas aleatorias: numéricas, alfanuméricas,
// o usando conjuntos personalizados de caracteres.

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n------ GENERADOR DE CONTRASEÑAS ------");
            System.out.println("1. Generar PIN aleatorio para SIM (4 dígitos)");
            System.out.println("2. Generar contraseña alfanumérica (8 caracteres)");
            System.out.println("3. Generar secuencia de N caracteres personalizados");
            System.out.println("4. Salir");

            // ==========================
            // LECTURA SEGURA DE OPCIÓN
            // ==========================
            boolean entradaCorrecta = false;
            while (!entradaCorrecta) {
                try {
                    System.out.print("Seleccione una opción: ");
                    opcion = sc.nextInt();   // Puede lanzar InputMismatchException
                    entradaCorrecta = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Debe ingresar un número válido.");
                    sc.nextLine(); // Limpia el buffer para evitar bucle infinito
                }
            }

            switch (opcion) {
                case 1:
                    String pin = generarPinSIM();
                    System.out.println("PIN generado: " + pin);
                    break;

                case 2:
                    String password = generarPasswordAlfanumerica();
                    System.out.println("Contraseña generada: " + password);
                    break;

                case 3:
                    int n = 0;
                    boolean numeroCorrecto = false;

                    // ===================================
                    // LECTURA SEGURA DEL NÚMERO "n"
                    // ===================================
                    while (!numeroCorrecto) {
                        try {
                            System.out.print("Ingrese el número de caracteres a generar: ");
                            n = sc.nextInt();  // También puede lanzar InputMismatchException
                            numeroCorrecto = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número válido.");
                            sc.nextLine(); // Limpiar entrada inválida
                        }
                    }

                    String secuencia = generarSecuenciaPersonalizada(n);
                    System.out.println("Secuencia generada: " + secuencia);
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }

    // =======================================================
    // MÉTODO 1: Generar PIN de 4 dígitos
    // =======================================================
    public static String generarPinSIM() {
        // secure() → genera cadenas con un generador seguro criptográficamente
        RandomStringUtils generador = RandomStringUtils.secure();

        // nextNumeric(4) → genera un string de 4 números aleatorios
        return generador.nextNumeric(4);
    }

    // =======================================================
    // MÉTODO 2: Generar contraseña alfanumérica de 8 caracteres
    // =======================================================
    public static String generarPasswordAlfanumerica() {
        RandomStringUtils generador = RandomStringUtils.secure();

        // nextAlphanumeric(8) → genera un string con letras y números
        // toUpperCase() → lo convierte todo a mayúsculas
        return generador.nextAlphanumeric(8).toUpperCase();
    }

    // =======================================================
    // MÉTODO 3: Generar secuencia "n" de caracteres desde array personalizado
    // =======================================================
    public static String generarSecuenciaPersonalizada(int n) {

        // Array EXACTO de 10 caracteres como pide el enunciado
        char[] conjunto = {'A', 'B', 'C', '1', '2', '3', 'X', 'Y', 'Z', '9'};

        RandomStringUtils generador = RandomStringUtils.secure();

        // next(n, conjunto) → genera una cadena usando SOLO los caracteres del array
        return generador.next(n, conjunto);
    }
}
