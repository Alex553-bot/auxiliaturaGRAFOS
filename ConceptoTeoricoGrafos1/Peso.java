public class Peso {
    private int vertice1; 
    private int vertice2; 
    private double peso;

    public Peso(int o, int d, double p) {
        vertice1 = o; 
        vertice2 = d;
        peso = p;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double p) {
        peso = p;
    }
    
    public int getVertice1() {return vertice1;}
    public int getVertice2() {return vertice2;}
    
}
