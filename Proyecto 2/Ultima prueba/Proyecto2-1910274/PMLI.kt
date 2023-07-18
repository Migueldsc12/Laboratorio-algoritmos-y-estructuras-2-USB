
// Autor: Miguel Salomon - 1910274

//Representacion del TAD PMLI

class PMLI(letra: Char) {
    val letra: Char

    private var palabras: ConjuntoPalabras = ConjuntoPalabras()

    /**
     * Metodo que construye la clase PMLI.
     * Pre: el caracter debe ser una letra minúscula del alfabeto español.
     * Post: se crea una estructura PMLI con el caracter dado.
     */
    init {
        require(alfabeto.pertenece(letra)) { "La letra debe estar en minuscula" }
        this.letra = letra;
    }

    /**
     * Método que agrega una palabra al conjunto en caso de ser valido.
     * Pre: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Post: se agrega la palabra a la estructura.
     */
    fun agregarPalabra(palabra: String) {
        if (!palabraValida(palabra) || palabra[0] != this.letra) {
            println("La palabra debe tener solo letras minúsculas.");
            println("La palabra debe empezar con el caracter de la estructura: ${this.letra}.");
            return;
        }
        if (buscarPalabra(palabra)) return;        
        palabras.agregar(palabra)
    }   

    /**
     * Método que elimina la palabra indicada en el conjunto de palabras
     * Pre: la palabra debe pertenecer a la estructura.
     * Post: se elimina la palabra de la estructura.
     */
    fun eliminarPalabra(palabra: String) {
        if (!palabraValida(palabra)) {
            println("La palabra debe tener solo letras minúsculas.");
            return;
        }
        if (buscarPalabra(palabra)) this.palabras.eliminar(palabra);        
        else println("La palabra $palabra no se encuentra.");
    }

     /**
     * Método recibe un String y retorna true si dicho String pertenece al ConjuntoPalabras
     * Pre: la palabra debe ser palabra válida y debe empezar con el caracter de la estructura.
     * Post: devuelve true si la palabra se encuentra en la estructura, false en caso contrario.
     */
    fun buscarPalabra(palabra: String): Boolean {
        if (!palabraValida(palabra)) {
            println("La palabra debe tener solo letras minúsculas.");
            return false;
        }
        return this.palabras.pertenece(palabra);
    }
    
    /**
     * Método que devuelve un arreglo con las palabras del conjunto
     * Pre: true.
     * Post: se devuelve un arreglo con las palabras que se encuentran en la estructura.
     */
    fun palabrasArray(): Array<String> {
        return this.palabras.palabrasArray();
    }

    /**
     * Método que retorna el número de palabras que estan en el conjunto.
     * Pre: true.
     * Post: se devuelve el número de palabras que se encuentran en la estructura.
     */
    fun cantidadDePalabras(): Int {
        return this.palabras.cantidadDePalabras();
    }

    /**
     * Pre: true.
     * Post: se devuelve una representación en String de la estructura.
    */
    override fun toString(): String {
        var str = "{this.letra}: ";
        str += this.palabras.toString();
        return str;
    }
}

    val alfabeto = HashTable();

    /**
     * Metodo que compara 2 palabras del conjunto
     * Pre: true.
     * Post: devuelve -1 si la primera palabra es menor que la segunda, 1 si la primera palabra es mayor que la segunda, 0 si son iguales.
     */
    fun compararPalabras(palabra1: String, palabra2: String): Int {
        var i = 0;
        while (i < palabra1.length && i < palabra2.length) {
            if (alfabeto.valorNodo(palabra1[i]) < alfabeto.valorNodo(palabra2[i])) {
                return -1;
            } else if (alfabeto.valorNodo(palabra1[i]) > alfabeto.valorNodo(palabra2[i])) {
                return 1;
            }
            i++;
        }
        if (palabra1.length < palabra2.length) return -1;
        
        if (palabra1.length > palabra2.length) return 1;        
        return 0;
    }

    /**
     * Metodo que verifica que la palabra sea valida
     * Pre: true.
     * Post: devuelve true si la palabra está formada únicamente por letras minúsculas del alfabeto español, false en caso contrario.
     */
    fun palabraValida(palabra: String): Boolean {
        return palabra.all { alfabeto.pertenece(it) };
    }