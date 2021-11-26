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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;

public class HFunction<T, R> implements Function<T, R> {

    private final Map<Predicate<T>, Function<T, R>> functions;

    public HFunction() {
	this.functions = new LinkedHashMap<>(); // To maintain order
    }

    public static <T, R> HFunction<T, R> create() {
	return new HFunction<>();
    }

    @Override
    public R apply(T t) {
	// Go over all functions until one of the predicates apply
	for (Entry<Predicate<T>, Function<T, R>> entry : functions.entrySet()) {
	    if (!entry.getKey().test(t)) {
		continue;
	    }

	    // Passed predicate, apply function
	    return entry.getValue().apply(t);
	}

	throw new JavaHaskellException("Non-exhaustive patterns, could not find a match for input '" + t + "'");
    }

    /**
     * Adds a function that executes if the input is the same as the given object.
     * 
     * @param pattern
     * @param function
     * @return
     */
    public HFunction<T, R> add(T pattern, Function<T, R> function) {
	// Add predicate that tests for equality over the given object
	functions.put(t -> t.equals(pattern), function);

	return this;
    }

    /**
     * Default action.
     * 
     * @param function
     * @return
     */
    public HFunction<T, R> add(Function<T, R> function) {
	// The predicate always returns true
	functions.put(t -> true, function);

	return this;
    }

    public HFunction<T, R> add(Predicate<T> pattern, Function<T, R> function) {
	// Add predicate that tests for equality over the given object
	functions.put(pattern, function);

	return this;
    }
}
