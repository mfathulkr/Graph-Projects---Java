//-----------------------------------------------------
// Title: GenQueue class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 03
// Description: This class is exist to have generic queues since Java does not have it
//-----------------------------------------------------
import java.util.LinkedList;

class GenQueue<E> {
  public LinkedList<E> list = new LinkedList<E>();

  public void enqueue(E item) {
    list.addLast(item);
  }

  public E dequeue() {
    return list.poll();
  }

  public boolean hasItems() {
    return !list.isEmpty();
  }

  public int size() {
    return list.size();
  }

}