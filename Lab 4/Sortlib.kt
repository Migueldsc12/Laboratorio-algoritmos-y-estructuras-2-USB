/*
Laboratorio semana 3 / CI-2692
Autor: Miguel Salomon - Carnet: 19-10274
*/

//Creamos el procedimiento swap
fun swap(A: Array<Int>, i: Int, j: Int): Unit {
    val aux = A[i];
    A[i] = A[j];
    A[j] = aux;
}

// Creamos el procedimiento bubbleSort
// Pre: A es un arreglo de enteros
// Post: ordena los elementos de A de menor a mayor
fun bubbleSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                swap(A, j, j - 1);
            }
        }
    }
}

// Creamos el procedimiento insertionSort
// Pre: A es un arreglo de enteros
// Post: ordena los elementos de A de menor a mayor
fun insertionSort(A: Array<Int>): Unit {
    for (i in 1 until A.size) {
        var j = i;
        while (j > 0 && A[j] < A[j - 1]) {
            swap(A, j, j - 1);
            j -= 1;
        }
    }
}

// Creamos el procedimiento selectionSort
// Pre: A es un arreglo de enteros
// Post: ordena los elementos de A de menor a mayor
fun selectionSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        var min = i;
        for (j in (i + 1) until A.size) {
            if (A[j] < A[min]) {
                min = j;
            }
        }
        swap(A, i, min);
    }
}

// Creamos el procedimiento shellSort
// Pre: A es un arreglo de enteros
// Post: ordena los elementos de A de menor a mayor
fun shellSort(A: Array<Int>): Unit {
    var incr: Int = A.size / 2;
    while (incr > 0) {
        for (i in incr until A.size) {
            var j: Int = i - incr;
            while (j > -1) {
                if (A[j] > A[j + incr]) {
                    swap(A, j, j + incr);
                    j -= incr;
                } else {
                    j = -1;
                }
            }
        }
        incr /= 2;
    }
}

// Creamos el procedimiento merge
// Pre: A y B son arreglos de enteros ordenados de menor a mayor, C es un
// arreglo de enteros de tamaño A.size + B.size
// Post: C es un arreglo de enteros ordenados de menor a mayor que
// contiene todos los elementos de A y B
fun merge(A: Array<Int>, B: Array<Int>, C: Array<Int>): Unit {
    var i: Int = 0;
    var j: Int = 0;
    for (k in 0 until C.size) {
        if (i == A.size) {
            C[k] = B[j];
            j += 1;
        } else if (j == B.size) {
            C[k] = A[i];
            i += 1;
        } else if (A[i] < B[j]) {
            C[k] = A[i];
            i += 1;
        } else {
            C[k] = B[j];
            j += 1;
        }
    }
}

// Creamos el procedimiento mergeSortInsertion
// Pre: A es un arreglo de enteros
// Post: ordena los elementos de A de menor a mayor
fun mergesortInsertion(T: Array<Int>){
    if(T.size < 50){
        insertionSort(T);
    }else{
        val U: Array<Int> = Array(T.size / 2, {0});
        val V: Array<Int> = Array(T.size - U.size, {0});
        for (i in 0 until U.size) {
            U[i] = T[i];
        }
        for (i in 0 until V.size) {
            V[i] = T[i + U.size];
        }
        mergesortInsertion(U);
        mergesortInsertion(V);
        merge(U, V, T);
    }
}
//Creamos las funciones que representan a los padres e hijos en el algoritmo heapsort
fun parent(i: Int): Int {
    return i / 2;
}

fun left(i:Int): Int {
    return 2*i;
}

fun right(i:Int): Int {
    return 2 * i + 1;
}

//Creamos el procedimiento maxHeapify
//Pre: A un arreglo de enteros, i un entero que es el indice de un elemento en A, n el tamano del heap
//Post: A convertido en un max-heap
fun maxHeapify(A: Array<Int>, i: Int, n: Int){
    var l: Int = left(i);
    var r: Int = right(i);
    var largest: Int;
    if(l <= n && A[l] > A[i]){
        largest = l;
    }else{
        largest = i;
    }
    if(r <= n && A[r] > A[largest]){
        largest = r;
    }
    if(largest != i){
        swap(A, i, largest);
        maxHeapify(A, largest, n);
    }
}

//Creamos el procedimiento buildMaxHeap
//Pre: Un arreglo de enteros de tamano n
//Post: Arreglo de enteros convertido en un max-heap
fun buildMaxHeap(A: Array<Int>, n: Int){
    for(i in n / 2 downTo 0){
        maxHeapify(A, i, n);
    }
    
}
//Creamos el procedimiento heapSort
//Pre: Un arreglo de enteros
//Post: arreglo de enteros con elementos ordenados de forma creciente
fun heapSort(A: Array<Int>){
    var n: Int = A.size - 1;
    buildMaxHeap(A,n);
        for(i in n downTo 1){
            swap(A, 0, i);
            maxHeapify(A, 0, i-1);
        }
    
}
// Creamos el procedimiento smoothSort 
// Pre: A es un arreglo de enteros
// Post: Devuelve el arreglo ordenado
fun smoothSort(A: Array<Int>){
    val n = A.size;
    val B = Array<Int>(8, {0});
    var b = 1;
    var c = 1;
    var p = 1;
    var q = 0;
    var r = 0;
    B[0] = p;
    B[1] = b;
    B[2] = r;
    B[3] = q;
    B[4] = c;

    while (B[3] != n){
        B[5] = B[2];
        if (B[0]%8 == 3){
            B[7] = B[1];
            B[6] = B[4];
            sift(A, B);
            B[0] = (B[0] + 1)/4;
            up(B);
            up(B);
        }
        else if (B[0]%4 == 1){
            if (B[3] + B[4] < n){
                B[7] = B[1];
                B[6] = B[4];
                sift(A, B);
            }
            else {
                trinkle(A, B);
            }
            down(B);
            B[0] *= 2;
            while (B[1] != 1) {
                down(B);
                B[0] *= 2;
            }
            B[0]++;
        }
        B[3]++;
        B[2]++;
    }
    while (B[3] != 1){
        B[3]--;
        if (B[1] == 1){
            B[2]--;
            B[0]--;
            while (even(B[0])){
                B[0] /= 2;
                up(B);
            }
        }
        else if (B[1] >= 3){
            B[0] -= 1;
            B[2] = B[2] - B[1] + B[4];
                if (B[0] > 0){
                    semitrinkle(A, B);
                }
            down(B);
            B[0] = 2*B[0] + 1;
            B[2] += B[4];
            semitrinkle(A, B);
            down(B);
            B[0] = 2*B[0] + 1;
        }
    }
}
//Creamos los procedimientos necesarios para Smoothsort
//Para los siguientes procedimientos el arreglo B = [p, b, r, q, c, r1, c1, b1]

// Pre: B es un arreglo de enteros
// Post: realiza un downheap en el arreglo B
fun down1(B: Array<Int>){
    val n = B[6];
    B[6] = B[7] - B[6] - 1;
    B[7] = n;
}

// Pre: B es un arreglo de enteros
// Post: realiza un up en el arreglo B

fun up1(B: Array<Int>){
    val n = B[7];
    B[7] += B[6] + 1;
    B[6] = n;
}

// Pre: B es un arreglo de enteros
// Post: realiza un up en el arreglo
fun up(B: Array<Int>){
    val n = B[1];
    B[1] += B[4] + 1;
    B[4] = n;
}

// Pre: B es un arreglo de enteros
// Post: realiza un down en el arreglo
fun down(B: Array<Int>){
    val n = B[4];
    B[4] = B[1] - B[4] - 1;
    B[1] = n;
}

//Pre: n es un entero
//Post: devuelve true si n es par, false en caso contrario
fun even(n: Int): Boolean {
    return n % 2 == 0;
}

// Pre: A es un arreglo de enteros, B es un arreglo de enteros
// Post: realiza un sift en el arreglo A
fun sift(A: Array<Int>, B: Array<Int>){
    while (B[7] >= 3){
        var r2 = B[5] - B[7] + B[6];
        if (A[r2] <= A[B[5] - 1]){
            r2 = B[5] - 1;
            down1(B);
        }
        if (A[B[5]] >= A[r2]){
            B[7] = 1;
        }
        else {
            swap(A, B[5], r2);
            B[5] = r2;
            down1(B);
        }
    }
}
// Pre: A es un arreglo de enteros, B es un arreglo de enteros
// Post: realiza el semitrinkle en el arreglo A
fun semitrinkle(A: Array<Int>, B: Array<Int>){
    B[5] = B[2] - B[4];
        if (A[B[5]] > A[B[2]]){
            swap(A, B[5], B[2]);
            trinkle(A, B);
        }
}

//Pre: A es un arreglo de enteros, B es un arreglo
//Post: realiza el trinkle en el arreglo
fun trinkle(A: Array<Int>, B: Array<Int>){
    B[7] = B[1];
    B[6] = B[4];
    var p1 = B[0];
    while (p1 > 0){
        while (even(p1)){
            p1 /= 2;
            up1(B);
        }
            var r3 = B[5] - B[7];
        if (p1 == 1 || A[r3] <= A[B[5]]){
            p1 = 0;
        }
        else if (p1 > 1 && A[r3] > A[B[5]]){
            p1--;
            if (B[7] == 1){
                swap(A, B[5], r3);
                B[5] = r3;
            }
            else if (B[7] >= 3){
                var r2 = B[5] - B[7] + B[6];
                if (A[r2] <= A[B[5]-1]){
                    r2 = B[5] - 1;
                    down1(B);
                    p1 *= 2;
                }
                if (A[r3] >= A[r2]){
                    swap(A, B[5], r3);
                    B[5] = r3;
                }
                else{
                    swap(A, B[5], r2);
                    B[5] = r2;
                    down1(B);
                    p1 = 0;
                }
            }
        }
    }
        sift(A, B);
}


// Creamos el procedimiento QuickSort
fun quickSort(A: Array<Int>, p: Int, r: Int) {
    if (p < r) {
        val q: Int = partition(A, p, r);
        quickSort(A, p, q - 1);
        quickSort(A, q + 1, r);
    }
}

// Creamos el procedimiento Partition
fun partition(A: Array<Int>, p: Int, r: Int): Int {
    val x: Int = A[r];
    var i: Int = p - 1;
    for (j in p until r) {
        if (A[j] <= x) {
            i++;
            swap(A, i, j);
        }
    }
    swap(A, i + 1, r);
    return i + 1;
}

// Creamos el procedimiento QuickSort Clasico

fun quicksortClasico(A:Array<Int>){
    quickSort(A, 0, A.size - 1);
}

// Creamos el procedimiento QuickSort with 3-way partitioning

fun quicksortThreeWay(A:Array<Int>){
    ThreeWayAux(A, 0, A.size - 1);
}

//Creamos el quicksort with 3-way partitioning segun la presentacion
fun ThreeWayAux(A:Array<Int>, l:Int, r:Int){
    var i:Int = l - 1;
    var j:Int = r;
    var p:Int = l - 1;
    var q:Int = r;
    var v:Int = A[r];

    while(true){
        while(A[++i]< v);
        while(v < A[--j]){
            if(j == l){
            break;
            }
        }
        if(i >= j){
            break;
        }
        
        swap(A, i, j);

        if(A[i] == v){
            p++;
            swap(A, p, i);
        }
        if(v == A[j]){
            q--;
            swap(A, j, q);
        }
    }
    swap(A, i, r);
    j = i - 1;
    i = i + 1;

    for(k in l until p - 1){
        swap(A, k, j);
        j--;
    }
    for(k in r-1 downTo q){
        swap(A, i, k);
        i++;
    }
    quickSort(A, l, j);
    quickSort(A, i, r);
}

//Creamos el procedimiento Dual-pivot QuickSort

fun quicksortDualPivot(A:Array<Int>){
    dualPivotAux(A, 0, A.size - 1);
}

// Creamos el procedimiento Dual-pivot segun la presentacion

fun dualPivotAux(A:Array<Int>, left:Int, right:Int){
    if (right - left >= 1){
        if (A[left] > A[right]){
            swap(A, left, right);
        }
        val p = A[left];
        val q = A[right];
        var l = left + 1;
        var g = right - 1;
        var k = l;
        while (k <= g){
            if (A[k] < p){
                swap(A, k, l);
                l++;
            }
            else if (A[k] >= q){
                while (A[g] > q && k < g){
                    g--;
                }
                swap(A, k, g);
                g--;
                if (A[k] < p){
                    swap(A, k, l);
                    l++;
                }
            }
            k++;
        }
        l--;
        g++;
        swap(A, left, l);
        swap(A, right, g);
        dualPivotAux(A, left, l - 1);
        dualPivotAux(A, l + 1, g - 1);
        dualPivotAux(A, g + 1, right);
    }
}