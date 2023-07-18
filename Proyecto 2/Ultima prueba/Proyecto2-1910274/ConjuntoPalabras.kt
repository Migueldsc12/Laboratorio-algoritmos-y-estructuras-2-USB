// Autor: Miguel Salomon - 1910274

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

//Representacion del TAD Conjunto Palabra

class ConjuntoPalabras() {

    private var size = 7;
    private var cantidadDePalabras = 0;
    private var palabras: Array<ListaCircular> = Array(size) { ListaCircular() };

    /**
     * Metodo que inserta una palabra en la tabla de hash
    * Pre: La palabra es válida y no pertenece a la tabla de hash.
    * Post: Se inserta la palabra en la tabla de hash.
    */
    fun agregar(palabra: String) {
        if (this.pertenece(palabra)) return;
        if (this.factorCarga() >= 0.7) this.rehashing();
        val n = this.hashIndice(palabra);
        palabras[n].agregarAlFinal(palabra);
        this.cantidadDePalabras++;
    }

    /**
     * Metodo que averigua si una palabra pertenece al conjunto.
    * Pre: true.
    * Post: Se devuelve si la palabra pertenece o no al conjunto.
    */
    fun pertenece(palabra: String): Boolean {
        val n = this.hashIndice(palabra);
        return palabras[n].existe(palabra);
    }

    /**
     * Metodo que elimina una palabra de la tabla de hash
    * Pre: La palabra es válida y pertenece a la tabla de hash.
    * Post: Se elimina la palabra de la tabla de hash.
    */
    fun eliminar(palabra: String) {
        if (!this.pertenece(palabra)) return;
        val n = this.hashIndice(palabra);
        palabras[n].eliminar(palabra);
        this.cantidadDePalabras--;
    }

    /**
     * Metodo que obtiene el indice en el que sera insertada la siguiente palabra en la tabla de hash
    * Pre: true.
    * Post: Se devuelve el índice en el que debería estar insertada la siguiente palabra en la tabla de hash.
    */
    private fun hashIndice(palabra: String): Int {
        return abs(palabra.hashCode() % this.sizeList());
    }
    
    /**
     * Metodo que aumenta el tamaño de la tabla de hash en caso de ser necesario
     * Pre: true.
     * Post: Se aumenta el tamaño de la tabla de hash y se reinsertan las palabras en la nueva tabla de hash.
     */
    private fun rehashing() {
        val palabraAnterior = this.palabras.copyOf();
        this.size = 2 * size;
        this.cantidadDePalabras = 0;
        this.palabras = Array(size) { ListaCircular() };
        
        for (i in 0 until palabraAnterior.size) {
            if (palabraAnterior[i].emptyList()) continue
            
            var nodo = palabraAnterior[i].obtenerPrimero();
            
            while (nodo != palabraAnterior[i].centinela) {
                this.agregar(nodo?.obtener()!!);
                nodo = nodo.next!!;
            }
        }
    }
    
    /**
     * Metodo que calcula el factor de carga de una tabla de hash.
    * Pre: true.
    * Post: Se devuelve el factor de carga de la tabla de hash.
    */
    private fun factorCarga(): Double {
        return (this.cantidadDePalabras().toDouble() / this.sizeList().toDouble());
    }

    /**
     * Metodo que calcula la cantidad de palabras que hay en el conjunto.
    * Pre: true.
    * Post: Retorna el número de palabras que hay en el conjunto de palabras.
    */
    fun cantidadDePalabras(): Int {
        return this.cantidadDePalabras;
    }

    /**
     * Metodo que crea un arreglo con las palabas del conjunto.
    * Pre: true.
    * Post: Se devuelve un arreglo con las palabras que hay en el conjunto de palabras.
    */
    fun palabrasArray(): Array<String> {
        var palabrasArray = Array(this.cantidadDePalabras) { "" };
        var n = 0;
        for (i in 0 until this.sizeList()) {
            if (palabras[i].emptyList()) continue
            var nodo = palabras[i].obtenerPrimero();
            while (nodo != palabras[i].centinela) {
                palabrasArray[n] = nodo?.obtener()!!;
                nodo = nodo.next!!;
                n++;
            }
        }
        return palabrasArray;
    }

    /**
     * Metodo que calcula el tamano del conjunto de palabras.
    * Pre: true.
    * Post: Se devuelve el número de palabras que hay en el conjunto de palabras.
    */
    fun sizeList(): Int {
        return this.size;
    }

    /**
     * Metodo que representa el conjunto de palabras como un string.
    * Pre: true.
    * Post: Se devuelve una representación en String del conjunto de palabras.
    */
    override fun toString(): String {
        if (this.cantidadDePalabras() == 0) return "[]\n";
        var str = "[";
        val palabras = this.palabrasArray();
        ordenLexCompleto(palabras);

        for (i in 0 until palabras.size - 1) {
            if ((i + 1) % 5 == 0 && i != 0) str += palabras[i] + ",\n"; else str += palabras[i] + ", ";
        }
        str += palabras[palabras.size - 1] + "]\n";
        return str;
    }
}

//Representacion de la tabla de hash.
 
class HashTable() {

    private val size = 26;
    private val alfabeto: Array<ListaCircular> = Array(size) { ListaCircular() };

    /**
     * Metodo que contruye la tabla de Hash para el alfabeto.
    * Pre: true.
    * Post: Se inicializa la tabla de hash.
    */
    init {
        var j = 1;
        for (i in 'a'..'n') {
            this.insertar(i, j);
            j++;
        }
        insertar('ñ', j);
        j++
        for (i in 'o'..'z') {
            this.insertar(i, j);
            j++;
        }
    }

    /**
     * Metodo que determina el indice para insertar una letra en la tabla de hash
    * Pre: true.
    * Post: retorna el índice en el que se debe insertar una letra en la tabla de hash.
    */
    private fun hashIndice(letra: Char): Int {
        return String.format("%c", letra).hashCode() % size;
    }

    /**
     * Metodo que inserta un dato en la tabla de hash
    * Pre: true.
    * Post: Se inserta el dato en la tabla de hash.
    */

    fun insertar(letra: Char, valor: Int) {
        val n = this.hashIndice(letra);
        alfabeto[n].agregarAlFinal(String.format("%c", letra), valor);
    }    

    /**
    * Pre: true.
    * Post: True si la letra pertenece al alfabeto || false si no.
    */
    fun pertenece(letra: Char): Boolean {
        val n = this.hashIndice(letra);
        return alfabeto[n].existe(String.format("%c", letra));
    }
 
    /**
     * Metodo que obtiene el valor de una letra en la tabla
    * Pre: Letra debe pertenecer al conjunto.
    * Post: Retorna el valor de la letra en la tabla de hash.
    */
    fun valorNodo(letra: Char): Int {
        val n = this.hashIndice(letra);
        return alfabeto[n].valorNodo(String.format("%c", letra))!!;
    }
 
    /**
     * Metodo que representa la tabla de hash como un string
    * Pre: true.
    * Post: Se devuelve una representación en String de la tabla de hash.
    */
    override fun toString(): String {
        var str = "";
        for (i in 0 until size) str += "${alfabeto[i]}\n";
        return str;
    }
}

// Representacion de una lista circular enlazada

   class ListaCircular() {    
       private var size: Int = 0;
       var centinela: Nodo = Nodo(null);    

   /**
    * Metodo que construte la lista enlazada circular
   * Pre: true.
   * Post: Se crea un objeto de la clase ListaCircular.
   */
   init {
       centinela.next(centinela);
       centinela.prev(centinela);
   }

   /**
    * Metodo que retorna el tamaño de la lista
   * Pre: true.
   * Post: Se devuelve el tamaño de la lista.
   */
   fun sizeList(): Int {
       return size;
   }

   /**
    * Metodo que agrega un nodo al principio de la lista
   * Pre: true.
   * Post: Se agrega un nodo con el dato dado al frente de la lista.
   */
   fun agregarAlFrente(n: String, valor: Int? = null) {
       val nuevoNodo = Nodo(n, valor);
       nuevoNodo.next(centinela.next!!);
       nuevoNodo.prev(centinela);
       centinela.next!!.prev(nuevoNodo);
       centinela.next(nuevoNodo);
       size++;
   }

   /**
    * Metodo que agrega un nodo al final de la lista
   * Pre: true.
   * Post: Se agrega un nodo con el dato dado al final de la lista.
   */
   fun agregarAlFinal(n: String, valor: Int? = null) {
       val nuevoNodo = Nodo(n, valor);
       nuevoNodo.next(centinela);
       nuevoNodo.prev(centinela.prev!!);
       centinela.prev!!.next(nuevoNodo);
       centinela.prev(nuevoNodo);
       size++;
   }
   
   /**
    * Metodo que verifica si el nodo dado existe en la lista
    * Pre: true.
    * Post: True si existe un nodo con el dato dado || false en caso contrario.
    */
   fun existe(n: String): Boolean {
       if (this.emptyList()) return false;

       var nodoActual = centinela.next;
       
       while (nodoActual != centinela) {
           if (nodoActual!!.obtener() == n) return true;
           nodoActual = nodoActual.next;
       }
       return false;
   }
   
   /**
    * Metodo que elimina un nodo de la lista
   * Pre: true.
   * Post: Se elimina el nodo con el dato dado de la lista.
   */
   fun eliminar(n: String) {
       if (this.emptyList()) return

       var nodoActual = centinela.next;
       while (nodoActual != centinela) {
           if (nodoActual?.obtener() == n) {
               nodoActual.prev?.next(nodoActual.next!!);
               nodoActual.next?.prev(nodoActual.prev!!);
               size--;
               return;
           }
           nodoActual = nodoActual?.next;
       }
   }

   /**
    * Metodo que verifica si la lista esta vacia
   * Pre: true.
   * Post: True si la lista está vacía || false en caso contrario.
   */
   fun emptyList(): Boolean {
       return size == 0;
   }  

   /**
    * Metodo que obtiene el primer nodo de la lista
   * Pre: true.
   * Post: null si la lista esta vacia || retorna el primer nodo de la lista.
   */
   fun obtenerPrimero(): Nodo? {
       if (this.emptyList()) return null;
       return centinela.next;
   }

   fun valorNodo(n: String): Int? {
       if (this.emptyList()) return null;

       var nodoActual = centinela.next;

       while (nodoActual != centinela) {
           if (nodoActual!!.obtener() == n) return nodoActual.valorNodo();
           nodoActual = nodoActual.next;
       }
       return null;
   }
   
      /**
      * Pre: true.
      * Post: Se devuelve una representación en String de la lista.
      */
      override fun toString(): String {
          if (this.emptyList()) return "[]";
          var string = "[";
          var nodoActual = centinela.next;
  
          while (nodoActual != centinela.prev) {
              string += "${nodoActual!!}, ";
              nodoActual = nodoActual.next;
          }
          string += "${nodoActual!!}]";
          return string;
          }
      } 

// Representacion de los nodos de la lista enlazada.
 
class Nodo(var n: String?, var valor: Int? = null) { 
    var next: Nodo? = null;
    var prev: Nodo? = null;
 
    /**
     * Metodo que cambia el nodo siguiente para ser el actual
    * Pre: true.
    * Post: El nodo siguiente al nodo actual es el nodo dado.
    */
    fun next(nodo: Nodo?) {
        this.next = nodo;
    }
 
    /**
     * Metodo que cambia el nodo anterior para ser el actual
    * Pre: true.
    * Post: El nodo anterior al nodo actual es el nodo dado.
    */
    fun prev(nodo: Nodo?) {
        this.prev = nodo;
    }
 
    /**
     * Metodo que cambia el valor de un nodo
    * Pre: true.
    * Post: El dato que cambia el nodo a el dato dado.
    */
    fun cambiar(n: String?) {
        this.n = n;
    }
 
    /**
     * Metodo que obtiene un nodo
    * Pre: true.
    * Post: El dato que obtiene el nodo que es el dato dado.
    */
    fun obtener(): String? {
        return this.n;
    }
 
    /**
     * Metodo que retorna el valor de un nodo
    * Pre: true.
    * Post: El valor que contiene el nodo que es el valor dado.
    */
    fun valorNodo(): Int? {
        return this.valor;
    }
 
    /**
     * Metodo que representa un nodo como un string
    * Pre: true.
    * Post: Se devuelve una representación en String del nodo.
    */
    override fun toString(): String {
        var str = "";
        str += "${this.obtener()}";
        if (this.valor != null) str += " (${this.valor})";
        str += "\n";
        return str;
    }
}
    
    /**
     * Metodo que intercambia 2 palabras de un arreglo
     * Pre: Los índices son válidos.
     * Post: Se intercambian las palabras en el arreglo.
     */
    fun swap(palabrasArray: Array<String>, i: Int, j: Int) {
        var temp = palabrasArray[i];
        palabrasArray[i] = palabrasArray[j];
        palabrasArray[j] = temp;
    }

    /**
     * Metodo que ordena un arreglo de palabras
    * Pre: true.
    * Post: Se ordena el arreglo de palabras completo.
    */
    fun ordenLexCompleto(palabrasArray: Array<String>) {
        ordenLex(palabrasArray, 0, palabrasArray.size - 1);
    }

    /**
     * Metodo que ordena una seccion del arreglo de palabras 
    * Pre: Los índices son válidos.
    * Post: Se ordena una sección del arreglo.
    */
    fun ordenLex(palabrasArray: Array<String>, low: Int, high: Int) {
        if (low < high) {
            var p = partitionInd(palabrasArray, low, high);
            ordenLex(palabrasArray, low, p - 1);
            ordenLex(palabrasArray, p + 1, high);
        }
    }
    
    /**
     * Metodo que particiona un arreglo de palabras
    * Pre: Los índices son válidos.
    * Post: Retorna un nuevo pivote de las particiones.
    */
    fun partitionInd(palabrasArray: Array<String>, low: Int, high: Int): Int {
        var pivot = palabrasArray[high];
        var i = low - 1;
        for (j in low until high) {
            if (compararPalabras(palabrasArray[j], pivot) <= 0) {
                i++;
                swap(palabrasArray, i, j);
            }
        }
        swap(palabrasArray, i + 1, high);
        return i + 1;
    }
 
 