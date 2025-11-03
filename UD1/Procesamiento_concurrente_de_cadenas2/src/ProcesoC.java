import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcesoC { //contador de vocales 

    public static void main(String[] args) {
        String vocales = "aeiou";
        int totalVocales = 0; //contador total de vocales en todas las cadenas
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        //lector de la entrada estandar por el Proceso B

        try {
            String linea;

            while ((linea = br.readLine()) != null) { //lee línea por línea hasta el final de la entrada
                int posComa = linea.lastIndexOf(',');  //encuentra la posición de la última coma
                String texto = linea.substring(0, posComa); //extrae el texto antes de la coma
                String numStr = linea.substring(posComa + 1); //extrae el número después de la coma

                int longitud; //longitud de la cadena

                try {
                    longitud = Integer.parseInt(numStr.trim()); //convierte el número a entero
                } catch (NumberFormatException e) { //maneja error de formato
                    longitud = texto.length(); //si hay error, usa la longitud del texto
                }

                int contVocales = 0; //contador de vocales en la cadena actual
                for (int i = 0; i < texto.length(); i++) { //recorre cada caracter de la cadena
                    char c = texto.charAt(i); //obtiene el caracter en la posición i
                    if (vocales.indexOf(Character.toLowerCase(c)) != -1) { //verifica si es vocal
                        contVocales++; // Si el carácter es vocal (minúscula o mayúscula), aumenta el contador
                    }
                }

                totalVocales += contVocales; //suma al total de vocales

                System.out.println("Cadena: \"" + texto + "\" - Longitud: " + longitud + " - Vocales: " + contVocales); 
                //imprime el resultado 
            }

            System.out.println("Total de vocales contadas: " + totalVocales); //imprime el total de vocales contadas

        } catch (IOException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
    }
}
/*El proceso C recibe los datos del proceso B en el formato cadena,longitud.
Separa ambos valores, analiza la cadena carácter por carácter y cuenta las vocales.
Muestra la cantidad de vocales de cada palabra y el total acumulado*/
