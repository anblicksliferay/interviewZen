package com.anblicks.interview.zen.q3;

/*  Class Node  */
class Node
{
    protected int data;
    protected Node next, prev;
 
    /* Constructor */
    public Node()
    {
        next = null;
        prev = null;
        data = 0;
    }
    /* Constructor */
    public Node(int d, Node n, Node p)
    {
        data = d;
        next = n;
        prev = p;
    }
    /* Function to set link to next node */
    public void setLinkNext(Node n)
    {
        next = n;
    }
    /* Function to set link to previous node */
    public void setLinkPrev(Node p)
    {
        prev = p;
    }    
    /* Funtion to get link to next node */
    public Node getLinkNext()
    {
        return next;
    }
    /* Function to get link to previous node */
    public Node getLinkPrev()
    {
        return prev;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }
}
 
/* Class Doubly CircularLinkedList */
public class TrainComposition
{
    protected Node start;
    protected Node end ;
    public int size;
 
    /* Constructor */
    public TrainComposition()
    {
        start = null;
        end = null;
        size = 0;
    }
    /* Function to check if list is empty */
    public boolean isEmpty()
    {
        return start == null;
    }
    /* Function to get size of list */
    public int getSize()
    {
        return size;
    }
    /* Function to insert element at beginning */
    public void insertAtStart(int val)
    {
        Node nptr = new Node(val, null, null);    
        if (start == null)
        {            
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;            
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;        
        }
        size++ ;
    }
    /*Function to insert element at end */
    public void insertAtEnd(int val)
    {
        Node nptr = new Node(val, null, null);        
        if (start == null)
        {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            end = nptr;    
        }
        size++;
    }
   
    /* Function to delete node at end  */
    public void deleteAtEnd() {
    	end = end.getLinkPrev();
        end.setLinkNext(start);
        start.setLinkPrev(end);
        size-- ;
        return;
    }
    /* Function to delete node at start  */
    public void deleteAtStart()
    {        
    	 if (size == 1)
         {
             start = null;
             end = null;
             size = 0;
             return; 
         }
         start = start.getLinkNext();
         start.setLinkPrev(end);
         end.setLinkNext(start);
         size--; 
         return ;
    }    
    /* Function to display status of list */
    public void display()
    {
        System.out.print("\nTrain Composition = ");
        Node ptr = start;
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == start) 
        {
            System.out.print(start.getData() + "\n");
            return;
        }
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != start) 
        {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");
    }
}
 
