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

package pt.up.fe.specs.j2h.tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.prelude.classes.Bounded;

/**
 * Utility methods for Tuples.
 * 
 * @author JoaoBispo
 *
 */
public class TupleUtils {

    /**
     * Checks if all elements of the tuple implement the Bounded interface. If true, it does nothing, otherwise throws
     * an exception.
     * 
     * @param tuple
     */
    // static void checkBounded(Tuple tuple) {
    // // Get elements
    // List<Object> elements = tuple.getElements();
    //
    // // Find first element that does not implement neither Bounded and Number
    // OptionalInt firstNonBoundedValue = IntStream.range(0, elements.size())
    // .filter(i -> !Bounded.class.isInstance(elements.get(i)) && !Number.class.isInstance(elements.get(i)))
    // .findFirst();
    //
    // // Throw exception if an index is present
    // if (firstNonBoundedValue.isPresent()) {
    // throw new JavaHaskellException(
    // "Value at index '" + firstNonBoundedValue.getAsInt() + "' of '" + tuple.getClass().getSimpleName()
    // + "' does not implement Bounded");
    // }
    // }

    static List<Object> minBound(Tuple tuple) {
	return minMaxBound(tuple, b -> b.minBound(), Bounded::minBound);
    }

    static List<Object> maxBound(Tuple tuple) {
	return minMaxBound(tuple, b -> b.maxBound(), Bounded::maxBound);
    }

    private static List<Object> minMaxBound(Tuple tuple, Function<Bounded<?>, Object> bFunction,
	    Function<Number, Number> nFunction) {

	List<Object> bounds = new ArrayList<>();

	for (Object element : tuple.getElements()) {
	    if (element instanceof Bounded) {
		bounds.add(bFunction.apply((Bounded<?>) element));
		continue;
	    }

	    if (element instanceof Number) {
		bounds.add(nFunction.apply((Number) element));
		continue;
	    }

	    throw new JavaHaskellException(
		    "Value of type '" + element.getClass().getSimpleName() + "' of '" + tuple.getClass().getSimpleName()
			    + "' does not implement Bounded");

	}

	return bounds;
    }
}
