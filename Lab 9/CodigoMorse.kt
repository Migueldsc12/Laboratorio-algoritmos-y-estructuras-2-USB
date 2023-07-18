//Autor: Miguel Salomon - 1910274

/**
 * Representacion el TAD Código Morse que sirve para descifrar el codigo morse.
 */
public class CrearCodigoMorse() {
    private val alphabet = ArbolBinarioDeDecision();

    /**
     * Recibe un codigo morse y devuelve la letra correspondiente || null si el codigo morse no es valido.
     * Pre: true.
     * Post: Letra correspondiente al codigo morse || null si el codigo morse no es valido.
     */
    fun decodificarLetra(secuencia: String): Char? {
        return alphabet.buscar(secuencia);
    }

    /**
     * Recibe un mensaje en codigo morse y devuelve el mensaje en texto correspondiente || un mensaje vacio si el mensaje en codigo morse no es valido.
     * Pre: true.
     * Post: Mensaje en texto correspondiente al mensaje en codigo morse.
     */
    fun decodificarMensaje(frase: String): String {
        val palabras = frase.split("/");
        var fraseDescifrada = "";
        for (p in palabras) {
            for (i in p) {
                if (i != '.' && i != '-' && i != ' ') {
                    return "";
                }
            }
        }
        for (palabra in palabras) {
            val letra = palabra.trim().split(" ");
            for (l in letra) {
                val letraDescifrada: Char? = decodificarLetra(l.trim());
                if (letraDescifrada == null) {
                    return "";
                }
                fraseDescifrada += letraDescifrada;
            }
            fraseDescifrada += " ";
        }
        return fraseDescifrada.trim();
    }

    /**
     * Representacion en String del alfabeto en morse.
     * Pre: true.
     * Post: Representacion en String del alfabeto en morse.
     */
    override fun toString(): String {
        return alphabet.toString();
    }
}