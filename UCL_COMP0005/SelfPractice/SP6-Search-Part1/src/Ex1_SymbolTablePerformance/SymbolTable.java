package Ex1_SymbolTablePerformance;

public interface SymbolTable<K extends Comparable<K>, V> {
	void put(K key, V value);
	V get(K key);
}
