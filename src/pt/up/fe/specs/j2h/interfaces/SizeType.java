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

package pt.up.fe.specs.j2h.interfaces;

import java.util.Arrays;

import pt.up.fe.specs.j2h.list.HList;

/**
 * Represents a size (e.g., of a list).
 * 
 * @author João Bispo
 *
 */
public enum SizeType {
    /**
     * When the containers has infinite elements.
     */
    INFINITE,
    /**
     * When the size is finite, and is fast to lookup.
     */
    FAST,
    /**
     * When the size is finite, but potentially has iterate over all elements.
     */
    ITERATOR;

    /**
     * 
     * @param isInfinite
     * @return INFINITE if true, FAST otherwise
     */
    public static SizeType fastOrInfinite(boolean isInfinite) {
	if (isInfinite) {
	    return INFINITE;
	}

	return FAST;
    }

    /**
     * 
     * @param isInfinite
     * @return INFINITE if true, ITERATOR otherwise
     */
    public static SizeType iteratorOrInfinite(boolean isInfinite) {
	if (isInfinite) {
	    return INFINITE;
	}

	return ITERATOR;
    }

    public static boolean has(SizeType sizeType, HList<?>... lists) {
	return Arrays.stream(lists)
		.filter(list -> list.getSizeType() == sizeType)
		.findFirst()
		.isPresent();
    }

    /**
     * 1) If any of the lists is Infinite, returns Infinite; 2) Otherwise, if any of the lists is Iterator, returns
     * Iterator 3) Returns fast
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static SizeType combine(HList<?>... lists) {
	if (Arrays.stream(lists).filter(list -> list.isInf()).findFirst().isPresent()) {
	    return INFINITE;
	}

	if (Arrays.stream(lists).filter(list -> list.getSizeType() == SizeType.ITERATOR).findFirst().isPresent()) {
	    return ITERATOR;
	}

	return FAST;

    }

}
