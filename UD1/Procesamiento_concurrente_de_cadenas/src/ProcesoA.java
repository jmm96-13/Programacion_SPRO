//import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class ProcesoA { //generador de cadenas
    public static void main(String[] args) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        PrintWriter pw = new PrintWriter(System.out, true); // PrintWriter envia texto por salida estándar (System.out)
                                                                      //true activa auto-flush y envia cada línea al Proceso B
                                                                      //Asi no necesito OutputStream os ya que System.out lo es
        for (int i = 0; i < 15; i++) { //Bucle para 15 cadenas
            int longitud = 5 + random.nextInt(11); //elige aleatoriamente la longitud de la cadena entre 5–15 
            StringBuilder sb = new StringBuilder(longitud); //constructor StringBuilder cadena carácter a carácter
            
            for (int j = 0; j < longitud; j++) { // Bucle que genera cada carácter aleatorio
                sb.append(alfabeto.charAt(random.nextInt(alfabeto.length()))); // se añade esa letra aleatoria a la cadena
            }
            pw.println(sb.toString()); //envía la cadena generada al proceso B por la salida estándar
        }
    }
}
