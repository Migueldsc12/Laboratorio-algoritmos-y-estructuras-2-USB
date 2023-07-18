import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {

    val archivoInstancia = File(args[0])
    val archivoSolucion = File(args[1])

    val lectorInstancia = BufferedReader(FileReader(archivoInstancia, Charsets.UTF_8))
    lectorInstancia.readLine()
    lectorInstancia.readLine()
    lectorInstancia.readLine()
    val numeroCiudadesInstancia = lectorInstancia.readLine().split(":")[1].trim().toInt()
    lectorInstancia.readLine()
    lectorInstancia.readLine()
    val indicesInstancia = Array<Int>(numeroCiudadesInstancia, { 0 })
    val ciudades = Array<Pair<Double, Double>>(numeroCiudadesInstancia, { Pair(0.0, 0.0) })
    for (i in 0 until numeroCiudadesInstancia) {
        val ciudad = lectorInstancia.readLine().trim().split(" ")
        val indice = ciudad[0].trim().toInt()
        val x = ciudad[1].trim().toDouble()
        val y = ciudad[2].trim().toDouble()
        indicesInstancia[i] = indice - 1
        ciudades[i] = Pair(x, y)
    }
    lectorInstancia.close()
    val lectorSolucion = BufferedReader(FileReader(archivoSolucion, Charsets.UTF_8))
    lectorSolucion.readLine() 
    lectorSolucion.readLine() 
    lectorSolucion.readLine() 
    val numeroCiudadesSolucion = lectorSolucion.readLine().split(":")[1].trim().toInt()
    lectorSolucion.readLine() 
    val indicesSolucion = Array<Int>(numeroCiudadesSolucion, { 0 })
    for (i in 0 until numeroCiudadesSolucion) {
        val indice = lectorSolucion.readLine().trim().toInt()
        indicesSolucion[i] = indice-1
    }
    lectorSolucion.close()

    if (!comprobarSolucion(ciudades, indicesInstancia, indicesSolucion)) {
        println("Solución incorrecta")
        return
    }
    println("Solución correcta")
}
/**
* Entrada: p1 y p2, dos pares de enteros que representan las coordenadas de dos puntos
* Salida: La distancia euclidiana entre los dos puntos
* Descripcion: Calcula la distancia euclidiana entre dos puntos en el plano
*/
fun dist(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Double {
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x * x + y * y).toDouble())
}

/**
* Entradas: ciudades, un arreglo de pares de enteros que representan las coordenadas de las ciudades
* Salida: La distancia total de la ruta que recorre todas las ciudades en el orden dado
* Descripcion: Calcula la distancia total de la ruta que recorre todas las ciudades en el orden dado
*/
fun dististanciaTotal(ciudades: Array<Pair<Double, Double>>): Int {
    var acc: Double = 0.0
    for (i in 0 until ciudades.size - 1) {
        acc += dist(ciudades[i], ciudades[i + 1])
    }
    return acc.toInt()
}

/**
* Entrada: ciudadesInstancia, un arreglo de pares de enteros que representan las coordenadas de las ciudades de la instancia
*           ciudadesSolucion, un arreglo de pares de enteros que representan las coordenadas de las ciudades de la solucion
* Salida: true si la solucion es correcta, false en otro caso
* Descripcion: Verifica que la solucion dada sea correcta
*/
fun comprobarSolucion(ciudades: Array<Pair<Double, Double>>, indicesInstancia: Array<Int>, indicesSolucion: Array<Int>): Boolean {
    for (i in 0 until indicesSolucion.size) {
        for (j in i + 1 until indicesSolucion.size) {
            if (indicesSolucion[i] == indicesSolucion[j] && (i != 0 && j != indicesSolucion.size - 1)) {
                println("La solución tiene la ciudad ${indicesSolucion[i] + 1} repetida")
                return false
            }
        }

    for (k in 0 until indicesInstancia.size) {
        if (!indicesInstancia.contains(indicesSolucion[k])) {
            println("La solución no contiene la ciudad ${indicesSolucion[k] + 1}")
            return false
        }
    }
    }
   
    return true
}
