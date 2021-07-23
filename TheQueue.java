public class TheQueue {
    private final int size = 20;
    private int[] array;
    private int front;
    private int rear;

    public TheQueue(){
        array = new int[size];
        front = 0;
        rear = -1;
    }

    public void insert(int value) {
        if(rear == size -1)
            rear = -1;
        array[++rear] = value;
    }

    public int remove() {
        int temp = array[front++];
        if (front == size)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return ( rear+1==front || (front+ size -1==rear) );
    }
}