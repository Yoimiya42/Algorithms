package StringSearch;

import java.util.Set;

public interface SymbolTable<K, V> {
	void put(K key, V value);
	V get(K key);

	Set<K> keySet();
	Set<K> keysWithPrefix(String prefix);
	String longestPrefixOf(K query);
}
