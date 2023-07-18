// Definimos la clase Estudiante con dos atributos: carne y creditosFaltantes
data class Estudiante(val carne: Int, val creditosFaltantes: Int)

// Función principal que recibe los argumentos de línea de comandos
fun main(args: Array<String>) {
    val n = args[0].toInt() // El primer argumento es el número de cupos disponibles (n)
    val estudiantes = mutableListOf<Estudiante>()
    for (i in 1 until args.size step 2) { // Iteramos sobre los argumentos restantes en pares (carné, créditos faltantes)
        estudiantes.add(Estudiante(args[i].toInt(), args[i+1].toInt())) // Creamos un objeto Estudiante con cada par de argumentos y lo agregamos a la lista de estudiantes
    }
    asignarCupo(n, estudiantes) // Llamamos a la función asignarCupo para asignar los cupos disponibles a los estudiantes
}

// Función que asigna los cupos disponibles a los estudiantes
fun asignarCupo(n: Int, estudiantes: MutableList<Estudiante>) {
    if (n >= estudiantes.size) { // Si hay suficientes cupos para todos los estudiantes, se imprimen que todos fueron admitidos
        println("TODOS FUERON ADMITIDOS")
        for (estudiante in estudiantes) {
            println(estudiante.carne)
        }
        return
    }
    mergeSort(estudiantes) // Ordenamos la lista de estudiantes por orden ascendente de créditos faltantes y, en caso de empate, por orden ascendente de carné
    for (i in 0 until n) { // Imprimimos los carnés de los n estudiantes con menor número de créditos faltantes
        println(estudiantes[i].carne)
    }
}

fun merge(A: MutableList<Estudiante>, B: MutableList<Estudiante>, C: MutableList<Estudiante>) {
    var i = 0
    var j = 0
    for (k in C.indices) {
        if (i == A.size) {
            C[k] = B[j]
            j++
        } else if (j == B.size) {
            C[k] = A[i]
            i++
        } else if (A[i].creditosFaltantes < B[j].creditosFaltantes ||
            (A[i].creditosFaltantes == B[j].creditosFaltantes && A[i].carne < B[j].carne)) {
            C[k] = A[i]
            i++
        } else {
            C[k] = B[j]
            j++
        }
    }
}

fun mergeSort(arr: MutableList<Estudiante>) {
    if (arr.size < 2) {
        return
    }
    val mid = arr.size / 2
    val left = arr.subList(0, mid).toMutableList()
    val right = arr.subList(mid, arr.size).toMutableList()
    mergeSort(left)
    mergeSort(right)
    merge(left, right, arr)
}