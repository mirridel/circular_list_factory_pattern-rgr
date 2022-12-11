package com.example.rgr.data.structure;

import com.example.rgr.data.types.Comparator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.rgr.data.Action;
import com.example.rgr.data.Counter;

public class CircularList<T> implements Serializable {
	
	protected Node<T> head;		 //the first node of the list
	protected Node<T> tail;		 //the last node of the list
	protected Node<T> location;	 // true if element found, else false
	protected int numElements;		 // Number of elements in this list
	protected boolean found;         // true if element found, else false
	
	public CircularList()
	//default constructor
	{
		head = null;
		tail = null;
		numElements = 0;
		location = null;
	}
	
	public boolean isEmpty()
	//true if list is empty, else false
	{
		return (head == null);
	}
	
	public int size()
	// Determines the number of elements on this list
	{
		return numElements;
	}
	
	protected void find(T target) {
      location = head;
      found = false;
      if(!isEmpty()) {
    	  do {
            if (location.getData().equals(target)) {
             found = true;
             return;
            }
            else {
                location = location.getNext();
            }
          }while(location != tail.getNext());
      }
      
    }

    public boolean contains (T element) {
		 find(element);
		 return found;
	}
    
	protected void findPosition(int position) 
	//finds position in this list
	// Assumes Zero based indexing 
	{
		int start = 0;
		location = head;
		found = false;
	
		if ((!isEmpty()) && (position >= 0) && (position <= size())) {
			do {
				// move search to the next node
				location = location.getNext();
				start++;

			} while ((location != head) && start < position);
			found = true;
		}

	}
	
	public T get(T data)
	  // Returns an element e from this list such that e.equals(element);
	  // if no such element exists, returns null.
	  {
	    find(data);    
	    if (found)
	      return location.getData();
	    else
	      return null;
	  }
	
	public void reset()
	  // Initializes current position for an iteration through this list,
	  // to the first element on this list.
	  {
	      location  = head;
	  }
	
	
	public void add(T data)
	// Adds element to this list.
	{
		Node<T> newNode = new Node<T>(data);   // Reference to the new node being added
	   	
	   	 if (isEmpty())            // Adding into an empty list
	   	 {
	   		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);	   	 
	   	 }
	   	 else                      // Adding into a non-empty list
	   	 {
	   		 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);
	   	 }
	   	 numElements++;
	}

    
    public void addFront(T data) 
    //adds new element to the front of the list
    {
    	Node<T> newNode = new Node<T>(data);   // Reference to the new node being added
	   	
    	if (isEmpty())            // Adding into an empty list
	   	 {
    		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);
	   	 }
	   	 else                       // Adding into a non-empty list
	   	 {
	   		newNode.setNext(head);
	   		head.setPrevious(newNode);
	   	    head = newNode;
	   	    head.setPrevious(tail);
	   	    tail.setNext(head);
	   	 }
	   	 numElements++;
	   	
    }
    
    public void addBack(T data)
    //adds new element to the back of the list
    {
    	Node<T> newNode = new Node<T>(data);   // Reference to the new node being added
	   	
	   	 if (isEmpty())            // Adding into an empty list
	   	 {
	   		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);	   	 
	   	 }
	   	 else                      // Adding into a non-empty list
	   	 {
	   		 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);
	   	 }
	   	 numElements++;
	   	
    }
    
    public void addAtPosition(T data, int position)
    //adds new element to the specified position
    {
   	 Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			// add element to an empty list
			 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);
			
		} else if (position <= 0 ) {
			// insert at front of the list
			newNode.setNext(head);
	   		head.setPrevious(newNode);
	   	    head = newNode;
	   	    head.setPrevious(tail);
	   	    tail.setNext(head);
	   	  
		} else if (position >= size()) {
			// if position does not exist, perform add at the most efficient
			// position for circular doubly linked list, the most efficient position is
			// at the end.		
			 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);	

		} else {
			/* General Case */
			// determine location where to perform insert
			findPosition(position);
			
			//inserts the elements to the specified position
			newNode.setPrevious(location.getPrevious());
       	newNode.setNext(location);
       	location.getPrevious().setNext(newNode);
       	location.setPrevious(newNode);
			
		}
		numElements++;
	}


	public T getHeadData()
	{
		return head.getData();
	}

	public T getTailData()
	{
		return tail.getData();
	}

	public T getDataAtPosition(int position)
	//adds new element to the specified position
	{
		if (position <= 0 ) {
			return getHeadData();
		} else if (position >= size()) {
			return getTailData();
		} else {
			findPosition(position);
			return location.getData();
		}
	}

	public boolean remove (T element)
    // Removes an element e from this list such that e.equals(element)
    // and returns true; if no such element exists, returns false.
    {
      find(element);
      if (found)
      { 
    	if(location == head && size() == 1) //removes the only existing element
    		                                //empties the list
    	{
    		head = null;
    		tail = null;
    		
    	}else if (location == head)    //removes first node
    	{   		
    		head = head.getNext(); 
            head.setPrevious(tail);
            tail.setNext(head); 
            
        }else if(location == tail)		//removes last node
        {
        	
        	tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail); 
        }
        else{						 // removes node at location
        	location.getPrevious().setNext(location.getNext());  
        	location.getNext().setPrevious(location.getPrevious());  	
        }
        numElements--;
      }
      return found;
    }
	
	 public void removeFront()
	 //removes the first element in the list
	 {
	        if(!isEmpty()){
	        	if(head.getNext() == head){  //if the first element is the only element in the list,	        		             //it empties the list
	        		head = null;
	        		tail = null;
	        	}else{			//removes the first element
	              head = head.getNext();
	              head.setPrevious(tail);
	              tail.setNext(head);              
	        	}
	        }
	        numElements--;
	 }
	 
	 public void removeBack()
	 //removes the last element in this list
	 {
		 
	     if(!isEmpty()){
	    	 
	    	 if(head.getNext() == head){ //if the last element is the only element in the list,
	                          //it empties the list      	
	    		 head = null;
	    		 tail = null;
	         }else{				//removes the last element
	            tail = tail.getPrevious();
	            tail.setNext(head);
	            head.setPrevious(tail); 
	         }
	     }
	     numElements--;
	    }
	 
	 public void removeAtPosition(int position)
	 //removes the element in the specified position
	 {
		 if(position <= 0){		//removes front element
			 head = head.getNext();	
			 head.setPrevious(tail);
             tail.setNext(head);   
		 }else if(position >= size() - 1){ //remove last element
			 
			 tail = tail.getPrevious(); 
	         tail.setNext(head);
	         head.setPrevious(tail); 
			 
		 }else{
			   //general case
			   //determines the position
			    findPosition(position);
			    
			    //removes the element in the specified position
				location.getPrevious().setNext(location.getNext());
				location.getNext().setPrevious(location.getPrevious());
		 }
		 numElements--;
	 }
    
    public String toString() {
    	String item = "[";
    		Node<T> current = head;
    		if(!isEmpty()){
    			do {
					item += current.getData();
					if (!current.equals(tail))
						item += ", ";
        			current = current.getNext();
        		} while(current != head);
    		}
    		item += "]";
        return item;
    }
    
    
    public String printReverse() {
    	String item = "";
    		Node<T> current = tail;
    		if(!isEmpty()){
    			do{
        			item += current.getData() + ", ";
        			current = current.getPrevious();
        		}while(current != tail);
    		}
    		item += "]";
        return item;
    }

	public void forEach(Action<T> a) {
		Node tmp = head;
		for (int i = 0; i < this.size(); i++) {
			a.toDo((T) tmp.getData());
			tmp = tmp.next;
		}
	}

	public void clear(){
		while (size() > 0)
			this.removeBack();
	}

	public void addAll(CircularList<T> input){
		input.forEach(el-> this.addBack(el));
	}

	public CircularList<T> subList(int min, int max){
		CircularList<T> lst = new CircularList<>();
		AtomicInteger n = new AtomicInteger();

		this.forEach(el ->{
			if (min <= n.get() && n.get() < max)
				lst.addBack(el);
			n.getAndIncrement();
		});

		return lst;
	}

	public CircularList<T> mergeSort(Comparator<T> comparator) {
		int mid = size() / 2;
		if (mid == 0)
			return this;
		CircularList<T> L = subList(0, mid);
		CircularList<T> R = subList(mid, size());
		CircularList<T> first = L.mergeSort(comparator);
		CircularList<T> second = R.mergeSort(comparator);
		return merge(first, second, comparator);
	}

	private CircularList<T> merge(CircularList<T> first, CircularList<T> second, Comparator<T> comparator){
		CircularList<T> accumulator = new CircularList<T>();

		if (first != null && second != null) {
			while (first.size() > 0 && second.size() > 0) {
				if (comparator.compare(first.getHeadData(), second.getHeadData()) <= 0) {
					accumulator.addBack(first.getHeadData());
					first.removeFront();
				}
				else {
					accumulator.addBack(second.getHeadData());
					second.removeFront();
				}
			}
			while (first.size() > 0) {
				accumulator.addBack(first.getHeadData());
				first.removeFront();
			}
			while (second.size() > 0) {
				accumulator.addBack(second.getHeadData());
				second.removeFront();
			}
		}
		return accumulator;
	}

	// РЕАЛИЗАЦИЯ СО СЧЕТЧИКОМ
	public CircularList<T> subList(int min, int max, Counter count){
		CircularList<T> lst = new CircularList<>();
		AtomicInteger n = new AtomicInteger();

		this.forEach(el ->{
			if (min <= n.get() && n.get() < max) {
				lst.addBack(el);
				count.inc();
			}
			n.getAndIncrement();
		});

		return lst;
	}

	public CircularList<T> mergeSort(Comparator<T> comparator, Counter count) {

		int mid = size() / 2;
		if (mid == 0)
			return this;

		CircularList<T> L = subList(0, mid, count);
		CircularList<T> R = subList(mid, size(), count);

		CircularList<T> first = L.mergeSort(comparator, count);
		CircularList<T> second = R.mergeSort(comparator, count);

		return merge(first, second, comparator, count);
	}

	private CircularList<T> merge(CircularList<T> first, CircularList<T> second,
								  Comparator<T> comparator, Counter count){
		CircularList<T> accumulator = new CircularList<T>();

		if (first != null && second != null) {
			while (first.size() > 0 && second.size() > 0) {
				if (comparator.compare(first.getHeadData(), second.getHeadData()) <= 0) {
					accumulator.addBack(first.getHeadData());
					count.inc();
					first.removeFront();
				}
				else {
					accumulator.addBack(second.getHeadData());
					count.inc();
					second.removeFront();
				}
			}
			while (first.size() > 0) {
				accumulator.addBack(first.getHeadData());
				count.inc();
				first.removeFront();
			}
			while (second.size() > 0) {
				accumulator.addBack(second.getHeadData());
				count.inc();
				second.removeFront();
			}
		}
		return accumulator;
	}
}