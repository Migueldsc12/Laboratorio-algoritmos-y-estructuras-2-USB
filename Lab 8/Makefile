KC=	kotlinc
KFLAG=	-cp

all:	\
	Diccionario.class \
	HashTableEntry.class \
	CircularList.class \
	HashTableChaining.class \
	CuckooHashTableEntry.class \
	CuckooHashTable.class \
	MainKt.class

HashTableEntry.class: HashTableEntry.kt
	$(KC) HashTableEntry.kt

CuckooHashTableEntry.class: CuckooHashTableEntry.kt
	$(KC) CuckooHashTableEntry.kt

CircularList.class: CircularList.kt
	$(KC) $(KFLAG) . CircularList.kt

Diccionario.class: Diccionario.kt
	$(KC) Diccionario.kt

HashTableChaining.class: HashTableChaining.kt
	$(KC) $(KFLAG) . HashTableChaining.kt

CuckooHashTable.class: CuckooHashTable.kt
	$(KC) $(KFLAG) . CuckooHashTable.kt

MainKt.class: Main.kt
	$(KC) $(KFLAG) . Main.kt

clean:
	rm -rf *.class META-INF
