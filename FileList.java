

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class FileList<E> extends FileContainer implements List<E>  {

	@SuppressWarnings("unused")
	private final String LEGAL_ELEMENTS[] = {"Byte", "Short", "Integer", "Long", "Double", "Float"};

	private Long time = System.currentTimeMillis();
	private String fileName = Long.toString(time) + ".list";
	private int size = 0;
	File file = null;
	PrintWriter fs = null;
	PrintWriter temp = null;
	private static int count = 0;
	    /**
     * Constructs an empty Filelist with the name System.currentTimeMillis().
     */
	public FileList() {
		file = new File(fileName);
		try {
			fs = new PrintWriter(new FileOutputStream(file, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    /**
     * Opens existing FileList.
     */
	public FileList(String filename){
		this.fileName = filename;
		file = new File(filename);
		try {
			fs = new PrintWriter(new FileOutputStream(file, true));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getFileName() {
		
		
		return fileName;
	}

	@Override
	public long getFileSize() {
		// TODO Auto-generated method stub
		return file.length();
	}
	
	    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
	public boolean add(E e) {
		try {
			fs = new PrintWriter(new FileOutputStream(file, true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fs.println(isLegal(e));
		fs.println(e);
		fs.close();
		return true;
	}

	    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
	public void add(int index, E element) {
		List<Number> temp = fileToList();
		temp.add(index, (Number) element);
		this.listToFile(temp);
		
	}

	

	private void listToFile(List<Number> list) {
		this.clear();
		for( Number items : list) {
			this.add((E) items);
		}
	}
	
	private  List<Number> fileToList(){
		ArrayList<Number> temp = new ArrayList<Number>();
		try {
			Scanner in = new Scanner(file);
			String currentPos;
			while(in.hasNextLine()) {
				currentPos = in.nextLine();
				if(currentPos.equals(LEGAL_ELEMENTS[0])) {
					temp.add(new Byte(in.nextByte()));
				}
				else if(currentPos.equals(LEGAL_ELEMENTS[1])) {
					temp.add(new Short(in.nextShort()));
				}
				else if(currentPos.equals(LEGAL_ELEMENTS[2])) {
					temp.add(new Integer(in.nextInt()));
				}
				else if(currentPos.equals(LEGAL_ELEMENTS[3])) {
					temp.add(new Long(in.nextLong()));
				}
				else if(currentPos.equals(LEGAL_ELEMENTS[4])) {
					temp.add(new Double(in.nextDouble()));
				}
				else if(currentPos.equals(LEGAL_ELEMENTS[5])) {
					temp.add(new Float(in.nextFloat()));
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}
	
	private String isLegal(E e) {
		if(!(e instanceof Number)){
			throw new IllegalArgumentException("Must be legal Number");
		}
		return e.getClass().getSimpleName();
	}


	    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this list
     */
	public void clear(){
		PrintWriter fss;
		try {
			fss = new PrintWriter(file);
			fss.print("");
			fss.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
	public E remove(int index) {
		List<Number> temp = fileToList();
		Number removing = temp.remove(index);
		this.listToFile(temp);
		return (E) removing;
	}

	    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this list
     */
	public boolean remove(Object o) {
		List<Number> temp = fileToList();
		boolean removing = temp.remove(o);
		this.listToFile(temp);


		return removing;
	}
	    /**
     * Returns the FileList as a String 
     *
     * @return FileList as String
     */
	public String toString() {
		String ret = "";
		try {
			ret = "[";
			Scanner in = new Scanner(file);
			while (in.hasNextLine()){
				in.nextLine();
				ret += in.nextLine() + ", ";
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ret += "]";
		return ret;  
	}

	    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
	public int size() {
		size = 0;
		try {
			
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				size++;
				in.nextLine();
			}
			size = size/2;
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}






	public E set(int i, E e){
		throw new UnsupportedOperationException(); 
	}



	public boolean contains(Object e){
		throw new UnsupportedOperationException(); 
	}
	public Iterator<E> iterator(){
		throw new UnsupportedOperationException();
	}

	public Object[] toArray(){
		throw new UnsupportedOperationException();
	}
	public <T>T[] toArray(T[] a){
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o){
		throw new UnsupportedOperationException();
	}
	public E get(int index){
		throw new UnsupportedOperationException();
	}
	public boolean isEmpty(){
		throw new UnsupportedOperationException();
	}

	public List<E> subList(int i,int ii){
		throw new UnsupportedOperationException();
	}
	public int addAll(Object o){
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}