/*
        Autor: Miguel Salomon - 1910274
*/


/**
 * Estructura de datos de lista doblemente enlazada
 * con nodos del tipo HashTableEntry.
 */
class CircularList {
	var cabeza: HashTableEntry? = null
    /**
    * Inserta el nodo [x] en la cabeza de la lista.
    */
    fun insertar(x: HashTableEntry) {
    	x.prox = cabeza
    	cabeza?.ant = x
        cabeza = x
    }

    /** Elimina el nodo [x] de la lista. */
    fun eliminar(x: HashTableEntry) {

        // Si se elimina la cabeza de la lista
    	if (x.ant == null) cabeza = x.prox
        
        /* La seguridad de nulos permite no tener
        que chequear si x tiene anterior y/o proximo
        para actualizar los nodos adyacentes, si los
        hubiere */
        x.ant?.prox = x.prox
        x.prox?.ant = x.ant
    }

    /**
     * Retorna el nodo de la lista cuya [clave] es la dada
     * en el argumento de la función; null si no se encontró
     * dicho elemento en la lista.
     */
    fun buscar(clave: Int): HashTableEntry? {
        var x: HashTableEntry? = cabeza

        while (x != null && x.clave != clave) x = x.prox

        return x
    }
}