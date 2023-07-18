// estaEnOrdenAscendente: Array<Int> -> Boolean
// Uso: estaEnOrdenAscendente(A)
// Precondición: A.size > 0
// Poscondición: Devuelve true si A está ordenado en forma ascendente
fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    val n = secuencia.size
    for (i in 0 until n-1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia [i] > secuencia [i+1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    }
    }
    // Si no encontró ningún par de elementos consecutivos que no estén ordenados en forma ascendente
    return true
}

// secuenciaRandom: Int -> Array<Int>
// Uso: secuenciaRandom(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores aleatorios
fun secuenciaRandom(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio
        secuencia[i] =  (0..n).random()
    }
    return secuencia
}

// secuenciaOrdenada: Int -> Array<Int>
// Uso: secuenciaOrdenada(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos ordenados en forma ascendente
fun secuenciaOrdenada(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n
        secuencia[i] =  i+1
    }
    return secuencia
}

// secuenciaInvertida: Int -> Array<Int>
// Uso: secuenciaInvertida(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos ordenados en forma descendente
fun secuenciaInvertida(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n en forma descendente
        secuencia[i] =  n-i
    }
    return secuencia
}

// secuenciaCerosYUnos: Int -> Array<Int>
// Uso: secuenciaCerosYUnos(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores 0 o 1
fun secuenciaCerosYUnos(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio entre 0 y 1
        secuencia[i] =  (0..1).random()
    }
    return secuencia
}

// secuenciaMedia: Int -> Array<Int>
// Uso: secuenciaMedia(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores en
fun secuenciaMedia(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n/2) {
        // Itera sobre los elementos de la secuencia entre el primero y el elemento n/2 - 1
        secuencia[i] =  i+1
    }
    for (i in n/2 until n) {
        // Itera sobre los elementos de la secuencia entre el elemento n/2 y el último
        secuencia[i] =  n-i
    }
    return secuencia
}

// tipoSecuencia: String, Int -> Array<Int>
// Uso: tipoSecuencia(tipo, n)
// Precondición: tipo es uno de los siguientes valores: "random", "sorted", "inv", "zu", "media"
// Poscondición: Devuelve una secuencia de n elementos con valores aleatorios, ordenados, invertidos, con ceros y unos o con valores en forma de media luna
fun tipoSecuencia(tipo : String, n : Int ): Array<Int> {
    if(tipo == "random"){
        return secuenciaRandom(n)
    }
    else if(tipo == "sorted"){
        return secuenciaOrdenada(n)
    }
    else if(tipo == "inv"){
        return secuenciaInvertida(n)
    }
    else if(tipo == "zu"){
        return secuenciaCerosYUnos(n)
    }
    else if(tipo == "media"){
        return secuenciaMedia(n)
    }
    else{
        // Si el tipo de secuencia no es ninguno de los anteriores, devuelve una secuencia ordenada
        println("Error: Tipo de secuencia incorrecto. Se utilizará una secuencia ordenada")
        return secuenciaOrdenada(n)
    }
}

fun calcularMedia(tiempo: Array<Long>): Double {
    var suma = 0.0
    for (i in 0 until tiempo.size) {
        suma += tiempo[i]
    }
    return suma/tiempo.size
}

fun calcularDesviacionEstandar(tiempo: Array<Long>, media: Double): Double {
    var suma = 0.0
    for (i in 0 until tiempo.size) {
        suma += Math.pow((tiempo[i] - media), 2.0)
    }
    return Math.sqrt(suma/(tiempo.size - 1))
}

fun mensaje(tipo: String, tiempo: Array<Long>) {
    // Imprime el tiempo promedio de ejecución y la desviación estándar en segundos
    // Se divide por 1000000000.0 para convertir de nanosegundos a segundos
    if(tiempo.size > 1){
        val media : Double = calcularMedia(tiempo)
        val desviacionEstandar : Double = calcularDesviacionEstandar(tiempo, media)
        println("\u001b[32mOrdenamiento exitoso con $tipo en ${tiempo.size} intentos \u001b[0m")
        println("Tiempo promedio de ejecución de $tipo: \u001b[33m${(media/1000000000.0).toDouble()} segundos \u001b[0m")
        println("Desviación estándar de $tipo: \u001b[33m${(desviacionEstandar/1000000000.0).toDouble()} segundos \u001b[0m")
    }
    else{
        println("\u001b[32mOrdenamiento exitoso con $tipo \u001b[0m")
        println("Tiempo de ejecución de $tipo: \u001b[33m${(tiempo[0]/1000000000.0).toDouble()} segundos \u001b[0m")
    }
    println("----------------------------------------------------------------------------------------")
}

fun error(tipo: String) {
    println("\u001b[31mError: La secuencia no está ordenada con $tipo \u001b[0m")
    println("----------------------------------------------------------------------------------------")
}

fun main(args: Array<String>) {
    if(args.size != 6){
        // Si la cantidad de argumentos es distinta de 6, imprime un mensaje de error y termina la ejecución
        println("Error: Cantidad de argumentos incorrecta")
        println("Uso: ./main -n <tamaño> -t <intentos> -s <tipo>")
        return
    }
    // Crea un diccionario con los argumentos pasados por línea de comandos
    // Ejemplo: ./main -n 100 -t 10 -s random
    val consts = mapOf(args[0] to args[1], args[2] to args[3], args[4] to args[5])
    // Obtiene los valores de los argumentos
    val n : Int = consts["-n"]!!.toInt()
    val intentos : Int = consts["-t"]!!.toInt()
    val tipo = consts["-s"]!!

    // Crea una secuencia según el tipo y el tamaño pasados por línea de comandos
    val secuencia = tipoSecuencia(tipo, n)
    println("\u001b[100mSecuencia de tamaño $n generada de tipo $tipo\u001b[0m")
    println("----------------------------------------------------------------------------------------")

    // Se realizan intentos de ordenamiento con Bubble Sort
    val tiempoBubbleSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        bubbleSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            error("Bubble Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoBubbleSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensaje("Bubble Sort", tiempoBubbleSort)

    // Se realizan intentos de ordenamiento con Insertion Sort y se mide el tiempo de ejecución
    val tiempoInsertionSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        insertionSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            error("Insertion Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoInsertionSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensaje("Insertion Sort", tiempoInsertionSort)

    // Se realizan intentos de ordenamiento con Selection Sort y se mide el tiempo de ejecución
    val tiempoSelectionSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        selectionSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            error("Selection Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoSelectionSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensaje("Selection Sort", tiempoSelectionSort)

    // Se realizan intentos de ordenamiento con Shell Sort y se mide el tiempo de ejecución
    val tiempoShellSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        shellSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            error("Shell Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoShellSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensaje("Shell Sort", tiempoShellSort)
}