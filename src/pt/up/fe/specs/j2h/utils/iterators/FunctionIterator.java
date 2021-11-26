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
import java.util.function.Function;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;

public class FunctionIterator<T, R> implements Iterator<R> {

    private final Function<T, R> function;
    private final List<Function<T, Boolean>> predicates;

    private Optional<R> currentResult;
    private final Iterator<T> iterator;

    public FunctionIterator(Function<T, R> function, List<T> list, List<Function<T, Boolean>> predicates) {
	this.function = function;
	this.predicates = predicates;

	iterator = list.iterator();

	// Compute next result
	currentResult = nextResult();
    }

    @Override
    public boolean hasNext() {
	return currentResult.isPresent();
    }

    @Override
    public R next() {
	if (!currentResult.isPresent()) {
	    throw new JavaHaskellException("No more elements");
	}

	R element = currentResult.get();

	// Compute next element
	currentResult = nextResult();

	return element;
    }

    private Optional<R> nextResult() {
	if (!iterator.hasNext()) {
	    return Optional.empty();
	}

	T element = iterator.next();

	// Cycle until there is an element that passes the predicates
	while (!testPredicates(element)) {
	    // If there are not more elements, return empty
	    if (!iterator.hasNext()) {
		return Optional.empty();
	    }

	    element = iterator.next();
	}

	// Transform element
	return Optional.of(function.apply(element));
    }

    private boolean testPredicates(T element) {
	// Test element on all predicates
	for (Function<T, Boolean> predicate : predicates) {
	    // Return false if any of the predicates fail
	    if (!predicate.apply(element)) {
		return false;
	    }
	}

	return true;
    }
}
