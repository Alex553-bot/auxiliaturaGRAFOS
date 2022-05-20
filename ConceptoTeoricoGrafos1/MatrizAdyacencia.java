import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
//import java.util.Stack;

public class MatrizAdyacencia {
    private int matrix[][];
    private HashMap<Integer, ArrayList<Peso>> pesoAristas;

    public MatrizAdyacencia(int n, boolean ponderado) {
        matrix = new int[n][n];
        if (ponderado) {
            pesoAristas = new HashMap<>();
        }
    }

    public MatrizAdyacencia(int[][] matriz) throws Exception {
        if (matriz.length == matriz[0].length) {
            matrix = matriz;
        } else {
            throw new Exception("error matriz no valida, debe ser cuadrada");
        }
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
        // hace referencia al numero de aristas DIRIGIDAS.
        return (numAristas);
    }

    public int getNroAristasNoDirigidos() {
        int numAristas = getNumAristas();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if ((matrix[i][j] > 0) && (matrix[j][i] > 0)) {
                    numAristas = numAristas - Math.min(matrix[i][j], matrix[j][i]);
                }
            }
        }
        return numAristas;
    }

    public boolean existeArista(int origen, int destino) {
        return (matrix[origen][destino] > 0);
    }

    public double getPesoArista(int i, int j) {
        double peso = 0;
        if (pesoAristas != null) {
            ArrayList<Peso> pesos = pesoAristas.get(i);
            boolean bandera = (pesos != null);
            for (int k = 0; k < pesos.size() && bandera; k++) {
                Peso p = pesos.get(k);
                bandera = (p.getVertice2() == j);
                if (bandera) {
                    peso = p.getPeso();
                }
            }
        }
        return peso;
    }

    public void insertarArista(int i, int j, boolean dirigido) {
        if ((pesoAristas == null)) {
            if (((i != j) || dirigido)) {
                matrix[i][j]++;
                if (!dirigido) {
                    insertarArista(j, i, true);
                }
            } else {
                insertarArista(i, j, true);
            }
        }
    }

    public void insertarArista(int i, int j, boolean dirigido, double peso) {
        if (pesoAristas != null && ((i != j) || dirigido)) {
            matrix[i][j]++;
            Peso p = new Peso(i, j, peso);
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
                for (int j = 0; j < matrix[vertice][i]; j++) {
                    adyacentes[auxiliar] = i;
                    auxiliar--;
                }
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
            ArrayList<Peso> pesos = pesoAristas.get(origen);
            for (Peso p : pesos) {
                if (p.getPeso() == peso) {
                    matrix[origen][destino]--;
                    pesos.remove(p);
                }
            }
            if (!dirigido) {
                quitarArista(destino, origen, true);
            }
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
        boolean[] ciclo = new boolean[matrix.length];
        return calcularGrafoCiclo(ciclo, 2, 0);
    }

    private boolean calcularGrafoCiclo(boolean[] ciclo, int nroA, int k) {
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
                respuesta = calcularGrafoCiclo(ciclo, 3, auxiliar);
            }
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

    public int[][] getMatrizAdyacente() {
        return matrix;
    }

    // un grafo puede ser bipartito si es conexo y no dirigido.
    public boolean esBipartito(int pos) {
        int n = matrix.length;
        boolean respuesta = false;

        char[] secuencia = new char[n];
        respuesta = (n > 1);
        Stack<Integer> vertices = new Stack<>();
        vertices.push(pos);
        secuencia[pos] = 'A';
        while ((!vertices.empty()) && respuesta) {
            int actual = vertices.pop();
            char valor = 'A';
            if (secuencia[actual] == 'A') {
                valor++;
            }

            for (int j = 0; (j < n) && respuesta; j++) {
                if ((matrix[actual][j] > 0)) {
                    if (secuencia[j] == '\0') {
                        vertices.push(j);
                        secuencia[j] = valor;
                    }
                    respuesta = (secuencia[j] != secuencia[actual]);
                }
            }
        }

        for (int k = 0; (k < n) && respuesta; k++) {
            respuesta = (secuencia[k] != '\0');
        }

        return respuesta;
    }

    public int calcularNumeroCromatico(int pos) {
        int resultado = 0;
        int n = matrix.length;
        if (n % 2 == 0) {
            int[] visitados = new int[n];

            for (int i = 0; i < n; i++) {
                visitados[i] = -1;
            }

            LinkedList<Integer> cola = new LinkedList<>();

            cola.add(pos);

            while (!cola.isEmpty()) {
                int actual = cola.poll();

                boolean auxiliar = true;
                int i;

                for (i = 0; (i < n) && auxiliar; i++) {
                    if ((matrix[actual][i] > 0) && (visitados[i] == -1)) {
                        cola.add(i);
                    }
                }

                for (i = 0; (i < resultado) && auxiliar; i++) {
                    for (int j = 0; j < n; j++) {
                        auxiliar = false;
                        if (matrix[actual][j] > 0) {
                            auxiliar |= visitados[j] == i;
                        }
                    }
                }
                if (i == resultado) {
                    resultado++;
                }
                visitados[actual] = i;
            }
        }
        return resultado;
    }
}
