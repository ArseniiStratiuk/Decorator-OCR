# Smart Document OCR with Decorators

## Project Goal
This project implements the **Decorator Pattern** to enhance an OCR (Optical Character Recognition) document parser.
- **Base Component**: `SmartDocument` uses Google Cloud Vision API to extract text from images.
- **Decorators**:
  - `CachedDocument`: Caches results in a local SQLite database (`data.db`) to optimize performance and reduce API costs.
  - `TimedDocument`: Measures and logs the execution time of the parsing operation.

## Usage
1. **Setup Google Cloud**:
   - Enable Vision API in your Google Cloud Console.
   - Set the `GOOGLE_APPLICATION_CREDENTIALS` environment variable to point to your JSON service account key.

2. **Run the Application**:
   - Open `src/main/java/apps/lab10/Main.java`.
   - Update the `filePath` variable to point to a real image on your computer.
   - Run the `main` method.

3. **Expected Behavior**:
   - **First Run**: The application calls the Google Cloud API (slower) and saves the result to the database.
   - **Second Run**: The application retrieves the text from the local SQLite cache (significantly faster).
