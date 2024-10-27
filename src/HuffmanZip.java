import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Provides methods for encoding and decoding files using Huffman coding.
 */
public class HuffmanZip {
    /**
     * Encodes the contents of the specified file using Huffman coding and writes the encoded data to a new file.
     *
     * @param filename the name of the file to be encoded
     */
    public static void encode(String filename) throws IOException {
    	TreeMap<Character, Integer> frequencies = new TreeMap<>();
        FileReader fileReader = new FileReader(filename);
        
        // Create a frequency map
        int charCode;
        while ((charCode = fileReader.read()) != -1) {
        	char currChar = (char) charCode;
        	frequencies.put(currChar, frequencies.getOrDefault(currChar, 0)+1);
        }
        // Create a Huffman tree based on the found frequency map
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        
        // Create a new file and write the frequency map to it
        BitOutputStream outputStream = new BitOutputStream(filename + ".hz");
        outputStream.writeObject(frequencies);
        
        fileReader.close();
        
        fileReader = new FileReader(filename);
        while ((charCode = fileReader.read()) != -1) {
        	char currChar = (char) charCode;
        	huffmanTree.writeCode(currChar, outputStream);
        }
        
        fileReader.close();
        outputStream.close();
    }

    /**
     * Decodes the contents of the specified encoded file back to its original form.
     *
     * @param filename the name of the file to be decoded
     */
    public static void decode(String filename) throws IOException, ClassNotFoundException { 
    	String outputFilename = filename.substring(0, filename.lastIndexOf(".hz"));
    	BitInputStream inputStream = new BitInputStream(filename);    	// Get the .hz file
    	
    	TreeMap<Character, Integer> frequencies = (TreeMap<Character, Integer>) inputStream.readObject();
    	HuffmanTree huffmanTree = new HuffmanTree(frequencies);
    	
    	FileWriter fileWriter = new FileWriter(outputFilename);
    	while (inputStream.hasNext()) {
    		fileWriter.write(huffmanTree.readCode(inputStream));
    	}
    	
    	fileWriter.close();
    	inputStream.close();
    }

    /**
     * The main entry point of the HuffmanZip program.
     * This method can be used to initiate encoding or decoding based on command-line arguments.
     *
     * @param args command-line arguments that can specify the operation (encode/decode) and file names
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	if (args[0].equals("-encode")) {
    		encode(args[1]);
    	} else if (args[0].equals("-decode")) {
    		decode(args[1]);
    	} else {
    		System.err.println("Choose a valid option");
    	}
    }
}
