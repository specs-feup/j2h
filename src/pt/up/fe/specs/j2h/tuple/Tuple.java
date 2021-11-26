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
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import pt.up.fe.specs.j2h.interfaces.HasHaskellString;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.classes.Bounded;

public abstract class Tuple implements Bounded<Tuple>, HasHaskellString {

    protected abstract List<Object> getElements();

    @Override
    public String toString() {
	return getElements().stream()
		.map(element -> element.toString())
		.collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public String toHaskellString() {
	List<String> strings = new ArrayList<>();
	for (Object element : getElements()) {
	    if (element instanceof HasHaskellString) {
		strings.add(((HasHaskellString) element).toHaskellString());
	    } else {
		strings.add(element.toString());
	    }
	}

	return strings.stream()
		.collect(Collectors.joining(",", "(", ")"));
    }

    /**
     * 
     * 
     * @param lists
     * @return true if all lists are infinite, and false otherwise
     */
    static boolean isInfinite(HList<?>... lists) {
	// Find first list that is not infinite
	boolean hasFiniteList = Arrays.stream(lists)
		.filter(list -> !list.isInf())
		.findFirst().isPresent();

	if (hasFiniteList) {
	    return false;
	}

	return true;
    }

    /**
     * 
     * @param list
     * @return
     */
    /*
    static <T> Optional<T> getElement(HList<T> list, int index) {
    // If list has infinite size, just return element
    if (list.isInf()) {
        return Optional.of(list.get(index));
    }
    
    // List is not infinite, can "safely" consult size (depending on the implementation, might load all values of a
    // list to memory)
    int size = list.size();
    
    return null;
    }
    */

    /**
     * The minimum of all list sizes.
     * 
     * @param list
     * @return
     */
    static OptionalInt calculateSize(HList<?>... lists) {
	return Arrays.stream(lists)
		// Remove all infinite lists
		.filter(list -> !list.isInf())
		.mapToInt(list -> list.size())
		.min();
    }
}
