public  class Triple {
    public int dist;
    public int len;
    public char symbol;
    public Triple(int distance, int length, char symbol) {
        this.dist = distance;
        this.len = length;
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return "(" +dist+ ", " +len+ ", '" +symbol+ "')";
    }
}