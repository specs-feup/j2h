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

package pt.up.fe.specs.j2h.prelude.classes;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;

/**
 * The Bounded class is used to name the upper and lower limits of a type. Ord is not a superclass of Bounded since
 * types that are not totally ordered may also have upper and lower bounds.
 * 
 * @author JoaoBispo
 *
 */
public interface Bounded<T> {

    T minBound();

    T maxBound();

    static Number minBound(Number number) {
	if (number instanceof Integer) {
	    return Integer.MIN_VALUE;
	}

	if (number instanceof Long) {
	    return Long.MIN_VALUE;
	}

	if (number instanceof Double) {
	    return Double.MIN_VALUE;
	}

	throw new JavaHaskellException("Not implement yet for Number class '"
		+ number.getClass().getSimpleName() + "' ");
    }

    static Number maxBound(Number number) {
	if (number instanceof Integer) {
	    return Integer.MAX_VALUE;
	}

	if (number instanceof Long) {
	    return Long.MAX_VALUE;
	}

	if (number instanceof Double) {
	    return Double.MAX_VALUE;
	}

	throw new JavaHaskellException("Not implement yet for Number class '"
		+ number.getClass().getSimpleName() + "' ");
    }
}
