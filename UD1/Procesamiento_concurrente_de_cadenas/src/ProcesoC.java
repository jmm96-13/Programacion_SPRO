import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProcesoC { //Contador de vocales
    private static int contarVocales(String s) {
        int v = 0;
        for (char c : s.toCharArray()) { // Recorre cada carácter de la cadena
            switch (c) { // Comprueba si el carácter es una vocal
                case 'a': case 'e': case 'i': case 'o': case 'u':
                    v++; break; //si es vocal, incrementa el contador
            }
        }
        return v;
    }

    public static void main(String[] args) {
        int totalVocales = 0; // Contador total de vocales

        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            // Lee líneas desde la entrada estándar (proceso anterior)
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true)
            // Escribe la salida por consola (entrada estándar del proceso gestor)
        ) {
            String line;
            while ((line = br.readLine()) != null) { // Lee cada línea
                int idx = line.lastIndexOf(','); // Encuentra la última coma
                if (idx < 0) continue; // Si no hay coma, ignora la línea
                String cadena = line.substring(0, idx); // Extrae la cadena antes de la coma
                String lenStr = line.substring(idx + 1).trim(); // Extrae la longitud después de la coma
                int len;
                try { len = Integer.parseInt(lenStr); } // Convierte la longitud a entero
                catch (NumberFormatException e) { len = cadena.length(); } //si falla, usa la longitud real de la cadena

                int v = contarVocales(cadena); // Cuenta las vocales en la cadena
                totalVocales += v;
                out.printf("Cadena: \"%s\" | Caracteres: %d | Vocales: %d%n", cadena, len, v); // Imprime la información de la cadena
            }
            out.println("TOTAL_VOCALES=" + totalVocales); // Imprime el total de vocales contadas
        } catch (IOException e) {
            e.printStackTrace(); //Muestra un error si hay problema con la entrada o salida
        }
    }
}
