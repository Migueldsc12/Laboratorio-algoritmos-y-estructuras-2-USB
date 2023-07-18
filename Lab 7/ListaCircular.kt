// Autor: Miguel Salomon - 1910274

import java.lang.StringBuilder
import kotlin.system.exitProcess

/**
 * Estructura de datos de lista circular doblemente
 * enlazada con centinela.
 */
class ListaCircular() {
    val centinela = Nodo(0)
    private var size: Int = 0

    fun estaVacia(): Boolean {
        return size == 0
    }

    /**
    * Inserta un nodo con el número entero [key] al final
    * de la lista.
    */
    fun agregarAlFinal(key: Int) {
        val x = Nodo(key)

        /* Rearregla los punteros de los nodos
        afectados por la inserción del nuevo */
        x.prev = centinela.prev 
        x.next = centinela
        centinela.prev.next = x
        centinela.prev = x
    }

    /*
    Inserta un nodo con el numero entero al inicio de la lista
    */

    fun agregarAlfrente(key: Int){
        val x = Nodo(key)
        /* Rearregla los punteros de los nodos
        afectados por la inserción del nuevo */
        x.next = centinela.next 
        x.prev = centinela
        centinela.next.prev = x
        centinela.next = x
    }

    /**
    * Elimina el nodo de la lista.
    * El nodo debe ser distinto del centinela de la lista.
    */
    fun eliminar(x: Nodo){
        if (x == centinela) {
            if (x.next == centinela) println("Error: Lista vacía, no se puede eliminar objeto")
            else println("Error: No se puede eliminar al centinela de la lista")
            exitProcess(1)
        } else {
            x.prev.next = x.next
            x.next.prev = x.prev
        }
        
    }

    //Devuelve el nodo con el dato dado, o si el dato no se encuentra en la lista, devuelve null
    fun buscar(dato: Int): Nodo? {
        if (estaVacia()) {
            println("La lista esta vacia")
            return null
        }
        var nodoActual = centinela.next
        while (nodoActual != centinela) {
            if (nodoActual.key == dato) {
                return nodoActual
            }
            nodoActual = nodoActual.next
        }
        println("El dato no se encuentra en la lista")
        return null
    }

    /**
    * Elimina el primer nodo de la lista.
    * La lista no debe estar vacía.
    */
    fun eliminarPrimero() {
        val x = centinela.next
        eliminar(x)
    }

    /**
    * Elimina el último nodo de la lista.
    * La lista no debe estar vacía.
    */
    fun eliminarUltimo() {
        val x = centinela.prev
        eliminar(x)
    }

    /**
     * Retorna el entero almacenado como primero en la lista.
     * La lista no debe estar vacía.
     */
    fun primero(): Int {
        val x = centinela.next

        if (x == centinela) {
            println("Error: Se intentó obtener el primer elemento de una lista vacía")
        }

        return x.key
    }

    /**
     * Retorna el entero almacenado como primero en la lista.
     * La lista no debe estar vacía.
     */
    fun ultimo(): Int {
        val x = centinela.prev

        if (x == centinela) {
            println("Error: Se intentó obtener el primer elemento de una lista vacía")
        }

        return x.key
    }

    /**
     * Retorna un string con la representación de la lista
     */
    override fun toString(): String {
        val strRep = StringBuilder("[")

        var x = centinela.next
        while (x != centinela) {
            strRep.append("${x.key}")
            x = x.next
            if (x != centinela) strRep.append(", ") 
        }
        strRep.append("]")

        return strRep.toString()
    }
}