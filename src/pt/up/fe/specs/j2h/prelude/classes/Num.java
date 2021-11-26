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

import pt.up.fe.specs.j2h.prelude.data.HInteger;

/**
 * Basic numeric class.
 * 
 * @author JoaoBispo
 *
 */
public interface Num<T extends Num<T>> extends NumStatic<T>, Eq<T> {

    T plus(T a);

    T minus(T a);

    T times(T a);

    T negate();

    T abs();

    /**
     * Sign of a number.
     * 
     * @return For real numbers, the signum is either -1 (negative), 0 (zero) or 1 (positive)
     */
    T signum();

    /**
     * Creates a type from an HInteger.
     * 
     * @param a
     * @return
     */
    T fromInteger(HInteger a);

    /**
     * Default implementation that uses BigInteger.
     * 
     * @param a
     * @return
     */
    default T fromInteger(int a) {
	return fromInteger(new HInteger(BigInteger.valueOf(a)));
    }

    /*
     * Int specializations
     */

    default T plus(int a) {
	return plus(fromInteger(a));
    }

    default T minus(int a) {
	return minus(fromInteger(a));
    }

    default T times(int a) {
	return times(fromInteger(a));
    }

}
