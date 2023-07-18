// Autor: Miguel Salomon - 1910274

import kotlin.system.exitProcess

/**
 * Implementación del TAD Pila.
 * 
 * @param n: Entero positivo, número máximo de elementos permitidos
 *          en la cola.
 */
class Pila(n: Int) {

    val MAX = if (n <= 0) {
        println("Error: No se puede crear una pila de $n elementos, el tamaño debe ser positivo.")
        exitProcess(1) 
    } else {
        n
    } 
    val p = ListaCircular()
    var nElems: Int 

    init {
        nElems = 0
    }

    /**
     * Inserta un entero [e] al final de la pila.
     *
     * El número de elementos actual en la pila no debe sobrepasar
     * al máximo [MAX] establecido.
     */
    fun empilar(e: Int) {
        if (nElems >= MAX) {
            println("Error: Overflow de pila")
            exitProcess(1)
        }

        p.agregarAlFinal(e)
        nElems++
    }

    /**
     * Elimina de la pila al elemento insertado más recientemente.
     *
     * La pila no debe estar vacía.
     */
    fun desempilar() {
        if (estaVacia()) {
            println("Error: Underflow de cola")
            exitProcess(1)
        }

        p.eliminarUltimo()
        nElems--
    }

    /**
     * Retorna el elemento que está en el tope de la pila.
     *
     * La pila no debe estar vacía.
     */
    fun tope(): Int {
        if (estaVacia()) {
            println("Error al obtener primer elemento: Pila vacía")
            exitProcess(1)
        }

        return p.ultimo()
    }

    /** Retorna true si la pila no tiene elementos, false de otra forma. */
    fun estaVacia(): Boolean = nElems == 0

    /**
     * Retorna un string con la representación del TAD Pila implementado
     */
    override fun toString(): String = p.toString()
}