/*
Laboratorio semana 2 / CI-2692
Autor: Miguel Salomon - Carnet: 19-10274
*/

//Creamos el procedimiento de multiplicacion de matrices simples
fun multiplicacionSimpleDeMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    if(A.size != B.size) {
        throw Exception("Las matrices no son cuadradas")
    }
    val C = Array(A.size) { IntArray(B[0].size) }
    for (i in A.indices) {
        for (j in B[0].indices) {
            for (k in B.indices) {
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }
    return C
}

//Creamos los procedimiento sumar y restar matrices
fun sumarMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    if(A.size != B.size) {
        throw Exception("Las matrices no son cuadradas")
    }
    val C = Array(A.size) { IntArray(A[0].size) }
    for (i in A.indices) {
        for (j in A[0].indices) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
    return C
}

fun restarMatrices(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    if(A.size != B.size) {
        throw Exception("Las matrices no son cuadradas")
    }
    val C = Array(A.size) { IntArray(A[0].size) }
    for (i in A.indices) {
        for (j in A[0].indices) {
            C[i][j] = A[i][j] - B[i][j];
        }
    }
    return C
}

//Creamos el procedimiento de multiplicacion con Strassen
fun multiplicacionStrassen(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
    if(A.size != B.size) {
        throw Exception("Las matrices no son cuadradas")
    }

    val C = Array(A.size) { IntArray(A.size) }

    if (A.size == 1){
        C[0][0] = A[0][0] * B[0][0]
    }
    else if (A.size%2 != 0){
        val A1 = Array(A.size+1) { IntArray(A.size+1) }
        val B1 = Array(A.size+1) { IntArray(A.size+1) }

        for (i in 0 until A.size) {
            for (j in 0 until A.size) {
                A1[i][j] = A[i][j]
                B1[i][j] = B[i][j]
            }
        }

        val C1 = multiplicacionStrassen(A1, B1)
        for (i in 0 until A.size) {
            for (j in 0 until A.size) {
                C[i][j] = C1[i][j]
            }
        }
    }
    else {

        val A11 = Array(A.size/2) { IntArray(A.size/2) }
        val A12 = Array(A.size/2) { IntArray(A.size/2) }
        val A21 = Array(A.size/2) { IntArray(A.size/2) }
        val A22 = Array(A.size/2) { IntArray(A.size/2) }
        val B11 = Array(A.size/2) { IntArray(A.size/2) }
        val B12 = Array(A.size/2) { IntArray(A.size/2) }
        val B21 = Array(A.size/2) { IntArray(A.size/2) }
        val B22 = Array(A.size/2) { IntArray(A.size/2) }

        for (i in 0 until A.size/2) {
            for (j in 0 until A.size/2) {
                A11[i][j] = A[i][j]
                A12[i][j] = A[i][j + A.size/2]
                A21[i][j] = A[i + A.size/2][j]
                A22[i][j] = A[i + A.size/2][j + A.size/2]
                B11[i][j] = B[i][j]
                B12[i][j] = B[i][j + A.size/2]
                B21[i][j] = B[i + A.size/2][j]
                B22[i][j] = B[i + A.size/2][j + A.size/2]
            }
        }

        // Calculamos los productos de Strassen
        val P1 = multiplicacionStrassen(A11, restarMatrices(B12, B22))
        val P2 = multiplicacionStrassen(sumarMatrices(A11, A12), B22)
        val P3 = multiplicacionStrassen(sumarMatrices(A21, A22), B11)
        val P4 = multiplicacionStrassen(A22, restarMatrices(B21, B11))
        val P5 = multiplicacionStrassen(sumarMatrices(A11, A22), sumarMatrices(B11, B22))
        val P6 = multiplicacionStrassen(restarMatrices(A12, A22), sumarMatrices(B21, B22))
        val P7 = multiplicacionStrassen(restarMatrices(A11, A21), sumarMatrices(B11, B12))

        // Calcular las sumas y restas de C
        val C11 = sumarMatrices(restarMatrices(sumarMatrices(P5, P4), P2), P6)
        val C12 = sumarMatrices(P1, P2)
        val C21 = sumarMatrices(P3, P4)
        val C22 = restarMatrices(restarMatrices(sumarMatrices(P5, P1), P3), P7)

        for (i in 0 until A.size/2) {
            for (j in 0 until A.size/2) {
                C[i][j] = C11[i][j]
                C[i][j + A.size/2] = C12[i][j]
                C[i + A.size/2][j] = C21[i][j]
                C[i + A.size/2][j + A.size/2] = C22[i][j]
            }
        }
    }
    return C
}