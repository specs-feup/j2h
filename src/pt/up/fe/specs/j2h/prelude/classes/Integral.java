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

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.data.HInt;
import pt.up.fe.specs.j2h.prelude.data.HInteger;

/**
 * Integral numbers, supporting integer division.
 * 
 * @author JoaoBispo
 *
 */
public interface Integral<N extends Number, T extends Integral<N, T>> extends Real<N, T>, HEnum<T> {

    /**
     * Integer division truncated toward zero.
     * 
     * @param a
     * @return
     */
    default T quot(T a) {
	return quot(a.getNumber());
    }

    /**
     * Convenience method that accepts Numbers.
     * 
     * @param a
     * @return
     */
    T quot(Number a);

    /**
     * Integer remainder, equivalent to Java %.
     * 
     * <p>
     * (x `quot` y)*y + (x `rem` y) == x
     * 
     * @param a
     * @return
     */
    default T rem(T a) {
	return rem(a.getNumber());
    }

    /**
     * Convenience method that accepts Numbers.
     * 
     * @param a
     * @return
     */
    T rem(Number a);

    /**
     * Integer division truncated toward negative infinity.
     * 
     * @param a
     * @return
     */
    default T div(T a) {
	return div(a.getNumber());
    }

    /**
     * Convenience method that accepts Numbers.
     * 
     * @param a
     * @return
     */
    T div(Number a);

    /**
     * Integer modulus.
     * 
     * <p>
     * (x `div` y)*y + (x `mod` y) == x
     * 
     * @param a
     * @return
     */
    default T mod(T a) {
	return mod(a.getNumber());
    }

    T mod(Number a);

    /**
     * Simultaneous quot and rem.
     * 
     * TODO: Implement after doing tuples.
     * 
     * @param a
     * @return
     */
    // Integral quotRem(Integral a);

    /**
     * Simultaneous div and mod.
     * 
     * TODO: Implement after doing tuples.
     * 
     * @param a
     * @return
     */
    // Integral divMod(Integral a);

    /**
     * Converts the current number to an HInteger.
     * 
     * @return
     */
    default HInteger toInteger() {
	return new HInteger(BigInteger.valueOf(getNumber().longValue()));
    }

    /*
     *  HEnum implementations(non-Javadoc)
     * @see pt.up.fe.specs.j2h.types.interfaces.HEnum#toEnum(pt.up.fe.specs.j2h.types.HInt)
     */

    /**
     * 
     */
    @Override
    default T toEnum(Number a) {
	return fromNumber(a);
    }

    @Override
    default HInt fromEnum() {
	return H.i(getNumber().intValue());
    }

    /*
     * HEnum overloads for Number inputs
     */
    default HList<T> enumFromThen(Number second) {
	return enumFromThen(fromNumber(second));
    }

    default HList<T> enumFromTo(Number last) {
	return enumFromTo(fromNumber(last));
    }

    default HList<T> enumFromThenTo(Number second, Number last) {
	return enumFromThenTo(fromNumber(second), fromNumber(last));
    }

    /*
     * Additional functions
     */
    default boolean odd() {
	// After rem(2), value can only be 0 or 1, so can be safely cast to int
	return rem(2).getNumber().intValue() != 0;
    }

    /*
     * Additional functions
     */
    default boolean even() {
	return !odd();
    }
}
