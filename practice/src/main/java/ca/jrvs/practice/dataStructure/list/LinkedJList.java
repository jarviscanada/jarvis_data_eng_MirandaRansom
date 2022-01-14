package ca.jrvs.practice.dataStructure.list;

public class LinkedJList<E> implements JList<E>{
  transient int size = 0;
  transient Node<E> first;
  transient  Node<E> last;

  public LinkedJList(){ }

  private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * @param e element to be appended to this list
   * @return <tt>true</tt>
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
    link(e);
    return true;
  }

  private void link(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;

    if(l == null){
      first = newNode;
    } else {
      l.next = newNode;
    }
    size++;
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
    Object[] result = new Object[size];
    int i=0;
    for (Node<E> x = first; x != null; x = x.next){
      result[i++] = x.item;
    }
    return result;
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
    int index = 0;
    if (o == null){
      for (Node<E> x = first; x != null; x = x.next){
        if (x.item == null);
        return index;
      }
      index++;
    } else {
      for (Node<E> x = first; x != null; x = x.next){
        if (o.equals(x.item)){
          return index;
        }
        index++;
      }
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
    return indexOf(o) != -1;
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
  public E get(int index) {
    //check index
    if (!(index >= 0 && index < size)) {
      throw new IndexOutOfBoundsException();
    }
    return node(index).item;
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
  public E remove(int index) {
    return unlink(node(index));
  }

  E unlink(Node<E> x) {
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null){
      first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null){
      last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.item = null;
    size --;
    return element;
  }

  Node<E> node(int index){
    Node<E> x;
    if (index < (size >> 1)){
      x = first;
      for (int i=0; i < index; i++){
        x = x.next;
      }
    } else {
      x = last;
      for (int i=size -1; i>index; i--){
        x = x.prev;
      }
    }
    return x;
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   */
  @Override
  public void clear() {
    for (Node<E> x = first; x != null ;){
      Node<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }
}
