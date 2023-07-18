/*
        Autor: Miguel Salomon - 1910274
*/

/**
 * Estructura de datos que representa una entrada
 * de una tabla de hash con clave y valor asociado.
 */
class HashTableEntry(val clave: Int,val valor: String) {
    var ant: HashTableEntry? = null
    var prox: HashTableEntry? = null

    /**
     * Retorna un string con la representación de
     * la entrada de la tabla como un par (clave, valor).
     */
    override fun toString(): String = "($clave, $valor)"
}