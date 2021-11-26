/**
 * Copyright 2016 SPeCS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.j2h.list;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.interfaces.CombinedList;
import pt.up.fe.specs.j2h.interfaces.HasHaskellString;
import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.iterator.HListIteratorCombined;
import pt.up.fe.specs.j2h.list.util.HListCycle;
import pt.up.fe.specs.j2h.list.util.HListJava;
import pt.up.fe.specs.j2h.tuple.Tuple2;
import pt.up.fe.specs.j2h.tuple.Tuple2List;

public abstract class HList<T> extends AbstractList<T> implements HasHaskellString {

    private final SizeType sizeType;

    public HList(SizeType sizeType) {
	this.sizeType = sizeType;
    }

    /*
     * Java helpers
     */
    /**
     * 
     * @return the class of the current element
     */
    // protected abstract Class<T> getElementClass();

    /*
     * Haskell methods
     */

    /**
     * By default, returns false.
     * 
     * @return true if list is infinite
     */
    public boolean isInf() {
	return sizeType == SizeType.INFINITE;
    }

    public SizeType getSizeType() {
	return sizeType;
    }

    /**
     * Appends the given list to the end of the current list.
     */
    @SuppressWarnings("unchecked")
    public HList<T> conc(HList<T> list) {
	List<HList<T>> lists = new ArrayList<>();
	if (CombinedList.class.isInstance(this)) {
	    lists.addAll(((CombinedList<T>) this).getLists());
	} else {
	    lists.add(this);
	}

	if (CombinedList.class.isInstance(list)) {
	    lists.addAll(((CombinedList<T>) list).getLists());
	} else {
	    lists.add(list);
	}

	// Create combined iterator
	// Iterator<T> iterator = Iterators.concat(iterator(), list.iterator());
	// Check if any of them is infinite
	boolean isInf = isInf() || list.isInf();
	// return new HListIterator<>(iterator, isInf);
	return new HListIteratorCombined<>(lists, isInf);

    }

    /**
     * Appends the given string to the end of the current string.
     */
    /*
    public HList<T> concSlow(HList<T> list) {
    
    	// Create combined iterator
    	Iterator<T> iterator = Iterators.concat(iterator(), list.iterator());
    	// Check if any of them is infinite
    	boolean isInf = isInf() || list.isInf();
    
    	return new HListIterator<>(iterator, isInf);
    	// return new HListIterator<>(iterators, isInf);
    
    }
    */
    /**
     * Appends an element to the head of the list.
     * 
     * @param element
     * @param list
     * @return
     */
    public HList<T> cons(T element) {

	// Create a list with one element, and append to the current list
	HList<T> elementList = new HListJava<>(Arrays.asList(element));

	return elementList.conc(this);
	// List<T> newList = new ArrayList<>(size() + 1);
	// newList.add(element);
	// newList.addAll(this);
	//
	// // Using normal list, since operation returns the complete list
	// return new HListJava<>(newList);
    }

    /**
     * Same as List.toString, but without the spaces between commas.
     */
    @Override
    public String toString() {
	Iterator<T> it = iterator();
	if (!it.hasNext()) {
	    return "[]";
	}

	StringBuilder sb = new StringBuilder();
	sb.append('[');
	// Optional<Boolean> isString = Optional.empty();
	for (;;) {
	    T e = it.next();

	    // Add "quotes" if String
	    // if (!isString.isPresent()) {
	    // isString = Optional.of(String.class.isInstance(e));
	    // }

	    // if (isString.get()) {
	    // sb.append("\"");
	    // }
	    sb.append(e == this ? "(this Collection)" : e);
	    // if (isString.get()) {
	    // sb.append("\"");
	    // }
	    if (!it.hasNext()) {
		return sb.append(']').toString();
	    }
	    sb.append(',');
	}
    }

    /**
     * Default implementation re-implements toString().
     * 
     * @return
     */
    @Override
    public String toHaskellString() {
	Iterator<T> it = iterator();
	if (!it.hasNext()) {
	    return "[]";
	}

	StringBuilder sb = new StringBuilder();
	sb.append('[');

	for (;;) {
	    T e = it.next();
	    if (HasHaskellString.class.isInstance(e)) {
		sb.append(((HasHaskellString) e).toHaskellString());
	    } else {
		sb.append(e == this ? "(this Collection)" : e);
	    }

	    if (!it.hasNext()) {
		return sb.append(']').toString();
	    }
	    sb.append(',');
	}
    }

    @Override
    public HList<T> subList(int fromIndex, int toIndex) {
	return new HListJava<>(super.subList(fromIndex, toIndex));
    }

    /**
     * 
     * @return the first element
     */
    public T head() {
	return get(0);
    }

    public HList<T> tail() {
	return subList(1, size());
    }

    public T last() {
	return get(size() - 1);
    }

    public HList<T> init() {
	return subList(0, size() - 1);
    }

    public int length() {
	return size();
    }

    public boolean Null() {
	return isEmpty();
    }

    public HList<T> reverse() {
	return new HListJava<>(Lists.reverse(this));
    }

    public HList<T> take(int amount) {
	// Limit amount to size, if list is not infinite
	if (!isInf() && amount > size()) {
	    amount = size();
	}

	return subList(0, amount);
    }

    public HList<T> drop(int amount) {
	// If infinite list, throw exception
	if (isInf()) {
	    throw new JavaHaskellException("Method 'drop' not supported for infinite lists");
	}

	// Limit amount to size
	if (amount > size()) {
	    amount = size();
	}

	return subList(amount, size());
    }

    public boolean elem(T elem) {
	return contains(elem);
    }

    /**
     * 
     * @return a list that cycles over this current list
     */
    public HList<T> cycle() {
	return new HListCycle<>(this);
    }

    /**
     * zip takes two lists and returns a list of corresponding pairs. If one input list is short, excess elements of the
     * longer list are discarded.
     * 
     * @param list
     * @return
     */
    public <T2> HList<Tuple2<T, T2>> zip(HList<T2> list) {
	return Tuple2List.create(this, list);
    }

    /**
     * Returns true if current size is at least 'i'. Always returns true if 'i' is less or equal than size.
     * 
     * @param i
     * @return
     */
    public boolean isSizeAtLeast(int size) {
	return size <= size();
    }

}
