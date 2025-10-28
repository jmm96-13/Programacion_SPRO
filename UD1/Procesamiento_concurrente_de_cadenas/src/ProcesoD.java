import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProcesoD { //gestor de procesos A, B y C con tuberías

    // Copia bytes de in a out hasta que alcanza el final del archivo-Cierra out al terminar
    private static Thread pump(InputStream in, OutputStream out) {
        Thread t = new Thread(() -> { // Crea un hilo independiente
            try (in; out) { // Cierra ambos flujos al terminar
                byte[] buf = new byte[8192];
                int n;
                while ((n = in.read(buf)) != -1) { // Lee datos del flujo de entrada
                    out.write(buf, 0, n); // Escribe los bytes leídos en la salida
                    out.flush(); // Fuerza el envío inmediato
                }
            } catch (IOException ignored) {} //ignora errores de E/S
        });
        t.setDaemon(true); // Hilo demonio (no bloquea el cierre del programa)
        t.start(); // Inicia el hilo
        return t; // Devuelve el hilo creado
    }

    // Lee un flujo de texto línea por línea y lo muestra con un prefijo identificador
    private static Thread consumeWithPrefix(InputStream in, String prefix, boolean toErr) {
        Thread t = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) { // Lectura de texto con codificación UTF-8
                String line;
                while ((line = br.readLine()) != null) { // Procesa línea por línea
                    String msg = prefix + line; // Añade prefijo al mensaje
                    if (toErr) System.err.println(msg); // Si es error, muestra por salida de error
                    else System.out.println(msg); // Si no, muestra por salida estándar
                }
            } catch (IOException ignored) {} // Ignora posibles errores de lectura
        });
        t.setDaemon(true); //hilo demonio
        t.start(); // Inicia el hilo
        return t; // Devuelve el hilo creado
    }

    private static String[] cmd(String s) { return s.split(" "); } // Separa un comando en argumentos individuales

    public static void main(String[] args) throws Exception {
        if (args.length != 1) { // Verifica que se pase solo un argumento
            System.err.println("Uso: java ProcesoD <N> (1 <= N < 10)");
            System.exit(1);
        }
        int n;
        try { n = Integer.parseInt(args[0]); } // Convierte el argumento a entero
        catch (NumberFormatException e) {
            System.err.println("El argumento debe ser entero.");
            return;
        }
        if (n <= 0 || n >= 10) {
            System.err.println("El argumento debe ser positivo y menor que 10");
            return;
        }

        Runtime rt = Runtime.getRuntime(); // Permite ejecutar procesos externos
        List<Process> procesos = new ArrayList<>(); // Lista para almacenar los procesos A, B y C
        List<Thread> hilos = new ArrayList<>(); // Lista para los hilos de conexión y salida

        for (int i = 1; i <= n; i++) { // Crea y gestiona n conjuntos de procesos A-B-C
            final int id = i;

            // Ejecuta los JARs en el directorio actual
            Process pA = rt.exec(cmd("java -jar ProcesoA.jar"));
            Process pB = rt.exec(cmd("java -jar ProcesoB.jar"));
            Process pC = rt.exec(cmd("java -jar ProcesoC.jar"));

            procesos.add(pA); procesos.add(pB); procesos.add(pC); // Guarda los procesos para control posterior

            // Conexión de tuberías
            hilos.add(pump(pA.getInputStream(), pB.getOutputStream())); // Conecta salida de A con entrada de B
            hilos.add(pump(pB.getInputStream(), pC.getOutputStream())); // Conecta salida de B con entrada de C

            // Muestra la salida de C y los errores de A, B y C
            hilos.add(consumeWithPrefix(pC.getInputStream(), "[P" + id + "] ", false)); // Salida normal de C
            hilos.add(consumeWithPrefix(pA.getErrorStream(), "[P" + id + "][A-ERR] ", true)); // Errores de A
            hilos.add(consumeWithPrefix(pB.getErrorStream(), "[P" + id + "][B-ERR] ", true)); // Errores de B
            hilos.add(consumeWithPrefix(pC.getErrorStream(), "[P" + id + "][C-ERR] ", true)); // Errores de C
        }

        // Espera a que terminen todos los procesos
        for (Process p : procesos) p.waitFor(); // Bloquea hasta que cada proceso finalice
        for (Thread t : hilos) t.join(100); // Espera un poco para vaciar los buffers restantes
    }
}