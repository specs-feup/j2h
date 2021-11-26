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

package pt.up.fe.specs.j2h;

import java.math.BigInteger;

import pt.up.fe.specs.j2h.prelude.classes.Integral;

/**
 * Helper for class H.
 * 
 * @author JoaoBispo
 *
 */
class J2HUtils {

    static int getLimit(int start, int end, int step) {
	int total = end - start + step;
	return Math.abs(total / step);
    }

    static double getLimit(double start, double end, double step) {
	double total = end - start + step;
	return Math.abs(total / step);
    }

    static BigInteger getLimit(BigInteger start, BigInteger end, BigInteger step) {
	BigInteger total = end.subtract(start).add(step);
	return total.divide(step).abs();
    }

    static <N extends Number, T extends Integral<N, T>> T getLimit(T start, T end,
	    T step) {
	T total = end.minus(start).plus(step);
	return total.div(step).abs();
    }

    static int getSignal(int start, int end) {
	if (start > end) {
	    return -1;
	}

	return 1;
    }

}
