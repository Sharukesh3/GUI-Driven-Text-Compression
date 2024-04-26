public class CompressionRatio{
    private final int originalSize;
    private final int compressedSize;

    public CompressionRatio(int originalSize, int compressedSize) {
        this.originalSize = originalSize;
        this.compressedSize = compressedSize;
    }

    public double calculateRatio() {
        if (originalSize == 0) {
            System.err.println("Cannot calculate compression ratio. Original size is zero.");
            return Double.NaN;
        }
        return (double) compressedSize / originalSize;
    }

}