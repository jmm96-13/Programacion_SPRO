import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ProcesoB { //contador de caracteres
    public static void main(String[] args) {
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
            //lector de la entrada estandar BufferedReader lee las líneas que llegan del Proceso A
             PrintWriter escritor = new PrintWriter(System.out, true)) { 
            //escritor de la salida estandar, PrintWriter escribe las líneas procesadas para el Proceso C

            String linea; //variable para almacenar cada línea leída
            while ((linea = lector.readLine()) != null) { 
                //lee línea por línea hasta el final de la entrada, hasta que no haya más datos
                int longitud = linea.length(); //calcula la longitud de la línea
                escritor.println(linea + "," + longitud); //escribe la línea original separada por una coma
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.err); //muestra errores de escritura o lectura
        }
    }
}
//Lee las cadenas del Proceso A con BufferedReader y System.in, cuenta su longitud y reenvía “cadena,longitud” al Proceso C.
