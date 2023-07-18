//Autor: Miguel Salomon - 1910274

//Examen 2 - CI-2692

// Definición del TAD Sindicato
class Sindicato(val periodicidad: Int) {
    private var contadorHuelgas: Int = 0

    fun realizarHuelga() {
        contadorHuelgas++
    }

    fun obtenerContadorHuelgas(): Int {
        return contadorHuelgas
    }
}

// Función para contar los días de huelga en un período
fun contarDiasHuelga(periodo: Int, sindicatos: List<Sindicato>): Int {
    val diasSemana = listOf("L", "Ma", "Mi", "J", "V", "S", "D") // Lista de días de la semana
    var contadorHuelgas = 0

    for (dia in 2..periodo) { // Iterar sobre cada día en el período menos el primer lunes
        val diaSemana = diasSemana[(dia - 1) % 7] // Obtener el día de la semana correspondiente al día actual

        if (diaSemana != "S" && diaSemana != "D") { // No se hacen huelgas los sábados ni domingos
            for (sindicato in sindicatos) {
                if (dia % sindicato.periodicidad == 0) { // Si el día actual es un día de huelga para el sindicato
                    sindicato.realizarHuelga() // Incrementar el contador de días de huelga del sindicato
                    contadorHuelgas++ // Incrementar el contador de días de huelga total
                    break
                }
            }
        }
    }

    return contadorHuelgas
}

fun main(args: Array<String>) {

    val periodo = args[0].toInt() // Obtener el período
    val numSindicatos = args[1].toInt() // Obtener el número de sindicatos
    val sindicatos = args.sliceArray(2..numSindicatos + 1).map { Sindicato(it.toInt()) } // Obtener las periodicidades de los sindicatos y crear objetos Sindicato

    val diasHuelga = contarDiasHuelga(periodo, sindicatos) // Contar los días de huelga
    println("Total de días de huelga: $diasHuelga") // Imprimir el resultado

}
