// Autor: Miguel Salomon - 1910274

fun main() {
    bienvenida()
    var ayudanteOrtografico = AyudanteOrtografico()
    var eleccion: Int
    var closeAyudante = false
    while (closeAyudante == false) {
        elecciones()
        eleccion = readLine()!!.toInt()
        
            if(eleccion == 1) {
                ayudanteOrtografico = AyudanteOrtografico()
                println("Se ha creado un Ayudante Ortográfico")
            
            }else if(eleccion == 2) {
                println("El diccionario debe ser un archivo de texto con una palabra por línea.")
                println("Ingrese el nombre del archivo que contiene el diccionario incluyendo el '.txt': ")
                val fname = readLine()!!
                ayudanteOrtografico.cargarDiccionario(fname)
            
            }else if(eleccion == 3) {                
                print("Ingrese la palabra que desea eliminar del diccionario: ")
                val palabra = readLine()!!
                ayudanteOrtografico.borrarPalabra(palabra)
            
            }else if(eleccion == 4) {
                println("Ingrese el nombre del texto (archivo .txt): ")
                val fname = readLine()!!
                println("Ingrese el nombre del archivo donde desea guardar las sugerecias: ")
                val fname2 = readLine()!!
                ayudanteOrtografico.corregirTexto(fname, fname2)

            }else if(eleccion == 5) {
                ayudanteOrtografico.imprimirDiccionario()
            
            }else if(eleccion == 6){
                println("Gracias por usar el ayudante, nos vemos.")
                closeAyudante = true
            
            }else{                 
                println("Opción inválida. \n Escriba un número entre 1 y 6.\n")
            }
        }
    }

fun bienvenida() {
    val sep = ("==================================================================================================================================== \n");
    println(sep)
    println("Bienvenido al Asistente Ortográfico")
    println("Por favor ingresa un diccionario con palabras válidas.\n")
    println(sep)
}

fun elecciones() {
    println("Puedes realizar las siguientes operaciones:")
    println("1.Crear un nuevo ayudante ortográfico.")
    println("2.Cargar un diccionario.")
    println("3.Eliminar una palabra del diccionario.")
    println("4.Corregir texto.")
    println("5.Mostrar diccionario.")
    println("6.Salir de la aplicación.")
    print("Escriba el numero de la eleccion que quiere realizar: \n")
}