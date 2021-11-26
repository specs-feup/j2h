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
import java.util.function.BiFunction;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;

public class BiFunctionIterator<T, U, R> implements Iterator<R> {

    private final BiFunction<T, U, R> function;
    private final List<BiFunction<T, U, Boolean>> predicates;

    private R currentResult;

    private final Iterator<T> iterator1;
    private final List<U> list2;
    private Iterator<U> iterator2;

    private T currentT;
    private U currentU;

    public BiFunctionIterator(BiFunction<T, U, R> function, List<T> list1, List<U> list2,
	    List<BiFunction<T, U, Boolean>> predicates) {

	this.function = function;
	this.predicates = predicates;

	this.iterator1 = list1.iterator();
	this.list2 = list2;
	this.iterator2 = list2.iterator();

	// Bootstrap
	currentT = iterator1.next();

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
	while (!testPredicates(currentT, currentU)) {
	    if (!nextElements()) {
		return Optional.empty();
	    }
	}

	// Transform element
	return Optional.of(function.apply(currentT, currentU));
    }

    private boolean nextElements() {
	// No more elements in both iterators
	if (!iterator1.hasNext() && !iterator2.hasNext()) {
	    return false;
	}

	// If there is no next U, advance T and reinitialize U
	if (!iterator2.hasNext()) {
	    currentT = iterator1.next();
	    iterator2 = list2.iterator();
	}

	this.currentU = iterator2.next();

	return true;
    }

    private boolean testPredicates(T element1, U element2) {
	// Test element on all predicates
	for (BiFunction<T, U, Boolean> predicate : predicates) {
	    // Return false if any of the predicates fail
	    if (!predicate.apply(element1, element2)) {
		return false;
	    }
	}

	return true;
    }
}
