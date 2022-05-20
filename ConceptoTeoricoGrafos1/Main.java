import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // MatrizAdyacencia matriz = new MatrizAdyacencia(2, false);
        // System.out.println((matriz.valorarTipoEmparejamiento()));
        // MatrizAdyacencia grafo2 = new MatrizAdyacencia(4, false);
        // grafo2.insertarArista(0, 1, false);
        // grafo2.insertarArista(1, 2, false);
        // grafo2.insertarArista(2, 0, false);
        // grafo2.insertarArista(0, 3, false);
        // grafo2.insertarArista(1, 3, false);
        // grafo2.insertarArista(2, 3, false);
        Main main = new Main();
        MatrizAdyacencia matriz1 = new MatrizAdyacencia(4, false);
        // matriz1.insertarArista(0, 1, false);
        // matriz1.insertarArista(1, 2, false);
        // matriz1.insertarArista(2, 3, false);

        matriz1.insertarArista(0, 2, false);
        matriz1.insertarArista(2, 1, false);
        matriz1.insertarArista(2, 3, false);
        matriz1.insertarArista(1, 3, false);

        MatrizAdyacencia matriz2 = new MatrizAdyacencia(4, false);
        // matriz2.insertarArista(0, 2, false);
        // matriz2.insertarArista(2, 1, false);
        // matriz2.insertarArista(1, 3, false);
        // System.out.println(grafo2.esConexo());

        matriz2.insertarArista(0, 2, false);
        matriz2.insertarArista(0, 1, false);
        matriz2.insertarArista(0, 3, false);
        matriz2.insertarArista(1, 2, false);

        // System.out.println(main.sonIsomorfos(matriz1, matriz2));

        MatrizAdyacencia grafo1 = new MatrizAdyacencia(5, false);
        grafo1.insertarArista(0, 2, false);
        grafo1.insertarArista(0, 3, false);
        grafo1.insertarArista(2, 1, false);
        grafo1.insertarArista(3, 1, false);
        grafo1.insertarArista(1, 4, false);
        // grafo1.insertarArista(3, 4, false);

        MatrizAdyacencia grafo1Isomorfo = new MatrizAdyacencia(8, false);
        grafo1Isomorfo.insertarArista(0, 1, false);
        grafo1Isomorfo.insertarArista(1, 2, false);
        grafo1Isomorfo.insertarArista(2, 3, false);
        grafo1Isomorfo.insertarArista(3, 4, false);
        grafo1Isomorfo.insertarArista(4, 5, false);
        grafo1Isomorfo.insertarArista(5, 6, false);
        grafo1Isomorfo.insertarArista(6, 7, false);
        grafo1Isomorfo.insertarArista(7, 0, false);

        MatrizAdyacencia grafo2Isomorfo = new MatrizAdyacencia(7, false);
        grafo2Isomorfo.insertarArista(0, 3, false);
        grafo2Isomorfo.insertarArista(3, 6, false);
        grafo2Isomorfo.insertarArista(6, 2, false);
        grafo2Isomorfo.insertarArista(2, 5, false);
        grafo2Isomorfo.insertarArista(5, 1, false);
        grafo2Isomorfo.insertarArista(1, 4, false);
        grafo2Isomorfo.insertarArista(4, 0, false);

        //System.out.println("GRAFOS ISOMORFOS:");
        //System.out.println("GRAFOS 1:");
        //grafo1Isomorfo.dibujarGrafo();
        //System.out.println("GRAFOS 2:");
        //grafo2Isomorfo.dibujarGrafo();
        //System.out.println("Son isomorfos: ");
        //System.out.println(main.sonIsomorfos(grafo1Isomorfo, grafo2Isomorfo));

        System.out.println(matriz2.calcularNumeroCromatico(0));
    }

    //public ArrayList<Integer>[] representarListas(MatrizAdyacencia g) {
    //    int n = g.getNumVertices();
    //    ArrayList<Integer>[] listaG = new ArrayList[n];
    //    for (int i = 0; i < n; i++) {
    //        listaG[i] = new ArrayList<>();
//
    //        // realizar la conversion a listas y su llenado a listas tipo arraylist
    //        int j = 0;
    //        int[] adyacentesG1 = g.getAdyacentes(i);
    //        for (j = adyacentesG1.length - 1; j > -1; j--) {
    //            listaG[i].add(adyacentesG1[j]);
    //        }
    //    }
//
    //    return listaG;
    //}
//
    //public boolean sonIsomorfos(MatrizAdyacencia g1, MatrizAdyacencia g2) {
    //    int n = g1.getNumVertices();
    //    boolean respuesta = (g1.getNumVertices() == g2.getNumVertices());
//
    //    respuesta &= (g1.getNumAristas() == g2.getNumAristas());
    //    if (respuesta) {
//
    //        ArrayList<Integer>[] listaG1 = representarListas(g1);
    //        ArrayList<Integer>[] listaG2 = representarListas(g2);
//
    //        int[] relaciones = new int[n];
    //        // vaciamos las relaciones
    //        for (int i = 0; i < n; i++) {
    //            relaciones[i] = -1;
    //        }
//
    //        int i;
    //        int j;
    //        // en este espacio llenamos las relaciones(puede que este mal llenado)
    //        for (j = 0; (j < n) && respuesta; j++) {
    //            int ordenV1 = listaG1[j].size();
    //            respuesta = false;
    //            for (i = 0; i < n && !respuesta; i++) {
    //                respuesta = (ordenV1 == listaG2[i].size());
    //                if (respuesta) {
    //                    int k = 0;
    //                    while ((k < j) && respuesta) {
    //                        respuesta = (relaciones[k] != i);
    //                        k++;
    //                    }
    //                }
    //            }
    //            if (respuesta) {
    //                relaciones[j] = i - 1;
    //            }
    //        }
    //        for (i = 0; i < relaciones.length; i++) {
    //            System.out.print(relaciones[i] + ", ");
    //        }
    //        System.out.println();
//
    //        i = j = 0;
    //        while ((i < relaciones.length) && respuesta) {
    //            // recorrido para el grafo 1
    //            ArrayList<Integer> adyacentesG1 = listaG1[i];
    //            n = adyacentesG1.size();
    //            ArrayList<Integer> adyacentesG2 = listaG2[i];
//
    //            for (j = 0; j < n; j++) {
    //                int relacion = relaciones[adyacentesG1.get(j)];
    //                if (!adyacentesG2.contains(relacion)) {
    //                    int k = i;
    //                    while ((k < adyacentesG1.size()) && respuesta) {
    //                        respuesta = (adyacentesG2.contains(relaciones[k]));
    //                        respuesta &= (listaG2[relacion].size() == listaG2[relaciones[k]].size());
    //                        k++;
    //                    }
    //                    if (!respuesta) {
    //                        int auxiliar = relaciones[k];
    //                        relaciones[k] = relacion;
    //                        relaciones[adyacentesG1.get(j)] = auxiliar;
    //                    }
    //                }
    //            }
    //            i++;
    //        }
    //        for (i = 0; i < relaciones.length; i++) {
    //            System.out.print(relaciones[i] + ", ");
    //        }
    //    }
    //    return respuesta;
    //}

    // hacer si el grafo es biipartito.
}
