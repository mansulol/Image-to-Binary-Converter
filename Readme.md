# Image to Binary Converter

This is a **Java application** that converts an image into a binary file, storing the image as a sequence of characters. The same program can then read the binary file to reconstruct the original image. This project was built to explore and learn about Java I/O streams.

---

## Features 📋

- **Image to Binary Conversion**: Convert any image into a binary text file, where each pixel's data is represented as binary code.
- **Binary to Image Conversion**: Read the binary file and recreate the original image from the stored binary data.
- **File Handling with Java Streams**: Uses Java Input/Output streams to handle reading and writing binary data to and from files.

---

## Technologies Used ⚙️

- **Java**: The entire application is written in Java.
- **I/O Streams**: Java's `FileInputStream`, `FileOutputStream`, and other related classes are used to handle the conversion of images to binary and back.
- **ImageIO**: Used for reading and writing image files in formats such as `.png`, `.jpg`, etc.

---

## Installation and Execution 🔧

1. Clone the repository or download the ZIP file:
    ```bash
    git clone https://github.com/yourusername/image-to-binary-converter.git
    ```
2. Navigate to the project directory:
    ```bash
    cd image-to-binary-converter
    ```
3. Compile the project:
    ```bash
    javac src/com/imageconverter/*.java
    ```
4. Run the application:
    ```bash
    java src/com/imageconverter/Main
    ```

---

## Project Structure 🔩

```
image-to-binary-converter/
├── src/
│     └── com/
│           └── imageconverter/
│                 ├── Main.java           # Main class to launch the app
│                 ├── ImageConverter.java # Class that handles the conversion logic
│                 ├── BinaryWriter.java   # Class that writes binary data to a file
│                 ├── BinaryReader.java   # Class that reads binary data from a file
│                 └── sample-image.png    # Example image for testing
└── README.md                             # README file with project details
```

---

## Usage 🛠️

### 1. Convert Image to Binary

- Place the image you want to convert in the project directory (or specify the file path in the program).
- The program will read the image, convert each pixel into binary data, and store the binary representation in a `.txt` file.

### 2. Convert Binary to Image

- Load the binary `.txt` file produced by the previous step into the program.
- The program will read the binary data, decode it, and recreate the original image.

---

## Learning Objectives 🎓

- **File I/O in Java**: The project uses Java's Input/Output streams to read and write binary data to and from files, making it a great way to learn how streams work in Java.
- **Image Processing**: By converting images to binary and back, this project offers a practical application of how binary data can represent visual content.
  
---

## Known Issues 🐞

- **Image Size**: The program is optimized for small or medium-sized images. Very large images may lead to performance issues.
- **Exact Reconstruction**: Depending on the image format, some quality loss might occur during conversion and reconstruction.

---

## Future Improvements 🚀

- **Support for Multiple Image Formats**: Add support for more image formats beyond `.png` and `.jpg`.
- **Compression**: Implement binary data compression to reduce file sizes.
- **Error Handling**: Improve error handling when dealing with file input/output exceptions.

---

## Special Thanks 🎁

Thanks to the Java community and resources that helped with understanding the intricate workings of Java I/O streams.

---

Made by [Mansour Lo Lo] - mansourlol440@gmail.com