public class ThePriorityQueue {
    private final int SIZE = 20;
    private Edge[] array;
    private int currentSize;

    public ThePriorityQueue() {
        array = new Edge[SIZE];
        currentSize = 0;
    }

    public void insert(Edge item) {
        int j;
        for (j = 0; j < currentSize; j++)
            if (item.distance >= array[j].distance)
                break;
        for (int k = currentSize - 1; k >= j; k--)
            array[k + 1] = array[k];
        array[j] = item; // insert item
        currentSize++;
    }

    public Edge removeMin() {
        return array[--currentSize];
    }

    public int find(int value) {
        for (int j = 0; j < currentSize; j++)
            if (array[j].endV == value)
                return j;
        return -1;
    }

    public Edge peekMin() {
        return array[currentSize - 1];
    }

    public Edge peekN(int n) {
        return array[n];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public void removeN(int n) {
        for (int j = n; j < currentSize - 1; j++)
            array[j] = array[j + 1];
        currentSize--;
    }
}