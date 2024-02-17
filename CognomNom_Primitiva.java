import java.util.Scanner;
import java.util.Random;

/**
 * Programa de simulació de La Primitiva
 * @auhor // Ivan Vallejo i Pol Cubo
 * @version 1.0
 * @date //TODO: data
 */
//TODO: Fer refractor per canviar el nom de la classe
public class CognomNom_Primitiva {
    /**
     * Mètode main executable
     * @param args
     * @since 1.0
     */
    public static void main(String[] args) {
        menuPrincipal();
    }

    /**
     * // Menú principal del programa
     * @since 1.0
     */
    private static void menuPrincipal(){
        System.out.println("***** PRIMITIVA ******");

        int[] aposta = introduirAposta();
        int[] combinacioGuanyadora = calcularCombinacioGuanyadora();
        int premi;

        if (combinacioGuanyadora != null) {
            System.out.println("La combinació ganadora és: ");

            for (int i = 0; i < combinacioGuanyadora.length - 1; i++) {
                System.out.print(combinacioGuanyadora[i] + " ");
            }

            System.out.println("Reintegrament " + combinacioGuanyadora[combinacioGuanyadora.length - 1]);
        }

        premi = comprovarEncerts(aposta, combinacioGuanyadora);
        System.out.println("El teu premi és: "+premi+" €");
    }

    /**
     * //Introduir aposta
     * @return // Retorna un array de 7 posicions amb els números de l'aposta i el reintegrament
     * @since 1.0
     */
    private static int[] introduirAposta(){
        System.out.println("Introdueix la teva aposta: ");
        int[] aposta = new int[7];
        for (int i = 0; i < 6; i++) {
            int numeroIngresado;
           boolean repetido;
            do {
                numeroIngresado = llegirInt("Introdueix el número " + (i + 1) + ": ", 1, 49);
                repetido = false;

                // Comprobar si el número ya ha sido ingresado
                for (int j = 0; j < i; j++) {
                    if (aposta[j] == numeroIngresado) {
                        repetido = true;
                        System.out.println("Aquest número ja ha estat introduït. Torna a provar.");
                        break;
                    }
                }

            } while (repetido);

            aposta[i] = numeroIngresado;
        }
        aposta[6] = llegirInt("Introdueix el reintegrament: ", 0, 9);

        return aposta;
    }

    /**
     * //TODO: Completar
     * @return //TODO: Completar
     * @since 1.0
     */
    private static int[] calcularCombinacioGuanyadora(){
        Random random = new Random();
        int[] combinacio = new int[7];

        for (int i=0; i<6; i++) {
            combinacio[i] = random.nextInt(49)+1;
        }

        int reintegrament = random.nextInt(9);
        combinacio[6] = reintegrament;

        //TODO: Fer el codi del mètode

        return combinacio;
    }

    /**
     * // Comprovar encerts
     * @param aposta // Array amb la combinació de l'aposta
     * @param combinacioGuanyadora // Array amb la combinació guanyadora
     * @return // Retorna el premi a cobrar
     * @since 1.0
     */
    private static int comprovarEncerts(int[] aposta, int[] combinacioGuanyadora){
        int premi = 0;
        int encerts = 0;
        boolean reintegrament = false;

        //Comprobar encerts a la combinació
        for (int i = 0; i < aposta.length - 1; i++) {
            for (int j = 0; j < combinacioGuanyadora.length - 1; j++) {
                if (aposta[i] == combinacioGuanyadora[j]) {
                    encerts++;
                    break;
                }
            }
        }


        //Comprobar reintegrament
        if (aposta[6] == combinacioGuanyadora[6]) {
            reintegrament = true;
        }


        //Calcular premi
        if (encerts > 0) {
            premi += encerts * 20; // Sumar premio por aciertos en la apuesta principal
        }

        // Sumar premio por acertar el reintegro
        if (reintegrament) {
            premi += 6;
        }

        return premi;
    }

    /**
     * Aquest mètode llegeix un enter per teclat dins d'un domini determinat
     * @param missatge parametritzat per a mostrar a l'usuari@
     * @param min valor min acceptat
     * @param max valor max acceptat
     * @return retorna un int
     * @since 1.0
     */
    private static int llegirInt(String missatge, int min, int max) {
        Scanner llegir = new Scanner(System.in);
        int x = 0;
        boolean valorCorrecte = false;
        do{
            System.out.println(missatge);
            valorCorrecte = llegir.hasNextInt();
            if (!valorCorrecte){
                System.out.println("ERROR: Valor no enter.");
                llegir.nextLine();
            }else{ // Tinc un enter
                x = llegir.nextInt();
                llegir.nextLine();
                if (x < min || x > max){
                    System.out.println("Opció no vàlida");
                    valorCorrecte = false;
                }
            }
        }while(!valorCorrecte);

        return x;
    }

    /**
     * Aquest mètode serveix per capturar floats des de teclat amb control d'errors
     * @param missatge
     * @return
     * @since 1.0
     */
    private static float llegirFloat(String missatge){
        Scanner llegir = new Scanner(System.in);
        float x = 0;
        boolean valorCorrecte = false;
        do{
            System.out.print(missatge);
            valorCorrecte = llegir.hasNextFloat();

            if (!valorCorrecte){
                System.out.println("ERROR: Valor no float.");
                System.out.println("hola que tal");
            }else{
                x = llegir.nextFloat();
            }
            llegir.nextLine();
        }while(!valorCorrecte);

        return x;

    }

}
