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

package pt.up.fe.specs.j2h.utils;

import static pt.up.fe.specs.j2h.utils.NumOp.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;
import pt.up.fe.specs.j2h.list.util.HListCycle;
import pt.up.fe.specs.util.SpecsLogs;

/**
 * Operations over lists.
 */
public interface ListOp {

    /**
     * Sum that returns a result of the same type as the element of the list.
     * 
     * @param list
     * @param elementClass
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T extends Number> T sum(List<T> list, Class<T> elementClass) {
	T identity = zero(elementClass);

	// Return zero
	if (list.isEmpty()) {
	    return identity;
	}

	// Efficient implementations
	if (elementClass.equals(Integer.class)) {
	    int result = list.stream()
		    .mapToInt(element -> ((Integer) element).intValue())
		    .sum();

	    return (T) Integer.valueOf(result);
	}

	if (elementClass.equals(Double.class)) {
	    double result = list.stream()
		    .mapToDouble(element -> ((Double) element).doubleValue())
		    .sum();

	    return (T) Double.valueOf(result);
	}

	if (elementClass.equals(Long.class)) {
	    long result = list.stream()
		    .mapToLong(element -> ((Long) element).longValue())
		    .sum();

	    return (T) Long.valueOf(result);
	}

	// Inefficient fall-back
	SpecsLogs.msgInfo("sum: efficient implementation for type '" + elementClass.getName()
		+ "' not done yet, using fall-back implementation");
	return list.stream().reduce(zero(elementClass), NumOp::add);
    }

    /**
     * Product that returns a result of the same type as the element of the list.
     * 
     * @param list
     * @param elementClass
     * @return
     */
    static <T extends Number> T product(HList<T> list, Class<T> elementClass) {
	// Inefficient fall-back
	// LoggingUtils.msgInfo("Efficient implementation for type '" + elementClass.getName()
	// + "' not done yet, using fall-back implementation");
	return list.stream().reduce(NumOp.one(elementClass), NumOp::mult);
    }

    static <T> boolean elem(T elem, HList<T> list) {
	return list.elem(elem);
    }

    static <T> HList<T> repeat(T element) {
	return new HListCycle<>(Arrays.asList(element));
    }

    static <T> HList<T> replicate(int amount, T element) {
	return H.listT(element).cycle().take(amount);
    }

    static <T extends Comparable<T>> int compare(HList<T> list1, HList<T> list2) {
	// Compare heads
	int result = ListUtils.compareHeads(list1, list2);

	// Return the result if diff than 0, or if any of the lists are empty
	if (result != 0 || list1.isEmpty() || list2.isEmpty()) {
	    return result;
	}

	// Continue to the next element
	return compare(list1.subList(1, list1.size()), list2.subList(1, list2.size()));
    }

    /**
     * Less than.
     * 
     * @param compareTo
     * @return
     */
    static <T extends Comparable<T>> boolean lt(HList<T> list1, HList<T> list2) {
	if (compare(list1, list2) < 0) {
	    return true;
	}

	return false;
    }

    /**
     * Less or equal than.
     */
    static <T extends Comparable<T>> boolean leq(HList<T> list1, HList<T> list2) {
	if (compare(list1, list2) <= 0) {
	    return true;
	}

	return false;
    }

    /**
     * Greater than.
     * 
     * @return
     */
    static <T extends Comparable<T>> boolean gt(HList<T> list1, HList<T> list2) {
	if (compare(list1, list2) > 0) {
	    return true;
	}

	return false;
    }

    /**
     * Greater or equal than.
     * 
     * @return
     */
    static <T extends Comparable<T>> boolean geq(HList<T> list1, HList<T> list2) {
	if (compare(list1, list2) >= 0) {
	    return true;
	}

	return false;
    }

    /**
     * Equal than.
     * 
     * @return
     */
    static <T extends Comparable<T>> boolean eq(HList<T> list1, HList<T> list2) {
	if (compare(list1, list2) == 0) {
	    return true;
	}

	return false;
    }

    static <T extends Comparable<T>> T maximum(HList<T> list) {
	return Collections.max(list);
    }

    static <T extends Comparable<T>> T minimum(HList<T> list) {
	return Collections.min(list);
    }
}
