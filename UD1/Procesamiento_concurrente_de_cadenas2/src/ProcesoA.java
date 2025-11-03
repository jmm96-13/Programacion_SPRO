/*//import java.io.OutputStream;
import java.io.PrintWriter;

public class ProcesoA { //generador de cadenas
    public static void main(String[] args) throws Exception { // 
        String abcedario = "abcdefghijklmnñopqrstuvwxyz";
        PrintWriter out = new PrintWriter(System.out, true); 
        //printwriter para escribir en la salida estandar System.out
        //true activa el auto-flush, que asegura que los datos se envien inmediatamente
        int totalCadenas = 15; 
        //numero total de cadenas a generar

        for (int i = 0; i < totalCadenas; i++) { //bucle para generar 15 cadenas
            int len = 5 + (int) (Math.random() * 11); //longitud aleatoria entre 5 y 15

            StringBuilder sb = new StringBuilder(len); //objeto para construir la cadena
            for (int j = 0; j < len; j++) { //bucle para generar cada caracter de la cadena
                int indice = (int) (Math.random() * abcedario.length()); //indice aleatorio en el abecedario
                sb.append(abcedario.charAt(indice)); //agrega el caracter al StringBuilder
            }
            out.println(sb.toString()); //escribe la cadena generada en la salida estandar
        }
    }
}
*/
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class ProcesoA { //generador de cadenas
    public static void main(String[] args) {
        String alfabeto = "abcdefghijklmnñopqrstuvwxyz";
        Random aleatorio = new Random(); // Generador de números aleatorios 
        PrintWriter salida = new PrintWriter(System.out, true); // PrintWriter envia texto por salida estándar (System.out)
                                                                      //true activa auto-flush y envia cada línea al Proceso B
                                                                      //Asi no necesito OutputStream os ya que System.out lo es
        for (int i = 0; i < 15; i++) { //Bucle para 15 cadenas
            int longitud = 5 + aleatorio.nextInt(11); //elige aleatoriamente la longitud de la cadena entre 5–15 
            StringBuilder palabra = new StringBuilder(longitud); //constructor StringBuilder cadena carácter a carácter
            
            for (int j = 0; j < longitud; j++) { // Bucle que genera cada carácter aleatorio
                palabra.append(alfabeto.charAt(aleatorio.nextInt(alfabeto.length()))); // se añade esa letra aleatoria a la cadena
            }
            salida.println(palabra.toString()); //envía la cadena generada al proceso B por la salida estándar
        }
    }
}

//Genera 15 cadenas aleatorias de letras (a–z), de longitud entre 5-15 caracteres, elegido por Random aleatorio.
