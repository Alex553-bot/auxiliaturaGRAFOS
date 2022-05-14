
/**
 * Clase para probar los diferentes ejemplos
 */

public class Main {
    public static void main(String[] args) {
        MatrizAdyacencia matriz = new MatrizAdyacencia(4, false);
        matriz.insertarArista(0, 1, false); 
        matriz.insertarArista(1, 2, false);
        matriz.insertarArista(2, 0, false);
        matriz.insertarArista(0, 3, false); 
        matriz.insertarArista(1, 3, false); 
        matriz.insertarArista(2, 3, false);
        matriz.insertarArista(3, 0, false);
        matriz.insertarArista(0, 0, true);
        
        //matriz.insertarArista(0, 1, false); 
        //matriz.insertarArista(0, 4, false); 
        //matriz.insertarArista(0, 3, false); 
        //matriz.insertarArista(1, 4, false); 
        //matriz.insertarArista(1, 2, false); 
        //matriz.insertarArista(2, 4, false); 
        //matriz.insertarArista(2, 3, false); 
        //matriz.insertarArista(3, 4, false); 

        //matriz.insertarArista(0, 1, true, 5);
        //matriz.insertarArista(0, 1, true, 4);
        //matriz.insertarArista(2, 1, true, 5);
        //matriz.insertarArista(3, 1, true, 6);
        //matriz.insertarArista(1, 3, true, 8);
        //matriz.insertarArista(3, 2, true, 12);
        //matriz.insertarArista(0, 0, true, 2);
        

        System.out.println(matriz.esCiclo2());
        //System.out.println(matriz.esCompleto());
        System.out.println(matriz.esGrafoRueda());
        matriz.dibujarGrafo();
    }
}
