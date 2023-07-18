KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	AsignarCupo.jar

SRC=	AsignarCupo.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
