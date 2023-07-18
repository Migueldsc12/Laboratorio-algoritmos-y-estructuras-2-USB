// Autor: Miguel Salomon - 1910274

import java.io.File
import java.io.Reader
import java.io.InputStream
import java.io.BufferedReader

//Representacion del TAD AyudanteOrtografico

class AyudanteOrtografico() {

    private val MAX = 27; 
    private val dicc: Array<PMLI> = arrayOf(
        PMLI('a'), 
        PMLI('b'), 
        PMLI('c'), 
        PMLI('d'), 
        PMLI('e'), 
        PMLI('f'), 
        PMLI('g'), 
        PMLI('h'), 
        PMLI('i'), 
        PMLI('j'), 
        PMLI('k'), 
        PMLI('l'), 
        PMLI('m'), 
        PMLI('n'), 
        PMLI('ñ'), 
        PMLI('o'), 
        PMLI('p'), 
        PMLI('q'), 
        PMLI('r'), 
        PMLI('s'), 
        PMLI('t'), 
        PMLI('u'), 
        PMLI('v'), 
        PMLI('w'), 
        PMLI('x'), 
        PMLI('y'), 
        PMLI('z')
    );

    /**
    * Método que carga el diccionario.
    * Pre: El archivo fname debe existir y tener el formato correcto, que es
    * una palabra por línea, con todas las letras en minúscula.
    * Post: se carga el diccionario en la estructura.
    */
    fun cargarDiccionario(fname: String) {
        val archivo = File(fname);

        if (archivo.isDirectory) {
            println("El archivo $fname es un directorio. No se pudo cargar el diccionario.");
            return;
        }

        if (!archivo.exists()) {
            println("El archivo $fname no existe. No se pudo cargar el diccionario.");
            return;
        }

        var bufferedReader = archivo.bufferedReader();
        bufferedReader.useLines { lines ->
            lines.forEach {
                if (it.split(" ").size > 1) {
                    println("No se pudo cargar el diccionario.");
                    println("Formato incorrecto.");
                    println("Debe haber una sola palabra por línea.\n");
                    return;
                }

                val palabra = it.trim();

                if (!palabraValida(palabra)) {
                    println("No se pudo cargar el diccionario.");
                    println("Formato incorrecto.");
                    println("$palabra es una palabra invalida.\n");
                    return;
                }
            }
        }
        
        bufferedReader = archivo.bufferedReader();
        bufferedReader.useLines { lines ->
            lines.forEach {
                val palabra = it.trim();
                val primeraLetra = palabra[0];
                val ind = dicc.indexOfFirst { it.letra == primeraLetra };
                dicc[ind].agregarPalabra(palabra);
            }
        }
        println("\n Diccionario cargado \n");
    }

    /**
    * Método que elimina la palabra
    * Pre: la palabra debe pertenecer a la estructura y ser válida.
    * Post: se elimina la palabra de la estructura.
    */
    fun borrarPalabra(palabra: String) {
        if (!palabraValida(palabra)) {
            println("La palabra debe tener solo letras minúsculas.");
            return;
        }

        val primeraLetra = palabra[0];
        val ind = dicc.indexOfFirst { it.letra == primeraLetra };

        if (dicc[ind].buscarPalabra(palabra)) {
            dicc[ind].eliminarPalabra(palabra);
            println("Palabra eliminada del diccionario");
        }

        else println("$palabra no se encuentra.");
        
    }

    /**
    * Método que busca la palabra en el diccionario.
    * Pre: una palabra valida.
    * Post: se retorna true si la palabra se encuentra en el diccionario || false de lo contrario.
    */
    fun buscarPalabra(palabra: String): Boolean {
        if (!palabraValida(palabra)) {
            println("La palabra debe tener solo letras minúsculas.\n");
            return false;
        }

        val primeraLetra = palabra[0];
        val ind = dicc.indexOfFirst { it.letra == primeraLetra };
        return dicc[ind].buscarPalabra(palabra);
    }

    /**
    * Metodo que escribe el archivo de salida con las palabras válidas que no estan en el diccionario
    * Pre: Archivo de entrada valido y existente.
    * Post: se imprime en el archivo de salida las palabras válidas que no se encuentran en el diccionario.
    */
    fun corregirTexto(finput: String, foutput: String) {
        val archivo = File(finput);
       
        if (archivo.isDirectory()) {
            println("El archivo $finput es un directorio. Por lo tanto no se puede procesar");
            return;
        }

        if (!archivo.exists()) {
            println("El archivo $finput no existe. Por lo tanto no se puede procesar");
            return;
        }

        val bufferedReader = archivo.bufferedReader();
        val inputString = bufferedReader.use { it.readText() };
        val palabrasArray = procesarTexto(inputString);
        val diccTemporal = AyudanteOrtografico();

        for (i in 0 until palabrasArray.size) {
            val palabra = palabrasArray[i].trim();
            if (buscarPalabra(palabra)) {
                palabrasArray[i] = "";
                continue;
            } else if (diccTemporal.buscarPalabra(palabra)) {
                palabrasArray[i] = "";
                continue;
            } else {
                val primeraLetra = palabra[0];
                val palabrasCercanas = buscarMenorDist(palabra);
                val ind = diccTemporal.dicc.indexOfFirst { it.letra == primeraLetra };
                diccTemporal.dicc[ind].agregarPalabra(palabra);
                palabrasArray[i] = "$palabra,${palabrasCercanas[0]},${palabrasCercanas[1]},${palabrasCercanas[2]},${palabrasCercanas[3]}";
            }
        }

        val archivoSalida = File(foutput);

        if (archivoSalida.isDirectory()) {
            println("El archivo $foutput es un directorio. Por lo tanto no se pueden imprimir las secuencias");
            return;
        }

        if (!archivoSalida.exists()) archivoSalida.createNewFile() else archivoSalida.writeText("");

        val bufferedWriter = archivoSalida.bufferedWriter();

        bufferedWriter.use { out ->
            for (palabra in palabrasArray) {
                if (palabra != "") {
                    out.write(palabra);
                    out.newLine();
                }
            }
        }
        bufferedWriter.close();
        println("\n $foutput ha sido creado exitosamente con las palabras corregidas. \n");
    }

    /**
     * Metodo que imprime el diccionario.
    * Pre: true.
    * Post: se imprime el diccionario.
    */
    fun imprimirDiccionario() {
        for (i in 0 until MAX) println(dicc[i].toString());
    }

    /**
    * Método que procesa el texto y devuelve un arreglo con las palabras válida
    * Pre: texto debe ser un String distinto de vacío.
    * Post: se devuelve un arreglo con las palabras válidas.
    */
    private fun procesarTexto(texto: String): Array<String> {
        val palabras = texto.replace(".", " ").replace(",", " ").replace("?", " ").replace("!", " ").replace(";", " ").replace(":", " ").replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("¿", " ").replace("¡", " ").replace("\n", " ").replace("\t", " ").replace("\r", " ");
        val palabrasArray = palabras.split(" ").toTypedArray();
        return palabrasArray.filter { it != "" && it != " " && palabraValida(it) }.toTypedArray();
    }

    private fun palabrasArray(): Array<String> {
        var palabrasArray = arrayOf<String>();
        for (i in 0 until dicc.size) {
            val palabras = dicc[i].palabrasArray();
            palabrasArray += palabras;
        }
        return palabrasArray;
    }

    /**
     * Método que busca las 4 palabras con menor distancia en el diccionario.
     * Pre: Arreglo con palabras válidas.
     * Post: Arreglo con las 4 palabras con menor distancia.
     */

    private fun buscarMenorDist(palabra: String): Array<String> {
        val palabras = this.palabrasArray();
        val masCorta = Array(4) { "" };
        for (i in 0 until 4) {
            var menor = Int.MAX_VALUE;
            for (j in 0 until palabras.size) {
                val distancia = distancia2Palabras(palabra, palabras[j]);
                if (distancia < menor && palabras[j] !in masCorta) {
                    menor = distancia;
                    masCorta[i] = palabras[j];
                }
            }
        }
        return masCorta;
    }

    /**
     * Metodo para buscar la distancia entre dos palabras
     * Pre: 2 palabras validas
     * Post: Distancia entre las dos palabras
     */

    fun distancia2Palabras(a: String, b: String): Int {
        val d = Array(a.length + 1) { IntArray(b.length + 1) };
        for (i in 0..a.length) d[i][0] = i;        
        for (j in 0..b.length) d[0][j] = j;        
        for (i in 1..a.length) {
            for (j in 1..b.length) {
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1;
                d[i][j] = minOf(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
                if (i > 1 && j > 1 && a[i - 1] == b[j - 2] && a[i - 2] == b[j - 1]) d[i][j] = minOf(d[i][j], d[i - 2][j - 2] + 1);
            }
        }
        return d[a.length][b.length];
    }

    /**
     * Metodo que imprime la estructura
    * Pre: true.
    * Post: se imprime la estructura.
    */
    override fun toString(): String {
        var str = "";
        for (i in 0 until MAX) {
            if (i == MAX - 1) str += dicc[i].toString() else str += dicc[i].toString() + "\n";
        }
        return str;
    }
    
}