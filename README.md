# Smart Document OCR with Decorators

![Java CI](https://github.com/ArseniiStratiuk/Decorator-OCR/actions/workflows/maven.yml/badge.svg)

## Project Goal
This project implements the **Decorator Pattern** to enhance an OCR (Optical Character Recognition) document parser.
- **Base Component**: `SmartDocument` uses **Tesseract OCR** to extract text from images locally.
- **Decorators**:
  - `CachedDocument`: Caches results in a local SQLite database (`data.db`) to optimize performance.
  - `TimedDocument`: Measures and logs the execution time of the parsing operation.

## Prerequisites
1. **Install Tesseract OCR**:
   - **Windows**: Download and install from [UB-Mannheim/tesseract](https://github.com/UB-Mannheim/tesseract/wiki). 
     - **Important**: Note the installation path (e.g., `D:\tesseract-ocr` or `C:\Program Files\Tesseract-OCR`).
   - **macOS**: `brew install tesseract`
   - **Linux**: `sudo apt-get install tesseract-ocr`

2. **Java Development Kit (JDK)**:
   - Ensure JDK 17 or higher is installed.

## Setup & Usage

### 1. Configure Environment
The application needs to know where Tesseract is installed. You can set this like this:

**System Environment Variable**
- Set a system environment variable `TESSDATA_PREFIX` pointing to your Tesseract installation directory.
- Restart VS Code or your terminal to pick up the change.

### 2. Run the Application
1. Open `src/main/java/apps/lab10/Main.java`.
2. Run the `main` method.
3. **First Run**: The application calls Tesseract (slower) and saves the result to the database.
4. **Second Run**: The application retrieves the text from the local SQLite cache (significantly faster).

## Testing
This project includes basic unit tests for the Decorator logic.
To run tests via Maven:
```bash
mvn test
```

## CI/CD
A GitHub Actions workflow is included in `.github/workflows/maven.yml` to automatically build and test the project on every push to the `main` branch.

