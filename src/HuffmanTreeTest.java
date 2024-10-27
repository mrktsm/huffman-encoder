import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {
	@Test
    public void test_HuffmanTree() throws IOException {
        TreeMap<Character, Integer> frequencies = createFrequencies();
        HuffmanTree tree = new HuffmanTree(frequencies);
    }

    private TreeMap<Character, Integer> createFrequencies() {
        TreeMap<Character, Integer> frequencies = new TreeMap<>();
        frequencies.put('a', 5);
        frequencies.put('b', 9);
        frequencies.put('c', 12);
        frequencies.put('d', 13);
        frequencies.put('e', 16);
        return frequencies;
    }
    
    @Test
    public void testEncoding() {
        HuffmanTree tree = new HuffmanTree(createFrequencies());
        assertEquals("100", tree.encode('a'));
        assertEquals("101", tree.encode('b'));
        assertEquals("00", tree.encode('c'));
        assertEquals("01", tree.encode('d'));
        assertEquals("11", tree.encode('e'));
    }
    
    @Test
    public void testDecoding() {
        HuffmanTree tree = new HuffmanTree(createFrequencies());
        assertEquals('a', tree.decode("100"));
        assertEquals('b', tree.decode("101"));
        assertEquals('c', tree.decode("00"));
        assertEquals('d', tree.decode("01"));
        assertEquals('e', tree.decode("11"));

        // Test for incorrect values
        assertEquals('\0', tree.decode("0000"));
    }
    
    @Test
    public void testEncodeLoop() {
        HuffmanTree tree = new HuffmanTree(createFrequencies());
        assertEquals("100", tree.encodeLoop('a'));
        assertEquals("101", tree.encodeLoop('b'));
        assertEquals("00", tree.encodeLoop('c'));
        assertEquals("01", tree.encodeLoop('d'));
        assertEquals("11", tree.encodeLoop('e'));
    }
    
    // Test the writeCode method
    @Test
    public void test_readAndWriteCode() throws IOException {
        HuffmanTree tree = new HuffmanTree(createFrequencies());
        BitOutputStream outputStream = new BitOutputStream("resources/test.txt.hz");

        // Write codes to stream
        tree.writeCode('a', outputStream);
        tree.writeCode('b', outputStream);
        tree.writeCode('c', outputStream);
        outputStream.close();

        // Verify bits by reading them back using BitInputStream
        BitInputStream inputStream = new BitInputStream("resources/test.txt.hz");
        assertEquals('a', tree.readCode(inputStream));
        assertEquals('b', tree.readCode(inputStream));
        assertEquals('c', tree.readCode(inputStream));

        inputStream.close();
    }
    
    @Test
    public void testCreateLeafNode() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);

        assertEquals(leaf1.toString(), "a:5");
        assertEquals(leaf2.toString(), "b:3");
    }

    @Test
    public void testIsLeaf() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);

        assertTrue(leaf1.isLeaf());
        assertTrue(leaf2.isLeaf());
    }
    
    @Test
    public void testContains() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);

        assertTrue(leaf1.contains('a'));
        assertTrue(leaf2.contains('b'));
        assertFalse(leaf1.contains('b'));
    }
    
    @Test
    public void testGetSymbol() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);

        assertEquals('a', leaf1.getSymbol());
        assertEquals('b', leaf2.getSymbol());
    }
    
    @Test
    public void testToString() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);
        
        assertEquals(leaf1.toString(), "a:5");
        assertEquals(leaf2.toString(), "b:3");
    }
    
    @Test
    public void testMergeNodes() {
        HNode leaf1 = new HNode('a', 5);
        HNode leaf2 = new HNode('b', 3);

        // Merge the leaf nodes into a parent node
        HNode parent = new HNode(leaf1, leaf2);
        assertEquals(parent.toString(), "ab:8");

        assertFalse(parent.isLeaf());
        assertTrue(parent.contains('a'));
        assertTrue(parent.contains('b'));
        assertFalse(parent.contains('c'));
        assertEquals('\0', parent.getSymbol());
    }
}
