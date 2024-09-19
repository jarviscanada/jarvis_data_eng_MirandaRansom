package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;

public class ArrayJList implements JList{
  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * The array buffer into which the elements of the ArrayList are stored.
   * The capacity of the ArrayList is the length of this array buffer.
   */
  transient Object[] elementData;

  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  private int size;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param  initialCapacity  the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity
   *         is negative
   */
  public ArrayJList(int initialCapacity){
    if(initialCapacity > 0){
      this.elementData = new Object[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public ArrayJList(){
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * @param o element to be appended to this list
   * @return <tt>true</tt> )
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean add(Object o) {
    if (size == elementData.length){  //need to grow
        grow();
    }
    elementData[size++] = o;
    return true;
  }

  private void grow(){
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); //increase size by 50%
    elementData = Arrays.copyOf(elementData, newCapacity);  //does NOT account for MAX_ARRAY_SIZE as Java implementaiton does
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element).
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs.
   *
   * @return an array containing all of the elements in this list in proper sequence
   */
  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns <tt>true</tt> if this list contains no elements.
   *
   * @return <tt>true</tt> if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  /**
   * Returns the index of the first occurrence of the specified element in this list, or -1 if this
   * list does not contain the element. More formally, returns the lowest index <tt>i</tt> such
   * that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index.
   *
   * @param o
   */
  @Override
  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++)
        if (elementData[i]==null)
          return i;
    } else {
      for (int i = 0; i < size; i++)
        if (o.equals(elementData[i]))
          return i;
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element. More formally, returns
   * <tt>true</tt> if and only if this list contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range (<tt>index &lt; 0 || index &gt;=
   *                                   size()</tt>)
   */
  @Override
  public Object get(int index) {
    return elementData[index];
  }

  /**
   * Removes the element at the specified position in this list. Shifts any subsequent elements to
   * the left (subtracts one from their indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public Object remove(int index) {
    Object rmItem = elementData[index];
    elementData[index] = null;
    return rmItem;
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elementData[i] = null;
    }

    size = 0;
  }
}
