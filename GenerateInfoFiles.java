import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase GenerateInfoFiles
 *
 * Genera archivos de prueba para el proyecto de programación.
 * Incluye:
 * - Archivo con información de vendedores.
 * - Archivo con información de productos.
 * - Archivos de ventas por vendedor.
 *
 * Los archivos se almacenan en la carpeta /data.
 */
public class GenerateInfoFiles {

    private static final String[] NAMES = {"Juan", "Maria", "Pedro", "Luisa", "Andres", "Sofia", "Carlos", "Laura"};
    private static final String[] LASTNAMES = {"Gomez", "Perez", "Rodriguez", "Martinez", "Fernandez", "Garcia", "Lopez", "Diaz"};
    private static final String[] PRODUCTNAMES = {"Laptop", "Celular", "Tablet", "Audifonos", "Teclado", "Mouse", "Monitor", "Impresora"};

    private static final Random RANDOM = new Random();
    private static final String OUTPUT_DIR = "data";

    public static void main(String[] args) {
        try {
            // Crear carpeta de salida si no existe
            File dir = new File(OUTPUT_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Generar archivos de prueba
            createSalesManInfoFile(5);
            createProductsFile(10);
            for (int i = 0; i < 5; i++) {
                createSalesMenFile(5, NAMES[i], 1000 + i);
            }
            System.out.println("✅ Archivos generados exitosamente en la carpeta /data.");
        } catch (IOException e) {
            System.out.println("❌ Error al generar los archivos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo de ventas de un vendedor con datos pseudoaleatorios.
     * @param randomSalesCount número de productos vendidos que se generarán
     * @param name nombre del vendedor
     * @param id número de documento del vendedor
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = OUTPUT_DIR + "/ventas_" + name + id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("CC;" + id);
            writer.newLine();
            for (int salesIndex = 0; salesIndex < randomSalesCount; salesIndex++) {
                int productId = RANDOM.nextInt(PRODUCTNAMES.length) + 1;
                int quantity = RANDOM.nextInt(5) + 1;
                writer.write("P" + productId + ";" + quantity);
                writer.newLine();
            }
        }
    }

    /**
     * Genera un archivo con la información de productos disponibles.
     * @param productsCount número de productos a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {
        String fileName = OUTPUT_DIR + "/productos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= productsCount; i++) {
                String productName = PRODUCTNAMES[RANDOM.nextInt(PRODUCTNAMES.length)];
                int price = (RANDOM.nextInt(50) + 1) * 1000;
                writer.write("P" + i + ";" + productName + ";" + price);
                writer.newLine();
            }
        }
    }

    /**
     * Genera un archivo con la información de los vendedores.
     * @param salesmanCount número de vendedores a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        String fileName = OUTPUT_DIR + "/vendedores.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < salesmanCount; i++) {
                String name = NAMES[RANDOM.nextInt(NAMES.length)];
                String lastname = LASTNAMES[RANDOM.nextInt(LASTNAMES.length)];
                long id = 1000 + i;
                writer.write("CC;" + id + ";" + name + ";" + lastname);
                writer.newLine();
            }
        }
    }
}
