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
        secuencia[i] = (0..1000000).random()
    }
    return secuencia
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

// Declaración del método principal
fun main(): Unit {
    // Se especifica el número de intentos que se realizarán para cada tamaño de secuencia
    val intentos: Int = 3

    for (n in 1000000..4000000 step 1000000) {
         // Se crean las secuencias aleaorias de tamaño n
        var secuencias: Array<Array<Int>> = Array(3, {Array(n, {0})})
        for (i in 0 until intentos) {
            secuencias[i] = secuenciaRandom(n)
        }
        println("\u001b[36mTres secuencias de tamaño $n generadas de tipo random\u001b[0m")
        println("----------------------------------------------------------------------------------------")

        println("\u001b[34mAlgoritmos con rendimiento O(n * log n):\u001b[0m")
        println("----------------------------------------------------------------------------------------")

        // Se realizan intentos de ordenamiento con Merge Sort y se mide el tiempo de ejecución
        val tiempoMergesortInsertion = Array<Long>(intentos, {0})
        for (i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
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
            val secuenciaCopia = secuencias[i].copyOf()
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
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            smoothSort(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Smooth Sort")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoSmoothSort[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Smooth Sort", tiempoSmoothSort)

        println("\u001b[34mVariaciones de Quick Sort:\u001b[0m")
        println("----------------------------------------------------------------------------------------")

        // Se realizan intentos de ordenamiento con Quick Sort y se mide el tiempo de ejecución
        val tiempoQuickSortClasico = Array<Long>(intentos, {0})
        for (i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            quicksortClasico(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Quick Sort Clásico")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoQuickSortClasico[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Quick Sort Clásico", tiempoQuickSortClasico)

        // Se realizan intentos de ordenamiento con Quick Sort con 3 formas y se mide el tiempo de ejecución
        val tiempoQuickSort3Formas = Array<Long>(intentos, {0})
        for(i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            quicksortThreeWay(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Quick Sort con 3 formas")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoQuickSort3Formas[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Quick Sort con 3 formas", tiempoQuickSort3Formas)

        // Se realizan intentos de ordenamiento con Quick Sort con Doble Pivote y se mide el tiempo de ejecución
        val tiempoQuickSortDoblePivote = Array<Long>(intentos, {0})
        for(i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            quicksortDualPivot(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Quick Sort con Doble Pivote")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoQuickSortDoblePivote[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Quick Sort con Doble Pivote", tiempoQuickSortDoblePivote)

        println("\u001b[34mAlgoritmos de ordenamiento lineal:\u001b[0m")
        println("----------------------------------------------------------------------------------------")

        // Se realizan intentos de ordenamiento con Counting Sort y se mide el tiempo de ejecución
        val tiempoCountingSort = Array<Long>(intentos, {0})
        for(i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            countingSort(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Counting Sort")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoCountingSort[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Counting Sort", tiempoCountingSort)

        // Se realizan intentos de ordenamiento con Radix Sort y se mide el tiempo de ejecución
        val tiempoRagixSort = Array<Long>(intentos, {0})
        for(i in 0 until intentos) {
            // Se copia la secuencia original para no modificarla
            val secuenciaCopia = secuencias[i].copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            radixSort(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento("Radix Sort")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoRagixSort[i] = (tiempoFinal - tiempoInicial)
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso("Radix Sort", tiempoRagixSort)
    }

    println("\u001b[1m\u001b[32mTodos los ordenamientos fueron exitosos\u001b[0m")
    println("----------------------------------------------------------------------------------------")
}