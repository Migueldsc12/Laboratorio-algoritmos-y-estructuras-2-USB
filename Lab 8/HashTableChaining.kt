import java.lang.StringBuilder

/*
        Autor: Miguel Salomon - 1910274
*/

/**
 * Implementación de tabla de hash dinámica que resuelve
 * colisiones usando encadenamiento con listas doblemente
 * enlazadas.
 *
 * Soporta las operaciones de agregar, eliminar, buscar y
 * chequear existencia.
 */
class HashTableChaining: Diccionario, Iterable<Pair<Int, String>> {

    private var n = 0
    private var T: Array<CircularList?> = arrayOfNulls(7)
    private val m: Int
        get() = T.size
    private val factorDeCarga: Float
        get() = n / m.toFloat()
    private val threshold = 0.7F

    /**
     * Función de hash que usa el método de la división.
     *
     * Retorna el valor hash asociado a la clave [k].
     */
    private fun h(k: Int) = k % m

    /**
     * Aumenta el tamaño de T a (m+16) * (3/2),
     * donde m es el tamaño actual del arreglo,
     * y reconstruye las tabla de hash de acuerdo
     * a la nueva función de hash.
     */
    private fun rehash() {  
        val TCopy = T
        val newSize = (3 * (m + 16)) / 2
        n = 0

        T = arrayOfNulls(newSize)

        for (el in TCopy) {
            if (el != null) {
                var x: HashTableEntry? = el.cabeza
                while (x != null) {
                    agregar(x.clave, x.valor)
                    x = x.prox
                }
            }
        }
    }

    /**
     * Agrega a la tabla una [clave] y su [valor] asociado.
     *
     * La clave no debe ser igual a la de ninguno de los
     * elementos preexistentes en la tabla.
     */
    override fun agregar(clave: Int, valor: String) {

        // Chequeo de precondición
        if (existe(clave)) throw Exception("La clave $clave ya se encontraba en la tabla")

        // Obtiene el valor hash de la clave
        val hk = h(clave)

        // Crea una lista nueva en T[hk] si no la había
        if (T[hk] == null) T[hk] = CircularList()

        /* Inserta el nodo con los atributos
        (clave, valor) y se aumenta n */
        T[hk]?.insertar(HashTableEntry(clave, valor))
        n++

        if (factorDeCarga >= threshold) rehash()
    }

    /**
     * Elimina de la tabla el elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en la tabla.
     */
    override fun eliminar(clave: Int) {
        val hk = h(clave)
        val del = T[hk]?.buscar(clave)

        // No se cumple la precondición
        if (del == null) throw NoSuchElementException("La clave $clave no está en la tabla")

        // Se elimina el nodo de la lista enlazada T[hk]
        T[hk]?.eliminar(del)

        /* Si la lista queda vacía se vuelve a hacer null
        la entrada de la tabla T[hk] y se actualiza n */
        if (T[hk]?.cabeza == null) T[hk] = null
        n--
    }

    /**
     * Retorna una String con el valor asociado al
     * elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en la tabla.
     */
    override fun buscar(clave: Int): String {
        val hk = h(clave)
        var x = T[hk]?.buscar(clave)

        // No se cumple la precondición
        if (x == null) throw NoSuchElementException("La clave $clave no se encuentra en la tabla")
        
        return x.valor
    }

    /**
     * Retorna true si la [clave] corresponde a algún elemento
     * de la tabla; false en cualquier otro caso. 
     */
    override fun existe(clave: Int): Boolean {
        val hk = h(clave)
        var x = T[hk]?.buscar(clave)

        return x != null
    }

    /**
     * Retorna un string con la representación de
     * los pares (clave, valor) contenidos en la
     * tabla.
     */
    override fun toString(): String {
        val strRepr = StringBuilder("{")

        for (par in this) strRepr.append("$par, ")
        strRepr.replace(strRepr.length - 2, strRepr.length, "}")

        return strRepr.toString()
    }

    /**
     * Retorna un iterador con la secuencia de pares 
     * (clave, valor) contenidos en la tabla.
     */
    override operator fun iterator(): Iterator<Pair<Int, String>> = HashTableChainingIterator(this)

    /** Retorna el número de elementos que ocupan la tabla. */
    override fun numElementos(): Int = n
    
    /**
     * Implementación de la interfaz Iterator para la clase
     * HashTableChaining.
     */
    inner class HashTableChainingIterator(l: HashTableChaining):
        Iterator<Pair<Int, String>> {
        val tabla = l.T
        val size = tabla.size
        var entradaActual: HashTableEntry? = null
        var indiceActual = 0

        init {
            // Busca la primera entrada no nula de la tabla
            while (indiceActual < size && tabla[indiceActual] == null) indiceActual++
            entradaActual = if (indiceActual == size) null else tabla[indiceActual]?.cabeza
        }

        /**
         * Retorna true si todavía hay elementos sobre los
         * que iterar; false de otra forma.
         */
        override fun hasNext(): Boolean = (entradaActual != null)

        /**
         * Retorna un par (clave, valor) con el siguiente
         * elemento del iterador.
         */
        override fun next(): Pair<Int, String> {

            if (entradaActual == null) {   
                throw NoSuchElementException("No hay más elementos que iterar")
            }

            /* Se almacena el valor a retornar y se prepara el siguiente
            en entradaActual */
            var valor = Pair(entradaActual!!.clave, entradaActual!!.valor)

            entradaActual = if (entradaActual?.prox != null) {
                entradaActual!!.prox
            } else {
                indiceActual++
                while (indiceActual < size && tabla[indiceActual] == null) indiceActual++

                // Si se llegó al final sin conseguir nuevos elementos
                if (indiceActual == size) null else tabla[indiceActual]?.cabeza
            }

            return valor
        }
    }
}