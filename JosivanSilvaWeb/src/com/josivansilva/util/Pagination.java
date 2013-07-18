package com.josivansilva.util;

/**
 * Pagination util class.
 * 
 * @author Josivan Silva
 *
 */
public class Pagination {
	private int pageNumber;
	private int first;
	private int previous;
	private int next;
	private float pageRows;
	private int last;
	private float rows;
	
	public Pagination () {
		if (this.pageNumber == 0) {
			this.pageNumber = 1;
		}
		this.pageRows   = 1;
		this.first      = 1;
		this.previous   = 1;
		this.next       = 1;
		this.last       = 1;
	}	
	public int getPageNumber() {
		return this.pageNumber;
	}
	public void setPageNumber (int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}	
	public int getPrevious() {
		if (this.pageNumber > 1) {
			this.previous = this.pageNumber - 1;
		}
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}	
	public int getNext() {
		int last = getLast();
		if (this.pageNumber == last) {
			this.next = this.pageNumber;
		} else {
			this.next = this.pageNumber + 1;
		}
		return next;
	}
	public void setNext (int next) {
		this.next = next;
	}
	public float getPageRows() {
		return pageRows;
	}
	public void setPageRows(float pageRows) {
		this.pageRows = pageRows;
	}
	public int getLast() {
		this.last = (int) Math.ceil (this.rows / this.pageRows);
		return this.last;
	}
	public void setLast (int last) {
		this.last = last;
	}
	public float getRows() {
		return rows;
	}
	public void setRows(float rows) {
		this.rows = rows;
	}
	public String getLimit () {
    	return (this.pageNumber - 1) * (int)this.pageRows + "," + (int)this.pageRows;
    }
	
}
