// Autor: Miguel Salomon - 1910274

/** Imprime un separador por la salida estándar. */
fun separador() = println("--------------------------------------------------------------------------------\n")

/**
 * Programa cliente que muestra el correcto funcionamiento de las
 * estructuras implementadas.
 */
fun main() {

     // ---- COLA ------

    val queueList = Cola(7)
    println("""
    |Cola con capacidad para ${queueList.MAX} elementos creada: $queueList.
    |
    |$queueList.estaVacia() = ${queueList.estaVacia()}
    |
    """.trimMargin("|")) 

    println("Encolando 7 elementos, los números enteros en [-3..3]:")
    println("Cola: $queueList")
    for (k in -3..3) {
        queueList.encolar(k)
        println("Cola: $queueList")
    }

    println("\nDesencolando los negativos:")
    println("Cola: $queueList")
    repeat(3) { 
        queueList.desencolar() 
        println("Cola: $queueList")
    }

    println("\nEncolando nuevos números:")
    println("Cola: $queueList")
    for (k in 4..6) {
        queueList.encolar(k) 
        println("Cola: $queueList")
    }

    println("\n$queueList.primero() = ${queueList.primero()}")
    println("$queueList.estaVacia() = ${queueList.estaVacia()}\n")
    separador()

    // ------- PILA -------

    val stackList = Pila(7)
    println("""
    |Pila con capacidad para ${stackList.MAX} elementos creada: $stackList.
    |
    |$stackList.estaVacia() = ${stackList.estaVacia()}
    |
    """.trimMargin("|")) 

    println("\nEmpilando 7 elementos, los números enteros en [-3..3]:")
    println("Pila: $stackList")
    for (k in -3..3) {
        stackList.empilar(k)
        println("Pila: $stackList")
    }

    println("\nDesempilando los positivos:")
    println("Pila: $stackList")
    repeat(3) {
        stackList.desempilar()
        println("Pila: $stackList")
    }

    println("\nEmpilándolos en orden inverso:")
    println("Pila: $stackList")
    for (k in 3 downTo 1){
        stackList.empilar(k)
        println("Pila: $stackList")
    }

    println("$stackList.tope() = ${stackList.tope()}")
    println("$stackList.estaVacia() = ${stackList.estaVacia()}\n")
    separador()

}