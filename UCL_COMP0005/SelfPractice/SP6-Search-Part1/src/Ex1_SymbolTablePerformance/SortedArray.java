package Ex1_SymbolTablePerformance;

public class SortedArray<K extends Comparable<K>, V> implements SymbolTable<K,V> {

	private final double resizeFactor = 2.0;
	private Pair<K, V>[] pairs;
	private int capacity;
	private int size;

	private static class Pair<K, V>{
		K key;
		V value;

		Pair(K key, V value){
			this.key = key;
			this.value = value;
		}
	}

	public SortedArray(int capacity){
		this.capacity = capacity;
		pairs = new Pair[capacity];
		this.size = 0;
	}

	private boolean isFull(){
		return size == capacity;
	}

	private void resize(){
		capacity *= resizeFactor;
		Pair<K,V>[] newPairs = new Pair[capacity];
		for(int i = 0; i < size; i++)
			newPairs[i] = pairs[i];

		pairs = newPairs;
	}

	private int binarySearch(K key){
		int left = 0;
		int right = size - 1;
		while (left <= right){
			int mid = (left + right) / 2;

			if (key.compareTo(pairs[mid].key) < 0)
				right = mid - 1;
			else if (key.compareTo(pairs[mid].key) > 0)
				left = mid + 1;
			else{
				return mid;
			}
		}

		return left;
	}

	@Override
	public void put(K key, V value) {
		if (isFull())
			resize();

		int index = binarySearch(key);
		if (index < size && pairs[index].key.equals(key)){
			pairs[index].value = value;
			return;
		}


		for(int i = size - 1; i >= index; i--)
			pairs[i + 1] = pairs[i];
		pairs[index] = new Pair<>(key, value);
		size++;
	}

	@Override
	public V get(K key) {
		int index = binarySearch(key);
		if (index < size && pairs[index].key.equals(key))
			return pairs[index].value;
		return null;
	}
}
