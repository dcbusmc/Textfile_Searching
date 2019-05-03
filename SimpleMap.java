public interface SimpleMap<K, V> {

    /**
     * Removes all of the mappings from this map
     */
    public void clear();
    
    /**
     * Associates the value specified with the key specified
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    public V put(K key, V value);
    
    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key);
    
    /**
     * Returns true if this map contains no key-value mappings
     */
    public boolean isEmpty();
}
