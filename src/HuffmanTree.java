import java.io.IOException;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Represents a Huffman Tree used for encoding and decoding characters based on their frequencies.
 */
public class HuffmanTree {
    public HNode root;

    /**
     * Constructs a Huffman Tree from the given frequency map.
     *
     * @param frequencies a TreeMap containing characters and their corresponding frequencies
     */
    public HuffmanTree(TreeMap<Character, Integer> frequencies) {
    	// Create priority queue
        PriorityQueue<HNode> pq = new PriorityQueue<>(new HNodeComparator());
        
        for (Character key : frequencies.keySet()) {
        	pq.add(new HNode(key, frequencies.get(key)));
        }
        
        while (pq.size() > 1) {
        	HNode nodeLeft = pq.poll();
        	HNode nodeRight = pq.poll();
        	
        	HNode parentNode = new HNode(nodeLeft, nodeRight);
        	pq.add(parentNode);
        }
        
        root = pq.poll();
    }

    /**
     * Encodes a single character into its corresponding Huffman code.
     *
     * @param symbol the character to encode
     * @return the Huffman code as a string
     */
    public String encodeLoop(char symbol) {
    	StringBuilder encoding = new StringBuilder();
    	
    	HNode curr = root;
    	while (!curr.isLeaf()) {
    		if (curr.left.contains(symbol)) {
    			curr = curr.left;
    			encoding.append('0');
    		} else {
    			curr = curr.right;
    			encoding.append('1');
    		}
    	}
    	
        return encoding.toString();
    }

    /**
     * Encodes a single character into its corresponding Huffman code using a recursive approach.
     *
     * @param symbol the character to encode
     * @return the Huffman code as a string
     */
    public String encode(char symbol) {
        return encode(symbol, root);
    }

    /**
     * Encodes a character recursively by traversing the Huffman Tree.
     *
     * @param symbol the character to encode
     * @param curr the current HNode in the tree
     * @return the Huffman code as a string
     */
    public String encode(char symbol, HNode curr) {
        if (curr.isLeaf()) return "";
        
        StringBuilder res = new StringBuilder();
        if (curr.left.contains(symbol)) {
        	res.append("0").append(encode(symbol, curr.left));
        } else {
        	res.append("1").append(encode(symbol, curr.right));
        }
        
        return res.toString();
    }

    /**
     * Decodes a given Huffman code back into its corresponding character.
     *
     * @param code the Huffman code to decode
     * @return the decoded character
     */
    public char decode(String code) {
    	HNode curr = root;
    	
    	int i = 0;
    	while (!curr.isLeaf()) {
    		char currChar = code.charAt(i);
    		
    		// if 0 go left else go right
    		curr = (currChar == '0') ? curr.left : curr.right;
    		++i;
    	}
    	
    	if (i < code.length()) return '\0';
    	
    	
    	
        return curr.getSymbol();
    }

    /**
     * Writes the Huffman code for a given character to the specified output stream.
     *
     * @param symbol the character whose code is to be written
     * @param stream the BitOutputStream to write to
     * @throws IOException if an I/O error occurs
     */
    public void writeCode(char symbol, BitOutputStream stream) throws IOException {
    	HNode curr = root;
    	while (!curr.isLeaf()) {
    		if (curr.left.contains(symbol)) {
    			curr = curr.left;
    			stream.writeBit(0);
    		} else {
    			curr = curr.right;
    			stream.writeBit(1);
    		}
    	}
    }

    /**
     * Reads a Huffman code from the specified input stream and returns the corresponding character.
     *
     * @param stream the BitInputStream to read from
     * @return the character represented by the read Huffman code
     * @throws IOException if an I/O error occurs
     */
    public char readCode(BitInputStream stream) throws IOException {
    	HNode curr = root;
    	
    	while (!curr.isLeaf()) curr = (stream.readBit() == 0) 
    			                    ? curr.left
    			                    : curr.right;
    	
    	return curr.getSymbol();
    }
}
