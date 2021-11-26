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

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterators;

/**
 * Utility methods to be used by package list.
 * 
 * @author JoaoBispo
 *
 */
public interface ListUtils {

    /**
     * 
     * @return the size of an infinite list
     */
    static int infListSize() {
	// Hack for certain things to work, such as concatenation of finite lists with infinite list
	// This way it is not needed to re-implement iterator, to always return "true" on "hasNext"
	return Integer.MAX_VALUE;
	// throw new JavaHaskellException("'size' is not defined for infinite lists");
    }

    static <T extends Comparable<T>> int compareHeads(HList<T> list1, HList<T> list2) {
	// If both have no heads, they are equal
	if (list1.isEmpty() && list2.isEmpty()) {
	    return 0;
	}

	// If current has no head but next has head, current is considered smaller
	if (list1.isEmpty() && !list2.isEmpty()) {
	    return -1;
	}

	// If current has head but next has not head, current is considered bigger
	if (!list1.isEmpty() && list2.isEmpty()) {
	    return 1;
	}

	return list1.get(0).compareTo(list2.get(0));
    }

    /**
     * Converts lists into a single, combined iterator.
     * 
     * @param lists
     * @return
     */
    static <T> Iterator<T> combineIterators(List<? extends List<T>> lists) {
	// Transform lists into iterator array
	Iterator<T>[] iterators = lists.stream()
		.map(list -> list.iterator())
		.toArray(size -> new Iterator[size]);

	return Iterators.concat(iterators);
    }
}
