import java.util.*

// Definimos la clase Estudiante con tres atributos: suscripcion, nombre y apellido
data class Cliente(val suscripcion: Int, val nombre: String, val apellido: String)

fun merge(A: Array<Cliente>, B: Array<Cliente>, C: Array<Cliente>) {
    var i = 0
    var j = 0
    for (k in C.indices) {
        if (i == A.size) {
            C[k] = B[j]
            j++
        } else if (j == B.size) {
            C[k] = A[i]
            i++
        } else if (A[i].suscripcion <= B[j].suscripcion) {
            C[k] = A[i]
            i++
        } else {
            C[k] = B[j]
            j++
        }
    }
}

fun mergeSort(arr: Array<Cliente>) {
    if (arr.size < 2) {
        return
    }
    val mid = arr.size / 2
    val left = arr.sliceArray(0 until mid)
    val right = arr.sliceArray(mid until arr.size)
    mergeSort(left)
    mergeSort(right)
    merge(left, right, arr)
}

fun encontrarGanador(clientes: Array<Cliente>): Cliente {
    var ganador = clientes[0]
    for (i in 1 until clientes.size) {
        if (clientes[i].suscripcion < ganador.suscripcion ||
            (clientes[i].suscripcion == ganador.suscripcion && clientes[i].nombre < ganador.nombre)) {
            ganador = clientes[i]
        }else if(clientes[i].suscripcion == i) {
            return clientes[i]
    }
}
    return ganador 
}

fun main(args: Array<String>) {
    // Lee los datos de los clientes desde la línea de comandos
    val clientes = mutableListOf<Cliente>()
    for (i in args.indices step 2) {
        val suscripcion = args[i].toInt()
        val nombre = args[i+1].split(" ")[0]
        val apellido = args[i+1].split(" ")[1]
        clientes.add(Cliente(suscripcion, nombre, apellido))
    }

    // Ordena la secuencia de clientes
    mergeSort(clientes.toTypedArray())

    // Encuentra al ganador
    val ganador = encontrarGanador(clientes.toTypedArray())

    // Imprime el nombre y apellido del ganador
    println("${ganador.nombre} ${ganador.apellido}")
}
