package edu.ser222.m01_03;

import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface. Also provides a main that
 * demonstrates it.
 * 
 * @author Borys Banaszkiewicz, Acuna
 * @version 1.0
 * @param <Item> contained type
 */

public class CompletedDeque<Item> implements Deque<Item> {
    
    public class DoubleLinearNode<Item> {
        
        private DoubleLinearNode<Item> next;
        private DoubleLinearNode<Item> prev;
        private Item val;
        
        public DoubleLinearNode() {
            this.next = null;
            this.prev = null;
            this.val = null;
        }
        
        public DoubleLinearNode(Item element) {
            this.next = null;
            this.prev = null;
            this.val = element;
        }
        
        public DoubleLinearNode<Item> getNext() {
            return this.next;
        }
        
        public void setNext(DoubleLinearNode<Item> node) {
            this.next = node;
        }
        
        public DoubleLinearNode<Item> getPrevious() {
            return this.prev;
        }
        
        public void setPrevious(DoubleLinearNode<Item> node) {
            this.prev = node;
        }
        
        public Item getElement() {
            return this.val;
        }
        
        public void setElement(Item element) {
            this.val = element;
        }
    }
    
    private DoubleLinearNode<Item> head;
    private DoubleLinearNode<Item> tail;
    private int size;
    
    
    public CompletedDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**  
     * Adds one element to the front of this Deque. 
     * @param element the element to be added to the front of the Deque  
     */
    @Override
    public void enqueueFront(Item element) {
        DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element);
        
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
            this.head = newNode;
        }
        
        this.size++;
    }
    
    /**  
     * Adds one element to the back of this Deque. 
     * @param element the element to be added to the back of the Deque  
     */
    @Override
    public void enqueueBack(Item element) {
        DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element);
        
        if (this.tail == null) {
            this.tail = newNode;
            this.head = newNode;
        }
        else {
            newNode.setPrevious(this.tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        
        this.size++;
    }
    
    /**  
     * Removes and returns the element at the front of this Deque.
     * Throws an exception if the Deque is empty.
     * @return the element at the front of this Deque
     * @throws NoSuchElementException if the Deque is empty
     */
    @Override
    public Item dequeueFront() throws NoSuchElementException {
        if (this.head == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        
        Item result = this.head.getElement();
        
        this.head = this.head.getNext();
        
        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.setPrevious(null);
        }
        
        this.size--;
        
        return result;
    }
    
    /**  
     * Removes and returns the element at the back of this Deque.
     * Throw an exception if the Deque is empty.
     * @return the element at the back of the Deque. 
     * @throws NoSuchElementException if the Deque is empty
     */
    @Override
    public Item dequeueBack() throws NoSuchElementException {
        if (this.tail == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        
        Item result = this.tail.getElement();
        
        this.tail = this.tail.getPrevious();
        
        if (this.tail == null) {
            this.head = null;
        } else {
            this.tail.setNext(null);
        }
        
        this.size--;
        
        return result;
    }
    
    /**  
     * Returns, without removing, the element at the front of this Deque.
     * Should throw an exception if the Deque is empty.
     * @return the first element in the Deque
     * @throws NoSuchElementException if the Deque is empty
     */
    @Override
    public Item first() throws NoSuchElementException {
        if (this.head == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        
        return this.head.getElement();
    }
    
    /**  
     * Returns, without removing, the element at the back of this Deque.
     * Should throw an exception if the Deque is empty.
     * @return the last element in the Deque
     * @throws NoSuchElementException if the Deque is empty
     */
    @Override
    public Item last() throws NoSuchElementException {
        if (this.tail == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        
        return this.tail.getElement();
    }
    
    /**  
     * Returns true if this Deque is empty and false otherwise.
     * @return if Deque empty
     */
    @Override
    public boolean isEmpty() {
        return this.tail == null && this.head == null;
    }
    
    /**  
     * Returns the number of elements in this Deque. 
     * @return the number of elements in the Deque
     */
    @Override
    public int size() {
        return this.size;
    }
    
    /**  
     * Returns a string representation of this Deque. The back element
     * occurs first, and each element is separated by a space. If the
     * Deque is empty, returns "empty".
     * @return the string representation of the Deque
     */
    @Override
    public String toString() {
        if (this.head == null) {
            return "empty";
        }
        
        DoubleLinearNode<Item> curr = this.tail;
        
        StringBuilder str = new StringBuilder();
        
        while (curr != null) {
            str.append(curr.getElement()).append(" ");
            curr = curr.getPrevious();
        }
        
        return str.toString();
    }
    public DoubleLinearNode<Item> getNext() {
        if (head != null) {
            return head.getNext();
        }
        return null;
    }

    public void setNext(DoubleLinearNode<Item> node) {
        if (head != null && head.getNext() != null) {
            head.getNext().setNext(node);
        }
    }
    /**
     * Program entry point for Deque. 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        CompletedDeque<Integer> deque = new CompletedDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
        System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
        
        CompletedDeque<Integer> deque_test_Int = new CompletedDeque<>();
        System.out.println(deque_test_Int.toString()); // output should be "empty"
        deque_test_Int.enqueueFront(5);
        deque_test_Int.enqueueFront(7);
        deque_test_Int.enqueueBack(6);
        deque_test_Int.enqueueFront(54);
        System.out.println(deque_test_Int.toString()); // output should be "6 5 7 54"
        
        deque_test_Int.dequeueFront();
        
        System.out.println(deque_test_Int.toString()); // output should be "6 5 7"
        
        deque_test_Int.dequeueBack();
        
        System.out.println(deque_test_Int.toString()); // output should be "5 7"
        
        deque_test_Int.dequeueFront();
        deque_test_Int.dequeueBack();
        System.out.println(deque_test_Int.toString()); // output should be "empty"
        
//        deque_test_Int.dequeueBack();
//        System.out.println(deque_test_Int.toString()); // output should be exception
        
        deque_test_Int.enqueueFront(5);
        System.out.println(deque_test_Int.toString()); // output should be "5"
                
        CompletedDeque<String> deque_test_String = new CompletedDeque<>();
        deque_test_String.enqueueFront("World");
        deque_test_String.enqueueBack("Hello");
        
        System.out.println(deque_test_String.toString()); // output should be "Hello World "
        
        deque_test_String.dequeueFront();
        
        System.out.println(deque_test_String.toString()); // output should be "Hello "
        
        deque_test_String.dequeueBack();
        
        System.out.println(deque_test_String.toString()); // output should be "empty"
        
        
        CompletedDeque<String> test = new CompletedDeque<>();
        
        test.enqueueFront("A");
        test.enqueueBack("B");
        test.enqueueBack("C");
        System.out.println(test.toString()); // output should be "C B A "
        
        test.setNext(test.getNext().getNext());
        System.out.println(test.toString()); // output should be "C B A "
    }
} 