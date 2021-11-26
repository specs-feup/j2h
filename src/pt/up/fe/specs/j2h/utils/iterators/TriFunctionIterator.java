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

package pt.up.fe.specs.j2h.utils.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.utils.TriFunction;

public class TriFunctionIterator<T1, T2, T3, R> implements Iterator<R> {

    private final TriFunction<T1, T2, T3, R> function;
    private final List<TriFunction<T1, T2, T3, Boolean>> predicates;

    private R currentResult;

    private final Iterator<T1> iterator1;
    private final List<T2> list2;
    private Iterator<T2> iterator2;
    private final List<T3> list3;
    private Iterator<T3> iterator3;

    private T1 currentT;
    private T2 currentU;
    private T3 currentV;

    public TriFunctionIterator(TriFunction<T1, T2, T3, R> function, List<T1> list1, List<T2> list2,
	    List<T3> list3, List<TriFunction<T1, T2, T3, Boolean>> predicates) {

	this.function = function;
	this.predicates = predicates;

	this.iterator1 = list1.iterator();
	this.list2 = list2;
	this.iterator2 = list2.iterator();
	this.list3 = list3;
	this.iterator3 = list3.iterator();

	// Bootstrap
	currentT = iterator1.next();
	currentU = iterator2.next();

	// Compute next result
	currentResult = nextResult().orElse(null);
    }

    @Override
    public boolean hasNext() {
	return currentResult != null;
    }

    @Override
    public R next() {
	if (currentResult == null) {
	    throw new JavaHaskellException("No more elements");
	}

	R element = currentResult;

	// Compute next element
	currentResult = nextResult().orElse(null);

	return element;
    }

    private Optional<R> nextResult() {

	boolean hasNext = nextElements();
	if (!hasNext) {
	    return Optional.empty();
	}

	// Cycle until there is an element that passes the predicates
	while (!testPredicates(currentT, currentU, currentV)) {
	    if (!nextElements()) {
		return Optional.empty();
	    }
	}

	// Transform element
	return Optional.of(function.apply(currentT, currentU, currentV));
    }

    private boolean nextElements() {
	// No more elements in both iterators
	if (!iterator1.hasNext() && !iterator2.hasNext() && !iterator3.hasNext()) {
	    return false;
	}

	// If there is no next V, advance U and reinitialize V
	if (!iterator3.hasNext()) {

	    // If there is no next U, advance T and reinitialize U
	    if (!iterator2.hasNext()) {
		currentT = iterator1.next();
		iterator2 = list2.iterator();
	    }

	    this.currentU = iterator2.next();
	    iterator3 = list3.iterator();
	}

	this.currentV = iterator3.next();

	return true;
    }

    private boolean testPredicates(T1 element1, T2 element2, T3 element3) {
	// Test element on all predicates
	for (TriFunction<T1, T2, T3, Boolean> predicate : predicates) {
	    // Return false if any of the predicates fail
	    if (!predicate.apply(element1, element2, element3)) {
		return false;
	    }
	}

	return true;
    }
}
