KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	PruebaOrtografia.jar

SRC=	AyudanteOrtografico.kt ConjuntoPalabras.kt PMLI.kt PruebaOrtografia.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
