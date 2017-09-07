package cs6301.g44;

public class Node<T> {
    T data;
    Node<T> next;

    public Node(T data, Node<T> nxt){
        this.data = data;
        this.next = nxt;
    }

}
