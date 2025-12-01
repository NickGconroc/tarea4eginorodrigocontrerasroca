package passwords;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;

// En una clase con el método main, implementa una aplicación para
// generar contraseñas y números pin de móvil. La aplicación tendrá tres
// opciones: la primera, genera números pin aleatorios para tarjetas SIM 
// de teléfonos móviles; la segunda opción genera contraseñas para usuarios
// de un sistema operativo con 8 caracteres, incluyendo números y letras; la
// tercera opción, genera una secuencia de "n" caracteres aleatorios de un 
// conjunto que tú elijas (debes pasar un array de char de 10 elementos). El 
// número "n" se debe solicitar por teclado.
// El programa se repite hasta que el usuario decida salir.

public class Main {

    public static void main(String[] args) {

        Scanner tec = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("------ GENERADOR DE CONTRASEÑAS ------");
            System.out.println("1. Genera un PIN aleatorio para SIM (4 dígitos)");
            System.out.println("2. Genera una contraseña alfanumérica (8 caracteres)");
            System.out.println("3. Genera una secuencia de N caracteres personalizados");
            System.out.println("4. Salir");

            boolean entradaCorrecta = false;
            while (!entradaCorrecta) {
                try {
                    System.out.print("Seleccione una opción: ");
                    opcion = tec.nextInt(); // Puede lanzar InputMismatchException
                    entradaCorrecta = true;
                } catch (InputMismatchException ime) {
                    System.out.println("Error: Debe ingresar un número válido, no un texto");
                    tec.nextLine(); // Limpieza de buffer
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

                    while (!numeroCorrecto) {
                        try {
                            System.out.print("Ingrese el número de caracteres a generar: ");
                            n = tec.nextInt(); // Puede lanzar InputMismatchException
                            numeroCorrecto = true;
                        } catch (InputMismatchException ime) {
                            System.out.println("Error: Debe ingresar un número válido.");
                            tec.nextLine(); // Limpieza de buffer
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

        tec.close();
    }

    // Función 1: genera números pin aleatorios para tarjetas SIM de teléfonos
    // móviles

    public static String generarPinSIM() {
        // secure() → genera cadenas con un generador seguro criptográficamente
        RandomStringUtils generador = RandomStringUtils.secure(); // RandomStringUtils permite generar cadenas
                                                                  // aleatorias: numéricas, alfanuméricas,
        // o usando conjuntos personalizados de caracteres.

        // nextNumeric(4) → genera un string de 4 números aleatorios
        return generador.nextNumeric(4);
    }

    // Función 2: genera contraseñas para usuarios de un sistema operativo con 8
    // caracteres, incluyendo números y letras

    public static String generarPasswordAlfanumerica() {
        RandomStringUtils generador = RandomStringUtils.secure(); // RandomStringUtils permite generar cadenas
                                                                  // aleatorias: numéricas, alfanuméricas,
        // o usando conjuntos personalizados de caracteres.

        // nextAlphanumeric(8) → genera un string con letras y números
        // toUpperCase() → lo convierte todo a mayúsculas
        return generador.nextAlphanumeric(8).toUpperCase();
    }

    // Función 3: genera una secuencia de "n" caracteres aleatorios
    // de un conjunto que tú elijas (debes pasar un array de char de 10 elementos).
    // El número "n" se debe solicitar por teclado.

    public static String generarSecuenciaPersonalizada(int n) {

        // Array de 10 caracteres char
        char[] conjunto = { 'A', 'B', 'C', '1', '2', '3', 'X', 'Y', 'Z', '9' };

        RandomStringUtils generador = RandomStringUtils.secure(); // RandomStringUtils permite generar cadenas
                                                                  // aleatorias: numéricas, alfanuméricas,
        // o usando conjuntos personalizados de caracteres.

        // next(n, conjunto) → genera una cadena usando SOLO los caracteres del array
        return generador.next(n, conjunto);
    }
}
