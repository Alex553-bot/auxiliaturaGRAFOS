
/**
 * Clase para probar los diferentes ejemplos
 */

public class Main {
    public static void main(String[] args) {
        // Grafo 1: 
        MatrizAdyacencia grafo1 = new MatrizAdyacencia(4, true);

        grafo1.insertarArista(0, 1, true, 3.0);
        grafo1.insertarArista(2, 0, true, 6.0);
        grafo1.insertarArista(2, 1, true, 9.0);
        grafo1.insertarArista(3, 0, true, 3.0);
        grafo1.insertarArista(3, 1, true, 2.0);
        grafo1.insertarArista(3, 2, true, 10.0);

        // Grafo 2:
        MatrizAdyacencia grafo2 = new MatrizAdyacencia(4, false);
        grafo2.insertarArista(0, 1, false);
        grafo2.insertarArista(1, 2, false);
        grafo2.insertarArista(2, 0, false);
        grafo2.insertarArista(0, 3, false);
        grafo2.insertarArista(1, 3, false);
        grafo2.insertarArista(2, 3, false);

        // Grafo 3: 
        MatrizAdyacencia grafo3 = new MatrizAdyacencia(5, false);
        grafo3.insertarArista(0, 1, false);        
        grafo3.insertarArista(0, 2, false);        
        grafo3.insertarArista(0, 3, false);        
        grafo3.insertarArista(0, 4, false);        
        grafo3.insertarArista(1, 2, false);        
        grafo3.insertarArista(1, 3, false);        
        grafo3.insertarArista(1, 4, false);        
        grafo3.insertarArista(2, 3, false);        
        grafo3.insertarArista(2, 4, false);        
        grafo3.insertarArista(3, 4, false);        
        
        
        System.out.print("Grafo 1 :");
        grafo1.dibujarGrafo();
        System.out.println("Numero de aristas dirigidas: " + grafo1.getNumAristas());
        System.out.println("Numero de vertices: " + grafo1.getNumVertices());
        System.out.println("Numero de aristas no dirigidos: "+ grafo1.getNroAristasNoDirigidos());
        System.out.println("Es grafo completo: " + grafo1.esCompleto());
        System.out.println("Es grafo ciclo: " + grafo1.esGrafoCiclo());
        System.out.println("Es grafo rueda: " + grafo1.esGrafoRueda());
        

        System.out.print("Grafo 2 :");
        grafo2.dibujarGrafo();
        System.out.println("Numero de aristas dirigidas: " + grafo2.getNumAristas());
        System.out.println("Numero de vertices: " + grafo2.getNumVertices());
        System.out.println("Numero de aristas no dirigidos: "+ grafo1.getNroAristasNoDirigidos());
        System.out.println("Es grafo completo: " + grafo2.esCompleto());
        System.out.println("Es grafo ciclo: " + grafo2.esGrafoCiclo());
        System.out.println("Es grafo rueda: " + grafo2.esGrafoRueda());

        System.out.print("Grafo 3 :");
        grafo3.dibujarGrafo();
        System.out.println("Numero de aristas dirigidas: " + grafo3.getNumAristas());
        System.out.println("Numero de vertices: " + grafo3.getNumVertices());
        System.out.println("Numero de aristas no dirigidos: "+ grafo1.getNroAristasNoDirigidos());
        System.out.println("Es grafo completo: " + grafo3.esCompleto());
        System.out.println("Es grafo ciclo: " + grafo3.esGrafoCiclo());
        System.out.println("Es grafo rueda: " + grafo3.esGrafoRueda());

    }
}
