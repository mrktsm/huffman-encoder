import java.util.Comparator;

/**
 * Comparator implementation for comparing two HNode objects based on their frequency.
 */
public class HNodeComparator implements Comparator<HNode> {
    
    /**
     * Compares two HNode objects for order based on their frequency.
     *
     * @param node1 the first HNode to be compared
     * @param node2 the second HNode to be compared
     * @return a negative integer, zero, or a positive integer as the first 
     *         argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(HNode node1, HNode node2) {
    	return Integer.compare(node1.frequency, node2.frequency);
    }
}
