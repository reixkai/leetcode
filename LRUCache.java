import java.util.HashMap;
import java.util.Map;

/**
 *  Design and implement a data structure for Least Recently Used (LRU) cache. 
 *  It should support the following operations: get and set.
 *  	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 *  	set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
 *  	it should invalidate the least recently used item before inserting a new item. 
 */

class CacheQueue {
	private int capacity;
	private int currentSize;
	private Node tail;
	private Node head;

	CacheQueue(int capacity) {
		this.capacity = capacity;
		this.currentSize = 0;
	}

	/**
	* Moves a node that's already in the queue
	* @param node
	*/
	public void moveToEnd(Node node) {
		if (node != null) {
			if (this.head == null) { // new node
				add(node);
			} else if (!node.equals(this.tail)) {
				Node prev = node.getPrev();
				Node next = node.getNext();
				if (prev != null && next != null) { // in the middle
					next.setPrev(prev);
					prev.setNext(next);
				} else if (prev == null && next != null) {
					// this is the same as head
					if (!node.equals(this.head)) {
						// should throw an error here instead of print
						System.out.println("prev is null but next is not, and also not the head");
					} else {
						next.setPrev(null);
						node.setPrev(this.tail);
						this.head = next;
					}
				} else {
					// should throw an error here instead of print
					System.out.println("This shouldn't happen...");
				}
				this.tail.setNext(node);
				node.setPrev(tail);
				node.setNext(null);
				this.tail = node;
			}
		}
	}

	public boolean isFull() {
		return this.currentSize >= this.capacity;
	}

	/**
	* Removes first node (LRU) from the queue.
	* @return key of removed node.
	*/
	public int removeTop() {
		if (this.currentSize > 0) {
			int key = this.head.getKey();
			this.head = this.head.getNext();
			if (this.head == null) {
				this.tail = null;
			} else {
				this.head.setPrev(null);
			}
			this.currentSize--;
			return key;
		}
		return -1;
	}

	/**
	* Adds to the end of the queue
	* @param newNode
	*/
	public void add(Node newNode) {	
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			this.tail.setNext(newNode);
			newNode.setPrev(tail);
			newNode.setNext(null);
			this.tail = newNode;
		}
		this.currentSize++;
	}

	public Node getHead() {
		return this.head;
	}

	public Node getTail() {
		return this.tail;
	}
}

class Node {
	private int key;
	private int value;
	private Node next;
	private Node prev;

	public Node(int key, int value) {
		this.key = key;
		this.value = value;
		this.next = null;
		this.prev = null;
	}

	public Node getPrev() {
		return this.prev;
	}

	public void setNext(Node newNode) {
		this.next = newNode;
	}

	public void setPrev(Node newNode) {
		this.prev = newNode;
	}

	public Node getNext() {
		return this.next;
	}

	public int getKey() {
		return this.key;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}

public class LRUCache {
	private Map<Integer, Node> nodeMap;
	private CacheQueue cacheQueue;

	public LRUCache(int capacity) {
		this.nodeMap = new HashMap<Integer, Node>();
		this.cacheQueue = new CacheQueue(capacity);
	}

	public int get(int key) {
		Node node = this.nodeMap.get(key);
		if (node != null) {
			this.cacheQueue.moveToEnd(node);
			return node.getValue();
		}
		return -1;
	}

	public void set(int key, int value) {
		if (nodeMap.containsKey(key)) {
			Node node = nodeMap.get(key);
			node.setValue(value);
			cacheQueue.moveToEnd(node);
		} else {
			if (cacheQueue.isFull()) {
				int topKey = cacheQueue.removeTop();
				nodeMap.remove(topKey);
			}
			Node newNode = new Node(key, value);
			cacheQueue.add(newNode);
			nodeMap.put(key, newNode);
		}
	}
}