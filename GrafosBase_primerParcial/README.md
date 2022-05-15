# Proyecto: Primer Parcial Teoria de Grafos(Auxiliatura)
______________
- Nombre: Alexander James Alvarez Rojas 
- SIS: 202103261
- Gestion: I-2022

____________
## Clase: Matriz Adyacencia
Representacion de un grafo por medio de la matriz de adyacencia: 
        G = 
        {[a][b][c],
         [d][e][f],
         [g][h][i]} 

La clase se encuentra escrita sobre Java 11, para poder ejecutar el codigo, se ejecuta el metodo main(String[] args) de la clase Main.java.
Donde se encuentran los ejemplos respectivos, ademas de algunos otros ejemplos: 
#### Explicacion de Atributos: 
- \- matrix: int[][]: representa las aristas dirigidas entre los vertices, es una matriz nxn
- \- pesoAristas: HashMap<Integer, ArrayList<Peso>>: almacena el peso de las aristas.
##### Clase: Peso
Clase que representa el peso de una arista, entre 2 vertices.
#### Explicacion de Metodos: 
##### 1. Contructores 
Consta de 2 constructores: 
- Con la creacion de un grafo desde 0
``` java
public MatrizAdyacencia(int n, boolean ponderado) {...}
```
Si es que el grafo a la hora de su creacion, fuera ponderado, solo admitira a adicion de aristas ponderadas entre sus vertices.
- Con la creacion de un objeto de la clase, por medio de la matriz de adyacencia
``` java
public Matriz(int[][] matriz) {...}
```
##### 2. Metodos get 
Consta de los siguientes metodos: 
###### 2.1 numero de vertices, dirigidos
``` java
public int getNumAristas() {...}
``` 
Este metodo nos permite contar el numero de aristas totales del grafo, contando a todos sus vertices, como aristas dirigidas, es decir el grado O(g_{v})+ y O(g_{v})-, se suman.
###### 2.2 numero de vertices, no dirigidos
``` java 
public int getNumAristasNoDirigidos() {...}
``` 
Este metodo nos permite contar el numero de aristas totales del grafo, si es que hay una coneccion de ida y vuelta entre 2 vertices, lo cuenta como 1 arista bidireccional.
###### 2.3 peso entre aristas
``` java 
public double getPeso(int i, int j) {...}
``` 
Dentro de este metodo podremos obtener el peso de una arista, que existe entre los vertices v_{i} (origen) y v_{j} (destino). Este metodo suelta resultado 0, si es que no existe una arista entre los vertices o si es que el grafo no es ponderado.
###### 2.4 vertices adyacentes
``` java
public int[] getAdyacentes(int i) {...}
```
Tenemos como salida un array estatico, por ello primeramente se calcula el orden del vertice (O(g_{v}-)). Si es que existe mas de una arista entre 2 vertices, anota el numero de veces con las que se cruza el grafo. Esto se lo realiza con el metodo: 
``` java
public int calcularOrdenVertice(int vertice) {...} 
```
##### 3. Insercion de aristas
###### 3.1 insercion de aristas, sin ponderacion
``` java 
public void insertarArista(int i, int j, boolean dirigido) {...}
```
las entradas que exige el metodo son: 'i' y 'j' que hacen referencia a los vertices de origen y destino correspondientes. Ademas del 'dirigido' para realizar la arista de vuelta.
###### 3.2 insercion arista, con ponderacion. 
``` java 
public void insertarArista(int i, int j, boolean dirigido, double peso) {...}
```
##### 4. Dibujar el grafo 
Se realiza la representacion del grafo, con el uso de una terminal, utilizando la forma de representacion de Berge: 
> V = {a, b, c, ..., n}
> A = {{a, b}, .. {n, d}}

si el grafo es ponderado: 
> A = {{a, b, peso}, ..., {n, d, peso}}

Para realizar este aspecto, cuenta con 2 metodos privados, para realizarlo: 
``` java
private String dibujarGrafoSinPeso() {...}
private String dibujarGrafoConPeso() {...} 
``` 
##### 5. Eliminacion de aristas
###### 5.1 eliminacion, sin peso
```java
public quitarArista(int origen, int destino, boolean dirigido) {...}
```
Elimina la arista, solamente si el grafo no es ponderado, ademas si es que existiera al menos 1 arista entre los vertices. 
Siendo la entrada 'dirigido', la que nos ayuda a eliminar una arista bidireccional.
###### 5.2 eliminacion, con peso
```java
public quitarArista(int origen, int destino, boolean dirigido, double peso) {...}
```
Elimina la arista, solamente si el grafo es ponderado, ademas si es que existiera al menos 1 arista entre los vertices, que se mostrara en el atributo matrix 
Siendo la entrada 'dirigido', la que nos ayuda a eliminar una arista bidireccional.
##### 6. Propiedades de la matriz
Son metodos booleanos que nos ayudan a definir grafos con ciertas propiedades: 
###### 6.1 grafo completo
```java
public boolean esCompleto() {...}
```
Verificar primeramente si es que el grafo no tenga ninguna arista con si mismo, luego que haya como maximo 1 arista entre todos los vertices. Si es que cumple con ambas condiciones es un grafo completo.
###### 6.2 grafo ciclo
```java
public boolean esCiclo() {...}
```
Delega a un metodo privado, para poder verficar si es un grafo ciclo.
```java
private boolean calcularGrafoCiclo(boolean[] ciclo, int nroA, int k) {...}
```
donde la entrada 'ciclo' nos sirve para marcar si es que ya pasamos por el vertice, 'nroA' es el numero de aristas permitidas a todos los vertices y 'k' el vertice desde el que queremos empezar.
Lo realiza por medio de ciclos anidados, dentro del primer ciclo anidado se verifica si es que aun existe algun vertice que no visito, si esto pasa busca el proximo vertice que tenga al menos 1 arista con el vertice actual, si esto no pasara, no existe un ciclo.
###### grafo rueda
```java
public boolean esGrafoRueda() {...}
```
Se realiza la verificacion de la existencia de 1 vertice que tenga 1 arista dirigida u bidireccional entre los demas vertices. 
Si es que esto ocurre, utilizamos el metodo: 
```java
private boolean calcularGrafoCiclo(boolean[] ciclo, int nroA, int k) {...}
```
Para saber si es que existe un bucle entre los vertices faltantes, del grafo.