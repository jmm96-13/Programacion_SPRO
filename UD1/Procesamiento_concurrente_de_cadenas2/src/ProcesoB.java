import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ProcesoB { //contador de caracteres
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
            //lector de la entrada estandar BufferedReader lee las líneas que llegan del Proceso A (por la entrada estándar)
             PrintWriter out = new PrintWriter(System.out, true)) { 
            //escritor de la salida estandar PrintWriter escribe las líneas procesadas para el Proceso C (por la salida estándar)

            String linea; //variable para almacenar cada línea leída
            while ((linea = in.readLine()) != null) { 
                //lee línea por línea hasta el final de la entrada
                int len = linea.length(); //calcula la longitud de la línea
                out.println(linea + "," + len); //escribe la línea original separada por una coma
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
//Lee las cadenas del Proceso A con BufferedReader y System.in, cuenta su longitud y reenvía “cadena,longitud” al Proceso C.
