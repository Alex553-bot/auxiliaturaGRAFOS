import java.util.HashMap;
import java.util.ArrayList;

/**
 * IMPORTANTE: mejorar nombres de todos los metodos!!
 */

public class MatrizAdyacencia {
    private int matrix[][];
    private HashMap<Integer, ArrayList<Peso>> pesoAristas;

    public MatrizAdyacencia(int n, boolean ponderado) {
        matrix = new int[n][n];
        if (ponderado) {
            pesoAristas = new HashMap<>();
        }
    }

    public MatrizAdyacencia() {
        // en este constructor debemos poder admitir alguna entrada para poder convertir
        // a un objeto de MatrizAdyacencia
    }

    public int getNumVertices() {
        return matrix.length;
    }

    public int getNumAristas() {
        int numAristas = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                numAristas += matrix[i][j];
            }
        }
        return (numAristas / 2);
    }

    public boolean existeArista(int origen, int destino) {
        return (matrix[origen][destino] > 0);
    }

    // verificar?
    public double getPesoArista(int i, int j) {
        double peso = 0;
        if (pesoAristas != null) {

        }
        return peso;
    }

    public void insertarArista(int i, int j, boolean dirigido) {
        if ((pesoAristas == null)) {
            if (((i != j) || dirigido)) {
                matrix[i][j]++;
                if (!dirigido) {
                    matrix[j][i]++;
                }
            } else {
                insertarArista(i, j, true);
            }
        }
    }

    // Un grafo ponderado, pesado o con costos es un grafo donde cada arista tiene
    // asociado un
    // valor o etiqueta, para representar el costo, peso, longitud, etc
    public void insertarArista(int i, int j, boolean dirigido, double peso) {
        if (pesoAristas != null && ((i != j) || dirigido)) {
            matrix[i][j]++;
            Peso p = new Peso(i, j, peso);
            matrix[i][j]++;
            ArrayList<Peso> lista = pesoAristas.get(i);
            if (lista == null) {
                lista = new ArrayList<>();
            }
            lista.add(p);
            pesoAristas.put(i, lista);

            if (!dirigido) {
                insertarArista(j, i, true, peso);
            }
        } else if (i == j) {
            insertarArista(i, j, true, peso);
        }
    }

    public int[] getAdyacentes(int vertice) {
        int auxiliar = calcularOrdenVertice(vertice);

        int[] adyacentes = new int[auxiliar];
        auxiliar--;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertice][i] > 0) {
                adyacentes[auxiliar] = i;
                auxiliar--;
            }
        }

        return adyacentes;
    }/// valor de retorno puede ser diferente

    public void dibujarGrafo() {
        if (pesoAristas == null) {
            System.out.println(dibujarGrafoSinPeso());
        } else {
            System.out.println(dibujarGrafoConPeso());
        }
    }

    private String dibujarGrafoSinPeso() {
        String respuesta = "GRAFO:\n";
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            respuesta += "\tVertice: " + i + "\n\t\t";

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    for (int k = 0; k < matrix[i][j]; k++) {
                        respuesta += "[" + i + ", " + j + "] ";
                    }
                }
            }
            respuesta += "\n";
        }
        return respuesta;
    }

    private String dibujarGrafoConPeso() {
        String respuesta = "GRAFO:\n";
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            respuesta += "\tVertice: " + i + "\n\t\t";
            ArrayList<Peso> pesos = pesoAristas.get(i);
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    for (Peso p : pesos) {
                        if (p.getVertice2() == j) {
                            respuesta += "[" + i + ", " + j + ", " + p.getPeso() + "]";
                        }
                    }
                }
            }
            respuesta += "\n";
        }
        return respuesta;
    }

    public boolean quitarArista(int origen, int destino, boolean dirigido) {
        boolean respuesta = ((matrix[origen][destino] > 0) && (pesoAristas == null));

        if (respuesta) {
            matrix[origen][destino]--;
            if (!dirigido) {
                matrix[destino][origen]--;
            }
        }
        return respuesta;
    }

    public boolean quitarArista(int origen, int destino, boolean dirigido, double peso) {
        boolean respuesta = ((matrix[origen][destino] > 0) && (pesoAristas != null));

        if (respuesta) {

        }

        return respuesta;
    }

    public boolean esCompleto() {
        boolean respuesta = true;
        int i = 0;

        while (respuesta && (i < matrix.length)) {
            respuesta = (matrix[i][i] == 0);
            i++;
        }
        i = 0;
        int j = i + 1;

        while (respuesta && (i < matrix.length)) {
            while (respuesta && (j < matrix.length)) {
                respuesta = (matrix[i][j] == 1) && (matrix[j][i] == 1);
                j++;
            }
            i++;
            j = i + 1;
        }

        return respuesta;
    }

    public boolean esGrafoCiclo() {
        // cada vertice debe tener como maximo 2 aristas, y que podamos armar los
        // vertices.
        int[] ciclo = new int[matrix.length + 1];
        int pos = 0;
        int i, j;
        i = 0;
        // ciclo para representar celda vacia
        for (int k = 0; k < ciclo.length; k++) {
            ciclo[k] = -1;
        }
        for (j = 0; j < matrix.length; j++) {
            if (matrix[i][j] > 0) {
                calcularGrafoCiclo(ciclo, i, j, pos, 2);
                j = matrix.length;
            }
        }

        for (i = 0; i < ciclo.length; i++) {
            System.out.print(ciclo[i]);
        }
        return ciclo[ciclo.length - 1] != -1;
    }

    private void calcularGrafoCiclo(int[] ciclo, int i, int j, int pos, int nroA) {
        boolean respuesta = (pos < matrix.length && ciclo[ciclo.length - 2] == -1);

        i = 0;
        while (respuesta && (i < pos)) {
            respuesta = (ciclo[i] != j);
            i++;
        }
        respuesta = respuesta && (calcularOrdenVertice(j) <= nroA);

        if (respuesta) {
            ciclo[pos] = j;
            for (i = 0; i < matrix.length; i++) {
                if (matrix[j][i] > 0) {
                    calcularGrafoCiclo(ciclo, j, i, pos + 1, nroA);
                }
            }

            respuesta = (ciclo[ciclo.length - 1] == -1) && (pos == matrix.length - 1);
            respuesta = respuesta && (matrix[j][ciclo[0]] == 1);
            if (respuesta) {
                ciclo[pos + 1] = ciclo[0];
            }
        }
    }

    public boolean esCiclo2() {
        boolean[] ciclo = new boolean[matrix.length];
        return calcularGrafoCiclo2(ciclo, 2, 0);
    }

    // k -> hace referencia en que vertice vamos a iniciar, para no usarlo usar -1
    public boolean calcularGrafoCiclo2(boolean[] ciclo, int nroA, int k) {
        boolean esCiclo = (matrix.length > 2);
        int n = matrix.length;
        int aux = k;

        while (esCiclo) {
            ciclo[k] = true;
            int i = 0;
            while (esCiclo && (i < n)) {
                esCiclo = ciclo[i];
                i++;
            }
            if (esCiclo) {
                esCiclo = matrix[k][aux] > 0;
                return esCiclo;
            }

            if (calcularOrdenVertice(k) <= nroA) {
                for (int j = 0; !esCiclo && (j < n); j++) {
                    esCiclo = !ciclo[j] && (matrix[k][j] > 0);
                    if (esCiclo) {
                        k = j;
                    }
                }
            }
        }
        return esCiclo;
    }

    public boolean esGrafoRueda2() {
        return false;
    }

    public boolean esGrafoRueda() {
        int n = matrix.length;
        boolean respuesta = (n > 3);

        if (respuesta) {
            int i = 0;
            int j = 0;
            do {
                do {
                    // revisar fila de un vertice
                    if (i != j) {
                        respuesta = (matrix[i][j] == 1);
                    }
                    j++;
                } while (respuesta && (j < n));
                i++;
                j = 0;
            } while (!respuesta && (i < n));
            if (!respuesta) {
                respuesta = true;
                i = j = 0;
                do {
                    do {
                        // revisar columna de un vertice
                        if (i != j) {
                            respuesta = (matrix[j][i] == 1);
                        }
                        j++;
                    } while (respuesta && (j < n));
                    i++;
                    j = 0;
                } while (!respuesta && (i < n));
            }
            if (respuesta) {
                boolean[] ciclo = new boolean[n];

                int auxiliar = (int) (Math.random() * (n));
                ciclo[i - 1] = true;
                while (auxiliar == (i - 1)) {
                    auxiliar = (int) (Math.random() * (n - 1));
                }
                respuesta = calcularGrafoCiclo2(ciclo, 3, auxiliar);
            }

        }

        return respuesta;
    }

    public boolean existeBucle() {
        boolean respuesta = false;

        int i = 0;
        while (!respuesta && (i < matrix.length)) {
            respuesta = (matrix[i][i] > 0);
            i++;
        }

        return respuesta;
    }

    public int calcularOrdenVertice(int vertice) {
        int resultado = 0;
        for (int i = 0; i < matrix.length; i++) {
            resultado += matrix[vertice][i];
        }
        return resultado;
    }
}