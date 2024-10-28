# Huffman Encoder

A simple file compression tool implementing Huffman encoding for efficient file size reduction. This project provides an effective way to compress files, making them smaller while maintaining the integrity of the data.

## Features

- **Huffman Encoding**: Implements a well-known algorithm for lossless data compression.
- **Java Documentation**: Extensive JavaDocs are provided to make the code understandable for everyone.
- **JUnit Testing**: Comprehensive unit tests ensure the functionality and reliability of the code.

## How Huffman Encoding Works

Huffman encoding is a method of lossless data compression. It assigns variable-length codes to input characters, with shorter codes assigned to more frequent characters. This minimizes the total number of bits needed to represent the data, leading to efficient compression.

### Example Process:
1. Count the frequency of each character in the input file.
2. Build a binary tree using these frequencies.
3. Generate binary codes for each character based on the tree structure.
4. Write the compressed data to a new file.

For a detailed walkthrough of the process, check out the [YouTube video](https://www.youtube.com/watch?v=V5D8P-DlwOA&ab_channel=MarkoTsymbaliuk) where I demonstrate how to use the Huffman Encoder.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mrktsm/huffman-encoder.git
2. Navigate into the directory:
   cd HuffmanCoding
3. Make sure huffmanzip.jar file is in the resources directory

### Usage 

To use the Huffman Encoder, run the following commands:
-**To Encode a File:**
   ```bash
   java -jar resources/huffmanzip.jar -encode <filename>
   ```

-**To Decode a File:**
   ```bash
   java -jar resources/huffmanzip.jar -decode <filename>
   ```
Replace <filename> with the name of the file you want to compress or decompress.

### Running Tests

To run the unit tests, you can use JUnit. Ensure you have JUnit set up and run the test cases provided in the repository.

### Acknowledgments

This project was developed for CS216 at Gettysburg College. Special thanks to my professor and peers for their support and guidance throughout the process.

### License
This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
