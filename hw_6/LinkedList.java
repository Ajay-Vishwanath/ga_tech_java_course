public class LinkedList<E> {
  private Node<E> head;
  private int size;

  public LinkedList() {
    head = null;
    size = 0;
  }

  private class Node<E> {
    private E data;
    private Node<E> next;

    private Node(E data, Node<E> next) {
      this.data = data;
      this.next = next;
    }
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    head = null;
    size = 0;
  }

  public void add(int index, E newData){
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    if (index == 0) {
      head = new Node<E>(newData, head);
    } else {
      Node<E> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      Node<E> newNode = new Node<E>(newData, current.next);
      current.next = newNode;
    }

    size++;
  }

  public void add(E newData) {
    add(size, newData);
  }

  public boolean contains(Object obj){
    Node<E> current = head;
    while (current != null) {
      if (current.data == null ? obj == null : current.data.equals(obj)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public E get(int index){
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  public int indexOf(Object obj){
    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      if (current.data == null ? obj == null : current.data.equals(obj)) {
        return i;
      }
      current = current.next;
    }
    return -1;
  }

  public E remove(int index){
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    Node<E> removedNode;
    if (index == 0) {
      removedNode = head;
      head = head.next;
    } else {
      Node<E> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      removedNode = current.next;
      current.next = current.next.next;
    }

    size--;
    return removedNode.data;
  }

  public boolean remove(Object obj){
    Node<E> current = head;
    Node<E> previous = null;

    for (int i = 0; i < size; i++) {
      if (current.data == null ? obj == null : current.data.equals(obj)) {
        if (previous == null) {
          head = head.next;
        } else {
          previous.next = current.next;
        }
        size--;
        return true;
      }
      previous = current;
      current = current.next;
    }
    return false;
  }

  public E set(int index, E newData){
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    E oldValue = current.data;
    current.data = newData;
    return oldValue;
  }

  public String toString(){
    String result = "[";
    Node<E> current = head;
    while (current != null) {
      result += String.valueOf(current.data);
      if (current.next != null) {
        result += ", ";
      }
      current = current.next;
    }
    result += "]";
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    return true;
  }
}