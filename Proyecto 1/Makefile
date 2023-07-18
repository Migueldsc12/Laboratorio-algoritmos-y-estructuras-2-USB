KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	DCLSTSP.jar

SRC=	DCLSTSP.kt

PROGVERIFICADOR=	VerificadorTSP.jar

SRCVERIFICADOR=	VerificadorTSP.kt

all:
	$(KC) $(SRCVERIFICADOR) $(KFLAGS) -d $(PROGVERIFICADOR)
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(PROGVERIFICADOR)
