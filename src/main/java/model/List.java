package model;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by iushakova on 30/08/15.
 */
public class List<T> implements Iterable{

    private Node<T> first;
    private Node<T> last;
    private int size;
    private int modCount;
    Iterator<Node> iterator;

    public void add(T data) {
        Node<T> newNode = new Node<T>();
        newNode.setData(data);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        modCount++;
        size++;
    }

    //TODO: return T or Object?
    public Object get(int i) {
        if (i >= size() || i < 0) {
            throw new NoSuchElementException();
        }
        Node element = first;
        for (int j = 0; i < size; j++) {
            if (j == i) return element.getData();
            element = element.getNext();
        }
        return null;
    }

    public void printList() {
        Node node = first;
        while (node != null) {
            System.out.println("List element: " + node.getData());
            node = node.getNext();
        }
        System.out.println("Size: " + size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String sep = System.lineSeparator();
        Node node = first;
        while (node != null) {
            sb.append("List element: " + node.getData() + sep);
            node = node.getNext();
        }
        return sb.toString();
    }

    public void remove(T data) {
        switch (size) {
            case 0: {
                return;
            }
            case 1: {
                if (first.getData().equals(data)) {
                    first = null;
                    last = null;
                    size--;
                    modCount++;
                }
                break;
            }
            default: {
                if (first.getData().equals(data)) {
                    first = first.getNext();
                    size--;
                    modCount++;
                }
                Node<T> previousNode = first;
                Node<T> currentNode = first;
                while (currentNode != null) {
                    if (currentNode.getData().equals(data)) {
                        previousNode.setNext(currentNode.getNext());
                        if (currentNode.getNext() == null){
                            last = previousNode;
                        }
                        size--;
                        modCount++;
                        break;
                    }
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new Iter();
    }

    private class Iter implements Iterator{
        private Node<T> lastReturned = null;
        private Node<T> next = first;
        private int nextIndex;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.getNext();
            nextIndex++;
            return lastReturned.getData();
        }

        @Override
        public void remove() {
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
