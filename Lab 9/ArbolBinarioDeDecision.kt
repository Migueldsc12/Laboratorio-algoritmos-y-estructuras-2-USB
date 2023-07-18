
//Autor: Miguel Salomon - 1910274

/**
 * Representacion de un arbol de decision binario para la decodificacion de codigo morse.
 */
public class ArbolBinarioDeDecision() {
    var raiz = Nodo(' ', "");
    
    /**
     * Se crea un arbol de decision binario con todas las letras del alfabeto en morse siguiendo los caminos 
     * en el orden visto en la figura 3.
     */
    init {
        agregar(Nodo('e', "."));
        agregar(Nodo('t', "-"));
        agregar(Nodo('i', ".."));
        agregar(Nodo('a', ".-"));
        agregar(Nodo('n', "-."));
        agregar(Nodo('m', "--"));
        agregar(Nodo('s', "..."));
        agregar(Nodo('u', "..-"));
        agregar(Nodo('r', ".-."));
        agregar(Nodo('w', ".--"));
        agregar(Nodo('d', "-.."));
        agregar(Nodo('k', "-.-"));
        agregar(Nodo('g', "--."));
        agregar(Nodo('o', "---"));
        agregar(Nodo('h', "...."));
        agregar(Nodo('v', "...-"));
        agregar(Nodo('f', "..-."));
        agregar(Nodo('l', ".-.."));
        agregar(Nodo('p', ".--."));
        agregar(Nodo('j', ".---"));
        agregar(Nodo('b', "-..."));
        agregar(Nodo('x', "-..-"));
        agregar(Nodo('c', "-.-."));
        agregar(Nodo('y', "-.--"));
        agregar(Nodo('z', "--.."));
        agregar(Nodo('q', "--.-"));
    }

    /**
     * Busca un nodo en el arbol de decision binario.
     * Pre: El codigo morse del nodo que se desea buscar.
     * Post: El valor del nodo que se desea buscar.
     */
    fun buscar(secuencia: String): Char? {
        var x: Nodo? = this.raiz;
        var y: Nodo?;
        var i = 0;

        while (x != null && i < secuencia.length) {
            if (secuencia[i] == '.'){
                x = x.izquierdo;
            }else{ 
                x = x.derecho;
            }
            i++;
        }
        y = x;

        if (y == this.raiz || y == null || secuencia.length != y.secuencia.length) {
            return null;
        } else {
            return y.valor;
        }
    }
   
    /**
     * Agrega un nodo al arbol de decision binario.
     * Pre: El nodo que se desea agregar no existe en el arbol de decision binario.
     * Post: El nodo se agrega al arbol de decision binario.
     */
    fun agregar(n: Nodo) {
        var x: Nodo? = raiz;
        var y: Nodo? = null;
        var i = 0;
        val secuencian = n.secuencia;
        while (x != null && i < secuencian.length) {
            y = x;
            if (secuencian[i] == '.') {
                x = x.izquierdo;
            }
            else {
                x = x.derecho;
            }
            i++;
        }
        if (y == null) {
            raiz = n;
        }
        else {
            n.padre = y;
            if (secuencian[i - 1] == '.') {
                y.izquierdo = n;
            }
            else {
                y.derecho = n;
            }
        }
    }

    /**
     * Representacion en String del arbol de decision binario.
     * Pre: El arbol de decision binario no es nulo.
     * Post: Se retorna una representacion en String del arbol de decision binario.
     */
    override fun toString(): String {
        var string = "ArbolDeDecision(raiz";
        if (raiz.izquierdo != null) {
            string += ", ${raiz.izquierdo}";
        }
        if (raiz.derecho != null) {
            string += ", ${raiz.derecho}";
        }
        return string + ")";
    }
}

    /**
    * Representacion de un nodo del arbol de decision binario para la decodificacion de codigo morse.
    */
    public class Nodo(val valor: Char, val secuencia: String) {
        var padre: Nodo? = null;
        var izquierdo: Nodo? = null;
        var derecho: Nodo? = null;
    }