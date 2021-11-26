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

import pt.up.fe.specs.j2h.tuple.Tuple2;

/**
 * Extracting components of fractions.
 * 
 * 
 * @author JoaoBispo
 *
 * @param <N>
 * @param <T>
 */
public interface RealFrac<N extends Number, NI extends Number, I extends Integral<NI, I>, T extends RealFrac<N, NI, I, T>>
	extends Real<N, T>, Fractional<T> {

    /**
     * The function properFraction returns a pair (n,f) such that 'this' = n+f, and:
     * 
     * <p>
     * n is an integral number with the same sign as 'this'; and <br>
     * f is a fraction with the same type and sign as 'this', and with absolute value less than 1.
     * <p>
     * The default definitions of the ceiling, floor, truncate and round functions are in terms of properFraction.
     * 
     * @return
     */
    Tuple2<I, T> properFraction();

    /**
     * 
     * @return the integer nearest x between zero and x
     */
    default I truncate() {
	return properFraction().fst();
    }

    /**
     * 
     * @return the nearest integer to x; the even integer if x is equidistant between two integers
     */
    default I round() {
	Tuple2<I, T> pair = properFraction();
	I n = pair.fst();
	T r = pair.snd();

	I m = r.lt(0) ? n.minus(1) : n.plus(1);
	// if (r.lt(0)) {
	// m = n.minus(1);
	// } else {
	// m = n.plus(1);
	// }

	T signum = r.abs().minus(0.5).signum();

	if (signum.eq(-1)) {
	    return n;
	}

	if (signum.eq(1)) {
	    return m;
	}

	assert signum.eq(0);

	return n.even() ? n : m;
	// if (n.even()) {
	// return n;
	// }
	//
	// return m;
    }

    /**
     * 
     * @return the least integer not less than x
     */
    default I ceiling() {
	Tuple2<I, T> pair = properFraction();
	I n = pair.fst();
	T r = pair.snd();

	return r.gt(0) ? n.plus(1) : n;
    }

    /**
     * 
     * @return the greatest integer not greater than x
     */
    default I floor() {
	Tuple2<I, T> pair = properFraction();
	I n = pair.fst();
	T r = pair.snd();

	return r.lt(0) ? n.minus(1) : n;
    }
}
