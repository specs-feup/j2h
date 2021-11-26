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

import java.math.BigInteger;

import pt.up.fe.specs.j2h.prelude.JavaNum;
import pt.up.fe.specs.j2h.prelude.data.HInteger;
import pt.up.fe.specs.j2h.prelude.data.Rational;

/**
 * 
 * @author JoaoBispo
 *
 */
public interface Real<N extends Number, T extends Real<N, T>> extends RealStatic<N, T>, JavaNum<N>, Num<T>, Ord<T> {

    /*
     * Haskell methods
     */

    /**
     * 
     * @return
     */
    Rational toRational();

    @Override
    default T plus(T a) {
	return plus(a.getNumber());
    }

    T plus(N n);

    @Override
    default T minus(T a) {
	return minus(a.getNumber());
    }

    T minus(Number n);

    @Override
    default T times(T a) {
	return times(a.getNumber());
    }

    T times(N n);

    T fromNumber(Number a);

    int compareTo(N a);

    @Override
    default int compareTo(T o) {
	return compareTo(o.getNumber());
    }

    default int compare(Number a) {
	return compareTo(fromNumber(a));
    }

    /**
     * Less than.
     * 
     * @param compareTo
     * @return
     */
    default boolean lt(Number a) {
	if (compare(a) < 0) {
	    return true;
	}

	return false;
    }

    /**
     * Less or equal than.
     */
    default boolean leq(Number a) {
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
    default boolean gt(Number a) {
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
    default boolean geq(Number a) {
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
    default boolean eq(Number a) {
	if (compare(a) == 0) {
	    return true;
	}

	return false;
    }

    @SuppressWarnings("unchecked")
    default T maximum(Number a) {
	if (geq(a)) {
	    return (T) this;
	}

	return fromNumber(a);
    }

    @SuppressWarnings("unchecked")
    default T minimum(Number a) {
	if (leq(a)) {
	    return (T) this;
	}

	return fromNumber(a);
    }

    /**
     * Converts an HInteger to this type.
     */
    @Override
    default T fromInteger(HInteger a) {
	return fromNumber(a.getNumber());
    }

    /**
     * Helper method that receives a BigInteger.
     * 
     * @param a
     * @return
     */
    @Override
    default T fromInteger(BigInteger a) {
	return fromNumber(a);
    }

}
