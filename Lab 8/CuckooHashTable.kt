import java.lang.StringBuilder

/*
        Autor: Miguel Salomon - 1910274
*/

/**
 * Implementación de tabla de hash dinámica basada en el
 * algoritmo de hashing de cuco.
 */
class CuckooHashTable: Diccionario, Iterable<Pair<Int, String>> {

    private var n = 0
    private var T1: Array<CuckooHashTableEntry?> = arrayOfNulls(7)
    private var T2: Array<CuckooHashTableEntry?> = arrayOfNulls(7)
    private val m: Int
        get() = T1.size
    private val factorDeCarga: Float
        get() = n / (2F * m) // Se toma el tamaño de los dos areglos
    private val maxIter: Int
        get() = n / 2
    private val threshold = 0.7F

    /**
     * Función principal de hash que usa el método de la división.
     *
     * Retorna el valor hash asociado a la clave [k].
     */
    private fun h1(k: Int) = k % m

    /**
     * Función de hash secundaria que usa el método de la multiplicación.
     *
     * Retorna el valor hash asociado a la clave [k].
     */
    private fun h2(k: Int) = (m * ((k * 0.6180339887) % 1)).toInt()

    /**
     * Aumenta el tamaño de T1 y T2 a (m+16) * (3/2)
     * y reconstruye las tabla de hash de acuerdo
     * a las nueva funciones de hash.
     */
    private fun rehash() {
        val TCopy1 = T1
        val TCopy2 = T2
        val newSize = (3 * (m + 16)) / 2
        n = 0

        T1 = arrayOfNulls(newSize)
        T2 = arrayOfNulls(newSize)

        for (x in TCopy1) if (x != null) agregar(x.clave, x.valor)
        for (x in TCopy2) if (x != null) agregar(x.clave, x.valor)
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

        // Prepara el elemento a insertar
        var x: CuckooHashTableEntry? = CuckooHashTableEntry(clave, valor)

        repeat(maxIter + 1) {
            var hk = h1(x!!.clave)

            // swap(x, T1[h1(k)])
            var temp = T1[hk]
            T1[hk] = x
            x = temp

            if (x == null) {
                n++
                if (factorDeCarga >= threshold) rehash()
                return
            }

            hk = h2(x!!.clave)

            // swap(x, T2[h2(k)])
            temp = T2[hk]
            T2[hk] = x
            x = temp

            if (x == null) {
                n++
                if (factorDeCarga >= threshold) rehash()
                return
            }
        }

        rehash()
        agregar(x!!.clave, x!!.valor)
    }

    /**
     * Elimina de la tabla el elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en la tabla.
     */
    override fun eliminar(clave: Int) {
        var hk = h1(clave)

        // Se elimina el nodo con la clave
        if (T1[hk]?.clave == clave) {
            T1[hk] = null
            n--
            return
        }

        hk = h2(clave)

        if (T2[hk]?.clave == clave) {
            T2[hk] = null
            n--
            return
        }

        // No se cumple la precondición
        throw NoSuchElementException("La clave $clave no se encuentra en la tabla")
    }

    /**
     * Retorna una String con el valor asociado al
     * elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en la tabla.
     */
    override fun buscar(clave: Int): String {
        var hk = h1(clave)
        if (T1[hk]?.clave == clave) return T1[hk]!!.valor

        hk = h2(clave)
        if (T2[hk]?.clave == clave) return T2[hk]!!.valor

        // No se cumple la precondición
        throw NoSuchElementException("La clave $clave no se encuentra en la tabla")
    }

    /**
     * Retorna true si la [clave] corresponde a algún elemento
     * de la tabla; false en cualquier otro caso. 
     */
    override fun existe(clave: Int): Boolean = T1[h1(clave)]?.clave == clave || T2[h2(clave)]?.clave == clave

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
    override operator fun iterator(): Iterator<Pair<Int, String>> = CuckooHashTableIterator(this)

    /** Retorna el número de elementos que ocupan la tabla. */
    override fun numElementos(): Int = n

    /**
     * Implementación de la interfaz Iterator para la clase
     * CuckooHashTable.
     */
    inner class CuckooHashTableIterator(l: CuckooHashTable):
        Iterator<Pair<Int, String>> {
        // Se concatenan ambos arreglos en uno solo
        val tabla = l.T1 + l.T2
        val size = tabla.size
        var entradaActual: CuckooHashTableEntry? = null
        var indiceActual = 0

        init {
            // Busca la primera entrada no nula en tabla
            while (indiceActual < size && tabla[indiceActual] == null) indiceActual++
            entradaActual = if (indiceActual == size) null else tabla[indiceActual]
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

            if (entradaActual == null) throw NoSuchElementException("No hay más elementos que iterar")

            /* Se almacena el valor a retornar y se prepara el siguiente
            en entradaActual */
            val proxEl = Pair(entradaActual!!.clave, entradaActual!!.valor)

            indiceActual++

            while (indiceActual < size && tabla[indiceActual] == null) indiceActual++
            entradaActual = if (indiceActual == size) null else tabla[indiceActual]

            return proxEl
        }
    }
}