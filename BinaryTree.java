// Class for the Binary Tree, the keys must be comparable and the tree
// must implement the simple map as per the instructions
public class BinaryTree<K extends Comparable<K>, V> implements SimpleMap<K, V> {
    
    // Declare the root node
    private TreeNode<K, V> root;
    
    //Constructor sets the root node to mull
    public BinaryTree(){
        root = null;
    }
    
    // Overrided clear function from SimpleMap erases reference to the tree
    // by assigning root to null
    @Override
    public void clear(){
        root = null;
    }
    
    // Overrided put function from SimpleMap creates a new node in the tree
    @Override
    public V put(K key, V value){
        
        // Declare a temporay node and instantiate it with the key and
        // previous value of that key
        TreeNode<K, V> tempNode = new TreeNode<>(key, get(key));
        
        // Call overloaded put function to either place a new node
        // in the tree or update the exisiting value for the key
        root = put(root, key, value);
        
        // Return the previous value of the key through the temporary node
        return tempNode.getValue();
    }
    
    // Overloaded put function creates or updates the nodes in the tree
    private TreeNode<K, V> put(TreeNode<K, V> node, K key, V value){
        
        // If the node is null, create a new node with the information
        // passed into the function
        if(node == null){
            return new TreeNode<>(key, value);
        }
        
        // Compare the keys recursively to find the correct spot to insert
        // the new node or if the key already exists
        if(node.getKey().compareTo(key) > 0){
            // If the current node has a value greater than our key, go left
            node.left = put(node.left, key, value);
        }
        else if(node.getKey().compareTo(key) < 0){
            // If the current node has a value less than our key, go right
            node.right = put(node.right, key, value);
        }
        else{
            // If the keys are equal, only update the value
            node.setValue(value);
        }
        
        // Return the updated or newly inserted node
        return node;
    }
    
    // Overrided get function from SimpleMap returns a value associated with 
    // a key
    @Override
    public V get(K key){
        // Return the value given by overloaded get function
        return get(root, key);
    }
    
    private V get(TreeNode<K, V> node, K key){
        
        // If the node is null it has no value, return null
        if(node == null){
            return null;
        }
        
        // Compare the key passed into the function with the keys in the tree
        // Recursive function calls determine which direction is taken
        if (node.getKey().compareTo(key) > 0){
            // If the current node has a value greater than our key, go left
            return get(node.left, key);
        }
        else if (node.getKey().compareTo(key) < 0){
            // If the current node has a value less than our key, go right
            return get(node.right, key);
        }
        else{
            // If the keys are equal, a match is found return the value
            return node.getValue();
        }
    }
    
    // Overrided isEmpty function from SimpleMap tests if the tree is empty
    @Override
    public boolean isEmpty(){
        // Set boolean condition to false
        boolean isEmpty = false;
        // If the root is null, the tree is empty
        if(root == null){
            //Set condition to true
            isEmpty = true;
        }
        // Return the condition
        return isEmpty;
    }
}
