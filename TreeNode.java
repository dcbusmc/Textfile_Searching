// Class for the nodes in the binary tree, the keys must be comparable
public class TreeNode<K extends Comparable<K>, V> {
    
    // Declare objects of type K and V for the keys and values
    // Key is declared final because it should not be changed once
    // set fot the node
    private final K key;
    private V value;
    
    // Declare two additional nodes for the left and right
    public TreeNode<K, V> left;
    public TreeNode<K, V> right;
    
    // Constructor takes in a key and a value and assigns it to the private
    // objects, also assigns the left and right nodes to null
    public TreeNode(K key, V value){
        this.key = key;
        this.value = value;
        
        left = null;
        right = null;
    }
    
    // Getter for the key
    public K getKey() {
        return this.key;
    }
    
    // Getter fot the value
    public V getValue() {
        return this.value;
    }
    
    // Setter for the value
    public void setValue(V value) {
        this.value = value;
    }
}
