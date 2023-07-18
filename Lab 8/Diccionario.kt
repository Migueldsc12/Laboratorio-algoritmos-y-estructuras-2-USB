/*
        Autor: Miguel Salomon - 1910274
*/

/**
 * TAD Diccionario.
 *
 * Soporta las operaciones de agregar, eliminar, buscar
 * y chequear existencia.
 */

 interface Diccionario: Iterable<Pair<Int, String>> {
    /**
     * Agrega al diccionario una [clave] y su [valor] asociado.
     *
     * La clave no debe ser igual a la de ninguno de los
     * elementos preexistentes en el diccionario.
     */
    fun agregar(clave: Int, valor: String)

    /**
     * Elimina del diccionario el elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en la tabla.
     */
    fun eliminar(clave: Int)

    /**
     * Retorna una String con el valor asociado al
     * elemento con la [clave] dada.
     *
     * La clave debe corresponder a algún elemento
     * preexistente en el diccionario.
     */
    fun buscar(clave: Int): String

    /**
     * Retorna true si la [clave] corresponde a algún elemento
     * del diccionario; false en cualquier otro caso. 
     */
    fun existe(clave: Int): Boolean

    /**
     * Retorna un string con la representación de
     * los pares (clave, valor) contenidos en el
     * diccionario.
     */
    override fun toString(): String

    /**
     * Retorna un iterador con la secuencia de pares 
     * (clave, valor) contenidos en el diccionario.
     */
    override operator fun iterator(): Iterator<Pair<Int, String>>

    /** Retorna el número de elementos que ocupan el diccionario. */
    fun numElementos(): Int
}