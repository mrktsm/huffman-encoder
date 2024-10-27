import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a node in a Huffman tree.
 * Each node contains a symbol, its frequency, references to left and right child nodes,
 * and a list of characters corresponding to the leaves.
 */
public class HNode {
    /** The right child node. */
    public HNode right;
    
    /** The left child node. */
    public HNode left;

    /** A list of characters associated with the leaves of the tree. */
    public Set<Character> leaves;

    /** The frequency of the symbol represented by this node. */
    public int frequency;

    /**
     * Constructs a leaf node with a specified symbol and frequency.
     *
     * @param c the character symbol of this node
     * @param f the frequency of this node
     */
    public HNode(char c, int f) {
    	leaves = new HashSet<>();
        leaves.add(c);
        frequency = f;
    }

    /**
     * Constructs an internal node combining two child nodes.
     *
     * @param left the left child node
     * @param right the right child node
     */
    public HNode(HNode left, HNode right) {
    	leaves = new HashSet<>();
    	
        leaves.addAll(left.leaves);
        leaves.addAll(right.leaves);
        
        this.left = left;
        this.right = right;
        
        frequency = left.frequency + right.frequency;
    }

    /**
     * Checks if this node is a leaf node (i.e., it does not have any children).
     *
     * @return true if this node is a leaf; false otherwise
     */
    public boolean isLeaf() {
        return right == null && left == null;
    }

    /**
     * Checks if this node contains a specific character.
     *
     * @param ch the character to check
     * @return true if this node contains the character; false otherwise
     */
    public boolean contains(char ch) {
    	// Since we are using a Hash Set this should take O(1) time
    	return leaves.contains(ch);
    }

    /**
     * Gets the symbol represented by this node.
     *
     * @return the character symbol of this node
     */
    public char getSymbol() {
    	if (isLeaf()) {
    		// Retrieves the set's iterator and returns the first character
    		return leaves.iterator().next();
    	}
        return '\0';
    }

    /**
     * Returns a string representation of this node.
     *
     * @return a string representation of this node
     */
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	
    	for (Character leafChar : leaves) str.append(leafChar);
    	
        return str.append(":").append(frequency).toString();
    }
}
