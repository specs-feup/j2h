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

import java.util.List;

import pt.up.fe.specs.j2h.interfaces.CombinedList;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;

public class HListIteratorCombined<T> extends HListIterator<T> implements CombinedList<T> {

    private final List<HList<T>> combinedLists;

    private final int combinedSize;
    // HListIteratorCombined(HList<T> elements, boolean isInf) {
    // this(Arrays.asList(elements), isInf);
    // }

    public HListIteratorCombined(List<HList<T>> lists, boolean isInf) {
	super(ListUtils.combineIterators(lists), isInf);

	this.combinedLists = lists;
	this.combinedSize = getCombinedSize(lists);
    }

    @Override
    public List<HList<T>> getLists() {
	return combinedLists;
    }

    /**
     * Optimized implementation of size, which does not force iterating over all elements.
     */
    @Override
    public int size() {
	if (isInf()) {
	    return super.size();
	}

	return combinedSize;
    }

    /**
     * The sum of all list sizes, or -1 if any of the lists are infinite.
     * 
     * @param list
     * @return
     */
    private static <T> int getCombinedSize(List<HList<T>> lists) {
	// Check if there is any infinite list
	boolean existsInfList = lists.stream()
		.filter(list -> list.isInf())
		.findFirst()
		.isPresent();

	if (existsInfList) {
	    return -1;
	}

	return lists.stream().mapToInt(list -> list.size()).sum();
    }
}
