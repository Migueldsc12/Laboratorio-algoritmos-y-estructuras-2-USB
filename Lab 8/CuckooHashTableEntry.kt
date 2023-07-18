/*
        Autor: Miguel Salomon - 1910274
*/
/**
 * Estructura de datos que representa una entrada
 * de una tabla de hash con clave y valor asociado.
 *
 * @param clave: Entero con la clave de la entrada.
 * @param valor: String con el valor asociado a la clave.
 */
class CuckooHashTableEntry(val clave: Int, val valor: String) {
	/**
     * Retorna un string con la representación de
     * la entrada de la tabla como un par (clave, valor).
     */
	override fun toString(): String = "($clave, $valor)"
}