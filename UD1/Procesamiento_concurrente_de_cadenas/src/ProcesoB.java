import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProcesoB { //Contador de caracteres
    public static void main(String[] args) {
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            // BufferedReader lee las líneas que llegan del Proceso A (por la entrada estándar)
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true)
            // PrintWriter envía texto por la salida estándar hacia el Proceso C
            // true activa el auto-flush para mandar cada línea
        ) {
            String line; //Lee cada cadena generada por el Proceso A
            while ((line = br.readLine()) != null) {
                int count = line.length(); // Cuenta los caracteres de la cadena
                out.println(line + "," + count); // Envía la cadena y su longitud al Proceso C separadas por una coma
            }
        } catch (IOException e) { 
            e.printStackTrace(); //Muestra un error si hay problema con la entrada o salida
        }
    }
}
