package com.example.rgr.data.structure; /************************************************************************************************************
 * 										Mentesnot Aboset
 * **********************************************************************************************************
 * 
 * This class implements Doubly linked list node set and get methods
 * 
 * **********************************************************************************************************/

import java.io.Serializable;

public class Node<T> implements Serializable {
	protected Node<T> next;        //next node reference
	protected Node<T> previous;    //previous node reference
	protected T data;				  //data in the node
	
	public Node(T data)
	// node constructor with one argument
	{
		  this.data = data;
	      next = previous = null;
	}
	// returns the next node in this list
	public Node<T> getNext()
	{
		return next;
	}
	// sets the next node in the list
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	// returns the data in this node
	public T getData()
	{
		return data;
	}
	// sets the data in this node
	public void setData(T data)
	{
		this.data = data;
	}
	// gets the previous node in this list
	public Node<T> getPrevious()
	{
		return previous;
	}
	// sets the previous node in this list
	public void setPrevious(Node<T> previous)
	{
		this.previous = previous;	
	}
}
