KC= kotlinc
KFLAGS= -include-runtime
SRC= DCLSTSP.kt
PROG= DCLSTSP.jar

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY: clean

clean:
	rm -rf $(PROG)
