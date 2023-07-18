KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	CodigoMorse.jar

SRC=	ArbolBinarioDeDecision.kt CodigoMorse.kt PruebaCodigoMorse.kt

TEMP=	*.class META-INF

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(TEMP)
