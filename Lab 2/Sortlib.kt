/*
Laboratorio semana 2 / CI-2692
Autor: Miguel Salomon - Carnet: 19-10274
*/

//Creamos el procedimiento swap
fun swap(A: Array<Int>, i: Int, j: Int): Unit {
    val aux = A[i]
    A[i] = A[j]
    A[j] = aux
}

/**
* Creamos el procedimiento bubbleSort
* Pre: A es un arreglo de enteros
* Post: ordena los elementos de A de menor a mayor
*/
fun bubbleSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                swap(A, j, j - 1)
            }
        }
    }
}

/**
* Creamos el procedimiento insertionSort
* Pre: A es un arreglo de enteros
* Post: ordena los elementos de A de menor a mayor
*/
fun insertionSort(A: Array<Int>): Unit {
    for (i in 1 until A.size) {
        var j = i
        while (j > 0 && A[j] < A[j - 1]) {
            swap(A, j, j - 1)
            j -= 1
        }
    }
}

/**
* Creamos el procedimiento selectionSort
* Pre: A es un arreglo de enteros
* Post: ordena los elementos de A de menor a mayor
*/
fun selectionSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        var min = i
        for (j in (i + 1) until A.size) {
            if (A[j] < A[min]) {
                min = j
            }
        }
        swap(A, i, min)
    }
}

/**
* Creamos el procedimiento shellSort
* Pre: A es un arreglo de enteros
* Post: ordena los elementos de A de menor a mayor
*/
fun shellSort(A: Array<Int>): Unit {
    var incr: Int = A.size / 2
    while (incr > 0) {
        for (i in incr until A.size) {
            var j: Int = i - incr
            while (j > -1) {
                if (A[j] > A[j + incr]) {
                    swap(A, j, j + incr)
                    j -= incr
                } else {
                    j = -1
                }
            }
        }
        incr /= 2
    }
}

/**
* Creamos el procedimiento merge
* Pre: A y B son arreglos de enteros ordenados de menor a mayor, C es un
* arreglo de enteros de tamaño A.size + B.size
* Post: C es un arreglo de enteros ordenados de menor a mayor que
* contiene todos los elementos de A y B
*/
fun merge(A: Array<Int>, B: Array<Int>, C: Array<Int>): Unit {
    var i: Int = 0
    var j: Int = 0
    for (k in 0 until C.size) {
        if (i == A.size) {
            C[k] = B[j]
            j += 1
        } else if (j == B.size) {
            C[k] = A[i]
            i += 1
        } else if (A[i] < B[j]) {
            C[k] = A[i]
            i += 1
        } else {
            C[k] = B[j]
            j += 1
        }
    }
}

/**
* Creamos el procedimiento mergeSortInsertion
* Pre: A es un arreglo de enteros
* Post: ordena los elementos de A de menor a mayor
*/
fun mergesortInsertion(T: Array<Int>){
    if(T.size < 50){
        insertionSort(T);
    }else{
        val U: Array<Int> = Array(T.size / 2, {0})
        val V: Array<Int> = Array(T.size - U.size, {0})
        for (i in 0 until U.size) {
            U[i] = T[i]
        }
        for (i in 0 until V.size) {
            V[i] = T[i + U.size]
        }
        mergesortInsertion(U)
        mergesortInsertion(V)
        merge(U, V, T)
    }
}