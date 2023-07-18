// Autor: Miguel Salomon - 1910274

import kotlin.system.exitProcess

/**
 * Implementación del TAD Cola
 * 
 * @param n: Entero positivo, número máximo de elementos permitidos
 *          en la cola.
 */
class Cola(n: Int) {

    val MAX = if (n <= 0) {
        println("Error: No se puede crear una pila de $n elementos, el tamaño debe ser positivo.")
        exitProcess(1) 
    } else {
        n
    }
    val c = ListaCircular()
    var nElems: Int

    init {
        nElems = 0
    }

    /**
     * Inserta un entero al final de la cola.
     */
    fun encolar(e: Int) {
        if (nElems >= MAX) {
            println("Error: Overflow de cola")
            exitProcess(1)
        }

        c.agregarAlFinal(e)
        nElems++
    }

    /**
     * Elimina de la cola al elemento con más antiguedad en la misma.
     * La cola no debe estar vacía.
     */
    fun desencolar() {
        if (estaVacia()) {
            println("Error: Underflow de cola")
            exitProcess(1)
        }

        c.eliminarPrimero()
        nElems--
    }

    /**
     * Retorna el elemento que está en la cabeza de la cola.
     * La cola no debe estar vacía.
     */
    fun primero(): Int {
        if (estaVacia()) {
            println("Error al obtener primer elemento: Cola vacía")
            exitProcess(1)
        }
        
        return c.primero()
    }

    /** Retorna true si la cola no tiene elementos, false de otra forma. */
    fun estaVacia(): Boolean = nElems == 0

    /**
     * Retorna un string con la representación del TAD Cola implementado
     */
    override fun toString(): String = c.toString()
}