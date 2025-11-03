
public class ProcesoD { //controlador de los 3 procesos
    public static void main(String[] args) { 
        if (args.length != 1) { //verifica que se haya pasado un argumento
            System.out.println("Uso: java Controlador <numero>"); //mensaje de uso 
            return; //termina la ejecución siin argumento
        }

        int n = Integer.parseInt(args[0]); //convierte el argumento a entero
        if (n < 1 || n >= 10) { //verifica que el número esté en el rango válido
            System.out.println("El número debe ser mayor que 0 y menor que 10"); //mensaje de error sino
            return;
        }

        for (int i = 0; i < n; i++) { //bucle para ejecutar la cadena n veces 
            try {
                String orden = "cmd /c start cmd /k \"java ProcesoA.java | java ProcesoB.java | java ProcesoC.java"; 
                //comando para abrir una nueva consola y ejecutar los 3 procesos en cadena
                // con tuberías, de forma que la salida de A pasa a B y luego a C

                String[] split = orden.split(" "); //divide el comando en "partes" para exec
                Runtime.getRuntime().exec(split); //ejecuta el comando y lanza los 3 procesos (A, B y C)
                

            } catch (Exception e) { 
                System.out.println("Error ejecutando procesos: " + e.getMessage()); //mensaje de error
            }
        }
    }
    }
