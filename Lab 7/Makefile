KC=	kotlinc
KFLAG=	-cp

all:	\
	Nodo.class ListaCircular.class \
	Cola.class Pila.class \
	MainKt.class

Nodo.class ListaCircular.class: Nodo.kt ListaCircular.kt
	$(KC) Nodo.kt ListaCircular.kt

Cola.class Pila.class: Cola.kt Pila.kt
	$(KC) $(KFLAG) . Cola.kt Pila.kt

MainKt.class: Main.kt
	$(KC) $(KFLAG) . Main.kt

clean:
	rm -rf  $(PROG)
