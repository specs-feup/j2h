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

/**
 * The Ord class is used for totally ordered datatypes.
 * 
 * @author JoaoBispo
 *
 */
public interface Ord<T extends Ord<T>> extends Eq<T>, Comparable<T> {

    default int compare(T a) {
	return compareTo(a);
    }

    /**
     * Less than.
     * 
     * @param compareTo
     * @return
     */
    default boolean lt(T a) {
	if (compare(a) < 0) {
	    return true;
	}

	return false;
    }

    /**
     * Less or equal than.
     */
    default boolean leq(T a) {
	if (compare(a) <= 0) {
	    return true;
	}

	return false;
    }

    /**
     * Greater than.
     * 
     * @return
     */
    default boolean gt(T a) {
	if (compare(a) > 0) {
	    return true;
	}

	return false;
    }

    /**
     * Greater or equal than.
     * 
     * @return
     */
    default boolean geq(T a) {
	if (compare(a) >= 0) {
	    return true;
	}

	return false;
    }

    /**
     * Equal than.
     * 
     * @return
     */
    default boolean eq(T a) {
	if (compare(a) == 0) {
	    return true;
	}

	return false;
    }

    @SuppressWarnings("unchecked")
    default T maximum(T a) {
	if (geq(a)) {
	    return (T) this;
	}

	return a;
    }

    @SuppressWarnings("unchecked")
    default T minimum(T a) {
	if (leq(a)) {
	    return (T) this;
	}

	return a;
    }
}
