//Autor: Miguel Salomon - 1910274
/**
 * Metodo cliente que decodifica el codigo morse recibido por consola.
 * Pre: El argumento recibido por consola debe ser el codigo morse.
 * Post: Texto decodificado del codigo morse recibido por consola.
 */
fun main(args: Array<String>) {
    val sep = ("===========================================================");
    val codigo = CrearCodigoMorse();
    val texto = args[0];
    val mensajeDecodificado = codigo.decodificarMensaje(texto);
    if (mensajeDecodificado == "") {
        println(sep);
        println("El codigo morse ingresado no es valido.");
        println(sep);
        return;
    }
    println(sep);
    println("\nTenga presente que el mensaje a decodificar debe estar en comillas dobles\n")
    println(sep);
    println("\nMensaje decodificado: '$mensajeDecodificado' \n");
    println(sep);
}