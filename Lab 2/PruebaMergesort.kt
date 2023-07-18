/*
Laboratorio semana 2 / CI-2692
Autor: Miguel Salomon - Carnet: 19-10274
*/

//Creamos el procedimiento mergesort de prueba que reciba un n para la cantidad de pruebas a hacer

fun mergesortInsertionPrueba(T: Array<Int>, n: Int){
    if(T.size < n){
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


