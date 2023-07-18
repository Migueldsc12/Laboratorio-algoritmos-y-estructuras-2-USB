KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	SeleccionarCliente.jar

SRC=	SeleccionarCliente.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
