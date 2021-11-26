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

package pt.up.fe.specs.j2h.list.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;
import pt.up.fe.specs.j2h.list.util.HListJava;

/**
 * Java List implementation of HList.
 */
abstract class HListIterator<T> extends HList<T> {

    private final Iterator<T> iterator;
    private final List<T> buffer;

    // private final int iteratorSize;

    /**
     * 
     * @param iterator
     * @param isInf
     * @param iteratorSize
     */
    protected HListIterator(Iterator<T> iterator, boolean isInf) {
	super(SizeType.iteratorOrInfinite(isInf));
	this.iterator = iterator;
	this.buffer = new ArrayList<>();
    }

    @Override
    public T get(int index) {
	// Ensure buffer has enough elements
	fill(index);
	return buffer.get(index);
    }

    @Override
    public int size() {
	if (isInf()) {
	    return ListUtils.infListSize();
	}

	fillAll();
	return buffer.size();
    }

    /**
     * Fills the buffer so that it contains at least as many elements as needed to fetch the given index.
     * 
     * @param index
     */
    private void fill(int index) {
	// If index is less than buffer size, then we already have enough elements
	if (index < buffer.size()) {
	    return;
	}

	// // If there are not more elements, just return
	// if (!iterator.hasNext()) {
	// return;
	// // throw new JavaHaskellException("Index '" + index + "' is out of bounds");
	// }

	// Fetch as many elements as needed to fill the buffer
	int numElementsToFetch = index - buffer.size() + 1;
	for (int i = 0; i < numElementsToFetch; i++) {
	    // If there are no more elements to fetch, return
	    if (!iterator.hasNext()) {
		return;
	    }

	    buffer.add(iterator.next());
	}
    }

    /**
     * Walks over all elements of the iterator, ensuring the buffer contains the complete stream.
     */
    private void fillAll() {
	while (iterator.hasNext()) {
	    buffer.add(iterator.next());
	}
    }

    @Override
    public HList<T> take(int amount) {
	// We have to have care when calling size, since we can have infinite iterators
	// Ensure there are enough elements. Subtracting 1 because fill accepts an index
	fill(amount);

	// Limit amount to size
	if (amount > buffer.size()) {
	    amount = buffer.size();
	}

	return new HListJava<>(buffer.subList(0, amount));
    }

    @Override
    public Iterator<T> iterator() {
	return super.iterator();
    }

    @Override
    public boolean isSizeAtLeast(int size) {
	// Adjust size to index
	fill(size - 1);
	return size <= buffer.size();
    }

}
