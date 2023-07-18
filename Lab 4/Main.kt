fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    for (i in 0 until secuencia.size - 1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia[i] > secuencia[i + 1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    }
    }
    // Si no encontró ningún par de elementos consecutivos que no estén ordenados en forma ascendente
    return true
}

/**
* secuenciaRandom: Int -> Array<Int>
* Uso: secuenciaRandom(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos de tipo entero, generados
* aleatoriamente en el intervalo [0,..., n].
*/
fun secuenciaRandom(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio
        secuencia[i] = (0..n).random()
    }
    return secuencia
}

/**
* secuenciaOrdenada: Int -> Array<Int>
* Uso: secuenciaOrdenada(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos ordenados en forma ascendente.
*/
fun secuenciaOrdenada(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n
        secuencia[i] = i + 1
    }
    return secuencia
}

/**
* secuenciaInvertida: Int -> Array<Int>
* Uso: secuenciaInvertida(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos ordenados en forma descendente.
*/
fun secuenciaInvertida(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n en forma descendente
        secuencia[i] = n - i
    }
    return secuencia
}

/**
* secuenciaCerosYUnos: Int -> Array<Int>
* Uso: secuenciaCerosYUnos(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos con valores 0 o 1.
*/
fun secuenciaCerosYUnos(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio entre 0 y 1
        secuencia[i] = (0..1).random()
    }
    return secuencia
}

/**
* secuenciaMedia: Int -> Array<Int>
* Uso: secuenciaMedia(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos de tipo entero, en donde
* los elementos de la secuencia están ordenados en forma ascendente hasta el
* elemento n / 2 y luego en forma descendente hasta el último elemento. Por
* ejemplo, si n = 10, la secuencia tiene la forma [1, 2,..., n / 2, n / 2,..., 2, 1].
*/
fun secuenciaMedia(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n / 2) {
        // Itera sobre los elementos de la secuencia entre el primero y el elemento n/2 - 1
        secuencia[i] = i + 1
    }
    for (i in n / 2 until n) {
        // Itera sobre los elementos de la secuencia entre el elemento n/2 y el último
        secuencia[i] = n - i
    }
    return secuencia
}

/**
* crearSecuencia: String, Int -> Array<Int>
* Uso: crearSecuencia(tipoSecuencia, n)
* Precondición: tipoSecuencia es uno de los siguientes valores: "random", "sorted",
* "inv", "zu", "media"
* Poscondición: Devuelve una secuencia de n elementos con valores aleatorios,
* ordenados, invertidos, con ceros y unos o con valores en forma de media luna
*/
fun crearSecuencia(tipoSecuencia: String, n: Int ): Array<Int> {
    if (tipoSecuencia == "random") {
        return secuenciaRandom(n)
    } else if (tipoSecuencia == "sorted") {
        return secuenciaOrdenada(n)
    } else if (tipoSecuencia == "inv") {
        return secuenciaInvertida(n)
    } else if (tipoSecuencia == "zu") {
        return secuenciaCerosYUnos(n)
    } else if (tipoSecuencia == "media") {
        return secuenciaMedia(n)
    } else {
        // Si el tipo de secuencia no es ninguno de los anteriores, devuelve una secuencia ordenada
        println("\u001b[31mError:\u001b[0m Tipo de secuencia incorrecto. Se utilizará una secuencia ordenada")
        return secuenciaOrdenada(n)
    }
}

/**
* calcularMedia: Array<Long> -> Double
* Uso: calcularMedia(tiempo)
* Precondición: tiempo.size > 0
* Poscondición: Devuelve el promedio de los elementos del arreglo tiempo
*/
fun calcularMedia(tiempo: Array<Long>): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += tiempo[i]
    }
    return suma / tiempo.size
}

/**
* calcularDesviacionEstandar: Array<Long>, Double -> Double
* Uso: calcularDesviacionEstandar(tiempo, media)
* Precondición: tiempo.size > 1
* Poscondición: Devuelve la desviación estándar de los elementos del arreglo tiempo
*/
fun calcularDesviacionEstandar(tiempo: Array<Long>, media: Double): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += Math.pow((tiempo[i] - media), 2.0)
    }
    return Math.sqrt(suma / (tiempo.size))
}

/**
* Uso: mensajeOrdenamientoExitoso(tipoDeOrdenamiento, tiempo)
* Si el usuario especificó más de un intento, se calcula la media y la desviación estándar.
* Si el usuario especificó un solo intento, se imprime el tiempo de ejecución.
*/
fun mensajeOrdenamientoExitoso(tipoDeOrdenamiento: String, tiempo: Array<Long>): Unit {
    if (tiempo.size > 1) {
        val media: Double = calcularMedia(tiempo)
        val desviacionEstandar: Double = calcularDesviacionEstandar(tiempo, media)
        // Se divide entre 1000000000.0 para convertir el tiempo de nanosegundos a segundos
        println("\u001b[32mOrdenamiento exitoso con $tipoDeOrdenamiento en ${tiempo.size} intentos \u001b[0m")
        println("Tiempo promedio de ejecución de $tipoDeOrdenamiento: \u001b[33m${(media/1000000000.0).toDouble()} segundos \u001b[0m")
        println("Desviación estándar de $tipoDeOrdenamiento: \u001b[33m${(desviacionEstandar/1000000000.0).toDouble()} segundos \u001b[0m")
    } else {
        println("\u001b[32mOrdenamiento exitoso con $tipoDeOrdenamiento \u001b[0m")
        println("Tiempo de ejecución de $tipoDeOrdenamiento: \u001b[33m${(tiempo[0]/1000000000.0).toDouble()} segundos \u001b[0m")
    }
    println("----------------------------------------------------------------------------------------")
}

/**
* Uso: mensajeErrorEnElOrdenamiento(tipoDeOrdenamiento)
* Imprime un mensaje de error indicando que la secuencia no está ordenada con el tipo de
* ordenamiento especificado
*/
fun mensajeErrorEnElOrdenamiento(tipoDeOrdenamiento: String): Unit {
    println("\u001b[31mError:\u001b[0m La secuencia no está ordenada con $tipoDeOrdenamiento \u001b[0m")
    println("----------------------------------------------------------------------------------------")
}

/**
* Uso: chekearInputValido(args)
* Precondición: args.size == 6
* Poscondición: Devuelve true si los argumentos son válidos, false en caso contrario
*/
fun chekearInputValido(args: Array<String>): Boolean {
    if (args.size != 6){
        // Si la cantidad de argumentos es distinta de 6, imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Cantidad de argumentos incorrecta")
        println("Uso: ./runSorlib.sh -n <tamañoSecuencia> -t <numIntentos> -s <tipoSecuencia>")
        return false
    }
    if (args[0] != "-n" && args[2] != "-n" && args[4] != "-n") {
        // Si -n no está en la posición 0, 2 o 4, imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Argumentos incorrectos")
        println("Uso: ./runSorlib.sh -n <tamañoSecuencia> -t <numIntentos> -s <tipoSecuencia>")
        return false
    } else if (args[0] != "-t" && args[2] != "-t" && args[4] != "-t") {
        // Si -t no está en la posición 0, 2 o 4, imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Argumentos incorrectos")
        println("Uso: ./runSorlib.sh -n <tamañoSecuencia> -t <numIntentos> -s <tipoSecuencia>")
        return false
    } else if (args[0] != "-s" && args[2] != "-s" && args[4] != "-s") {
        // Si -s no está en la posición 0, 2 o 4, imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Argumentos incorrectos")
        println("Uso: ./runSorlib.sh -n <tamañoSecuencia> -t <numIntentos> -s <tipoSecuencia>")
        return false
    }
    return true
}

// Declaración del método principal
fun main(args: Array<String>): Unit {
    if (!chekearInputValido(args)) {
        return
    }
    // Crea un diccionario con los argumentos pasados por línea de comandos
    // Ejemplo: ./runSortlib.sh -n 100 -t 10 -s random
    val consts = mapOf(args[0] to args[1], args[2] to args[3], args[4] to args[5])
    // Obtiene los valores de los argumentos
    val n : Int = consts["-n"]!!.toInt()
    val intentos : Int = consts["-t"]!!.toInt()
    val tipoSecuencia = consts["-s"]!!

    // Crea una secuencia según el tipo y el tamaño pasados por línea de comandos
    val secuencia = crearSecuencia(tipoSecuencia, n)
    println("\u001b[36mSecuencia de tamaño $n generada de tipo $tipoSecuencia\u001b[0m")
    println("----------------------------------------------------------------------------------------")

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
            mensajeErrorEnElOrdenamiento("Shell Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoShellSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Shell Sort", tiempoShellSort)

    // Se realizan intentos de ordenamiento con Merge Sort y se mide el tiempo de ejecución
    val tiempoMergesortInsertion = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        mergesortInsertion(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Mergesort Insertion")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoMergesortInsertion[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Mergesort Insertion", tiempoMergesortInsertion)

    // Se realizan intentos de ordenamiento con Heap Sort y se mide el tiempo de ejecución
    val tiempoHeapSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        heapSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Heap Sort")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoHeapSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Heap Sort", tiempoHeapSort)

    // Se realizan intentos de ordenamiento con Smooth Sort y se mide el tiempo de ejecución
    val tiempoSmoothSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        smoothSort(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Smooth Sort")
            println("Secuencia original: " + secuencia.contentToString())
            println("Secuencia procesada: " + secuenciaCopia.contentToString())
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoSmoothSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Smooth Sort", tiempoSmoothSort)

    // Se realizan intentos de ordenamiento con Quick Sort clásico y se mide el tiempo de ejecución
    val tiempoQuickSort = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        quicksortClasico(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Quick Sort clásico")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoQuickSort[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Quick Sort clásico", tiempoQuickSort)

    //Se realizan intentos de ordenamiento con Quick Sort con 3 formas de particionamiento y se mide el tiempo de ejecución
    val tiempoQuickSort3Formas = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        quicksortThreeWay(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Quick Sort con 3 formas de particionamiento")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoQuickSort3Formas[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Quick Sort con 3 formas de particionamiento", tiempoQuickSort3Formas)

    // Se realizan intentos de ordenamiento con Quick Sort de Doble Pivote y se mide el tiempo de ejecución
    val tiempoQuickSortDoblePivote = Array<Long>(intentos, {0})
    for (i in 0 until intentos) {
        // Se copia la secuencia original para no modificarla
        val secuenciaCopia = secuencia.copyOf()
        // Se ordena la secuencia copia y se mide el tiempo de ejecución
        val tiempoInicial = System.nanoTime()
        quicksortDualPivot(secuenciaCopia)
        val tiempoFinal = System.nanoTime()
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            mensajeErrorEnElOrdenamiento("Quick Sort de Doble Pivote")
            return
        }
        // Se guarda el tiempo de ejecución
        tiempoQuickSortDoblePivote[i] = (tiempoFinal - tiempoInicial)
    }
    // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
    mensajeOrdenamientoExitoso("Quick Sort de Doble Pivote", tiempoQuickSortDoblePivote)
}