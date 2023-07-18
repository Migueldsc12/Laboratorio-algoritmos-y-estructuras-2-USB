KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	PruebaMergesort.jar

SRC=	Sortlib.kt PruebaMergesort.kt

PROGMATRIX=	MultiplicacionMatrices.jar

SRCMATRIX=	MultiplicacionMatrices.kt PruebaMatrices.kt

all:
	$(KC) $(SRCMATRIX) $(KFLAGS) -d $(PROGMATRIX)
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(PROGMATRIX)
