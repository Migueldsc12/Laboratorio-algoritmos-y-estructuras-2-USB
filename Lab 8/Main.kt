import kotlin.system.measureTimeMillis
import kotlin.system.exitProcess
import kotlin.math.*

/*
        Autor: Miguel Salomon - 1910274
*/

/**
 * Recorre el arreglo de pares (clave, valor) dado y busca cada
 * elemento en la tabla de hash. Si el elemento existe, se elimina
 * de esta; de lo contrario, se agrega.
 */
fun testDictionary(
    dict: Diccionario,
    elementos: Array<Pair<Int, String>>
    ): Pair<Long, Int> {

    val time = measureTimeMillis {
        for ((clave, valor) in elementos) {
            if (dict.existe(clave)) dict.eliminar(clave)
            else dict.agregar(clave, valor)
        }
    }
    
    return Pair(time, dict.numElementos())
}

/**
 * Función que calcula el tiempo promedio dado un arreglo que contenga los tiempos de ejecución de cada prueba.
 */
fun calcularMedia(tiempos: Array<Long>): Double {
    var suma = 0.0
    for (i in 0 until tiempos.size) {
        suma += tiempos[i]
    }
    return (suma / tiempos.size)
}

/**
 * Función que calcula la desviación estándar de los tiempos de ejecución de cada prueba.
 */
fun calcularDesviacionEstandar(tiempos: Array<Long>, media: Double): Double {
    var suma = 0.0
    for (i in 0 until tiempos.size) {
        suma += (tiempos[i] - media).pow(2)
    }
    return sqrt(suma / tiempos.size)
}

/**
 * Programa cliente que hace pruebas sobre el rendimiento
 * de las clases implementadas: HashTableChaining y CuckooHashTable.
 *
 * Las pruebas se ejecutan con un arreglo del tamaño
 * especificado por el usuario a través de la linea de
 * comandos.
 */
fun main(args: Array<String>) {
    val n = try {
        args[0].toInt()
    } catch (e: NumberFormatException) {
        println("Error: El número dado debe ser un entero positivo.")
        exitProcess(1)
    }

    if (n <= 0) {
        println("Error: El número dado debe ser un entero positivo.")
        exitProcess(1)
    }

    val b = n / 3
    // Se genera un arreglo de n enteros en el intervalo [0..n/3]
    val claves = Array<Int>(n, { (0..b).random() })

    /* Se obtiene el arreglo de pares (clave, valor), donde
    el valor asociado a cada clave es resultado de convertir
    cada clave correspondiente a una string. */
    val pares = Array<Pair<Int, String>>(n,{ Pair(claves[it], claves[it].toString()) })

    val sep = "==========================================================="

    println("$sep\nEjecutando pruebas de implementaciones del TAD Diccionario:")

    println(" Cantidad de claves: $n")
    println(" Intervalo: [0..$b] \n")

    val hashTiempos = Array<Long>(5, {0})
    val cuckooTiempos = Array<Long>(5, {0})

    for (i in 0 until 5) {
        print("Prueba ${i+1}: ")
        val hashTimer = measureTimeMillis { testDictionary(HashTableChaining(), pares) }
        hashTiempos[i] = hashTimer
        println("Hash: $hashTimer ms")
        print("         ")
        val cuckooTimer = measureTimeMillis { testDictionary(CuckooHashTable(), pares) }
        cuckooTiempos[i] = cuckooTimer
        println("Cuckoo: $cuckooTimer ms")
    }

    val hashMedia = calcularMedia(hashTiempos)
    val hashDesviacion = calcularDesviacionEstandar(hashTiempos, hashMedia)

    val cuckooMedia = calcularMedia(cuckooTiempos)
    val cuckooDesviacion = calcularDesviacionEstandar(cuckooTiempos, cuckooMedia)

    println("Hash Table:   Tiempo promedio de las 5 pruebas: $hashMedia ms, desviación estándar: $hashDesviacion ms.")
    println("Cuckoo Table: Tiempo promedio de las 5 pruebas: $cuckooMedia ms, desviación estándar: $cuckooDesviacion ms.\n")

    if (testDictionary(HashTableChaining(), pares).second != testDictionary(CuckooHashTable(), pares).second) {
        print("Ha habido un error en las pruebas. Los diccionarios resultantes")
        println(" tienen cantidades distintas de elementos.")
        exitProcess(1)
    }

    println("Cantidad final de elementos en cada diccionario: ${testDictionary(HashTableChaining(), pares).second}. \n$sep")
}