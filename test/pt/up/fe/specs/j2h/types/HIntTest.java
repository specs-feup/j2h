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

package pt.up.fe.specs.j2h.types;

import static pt.up.fe.specs.j2h.TestUtils.*;

import java.math.BigInteger;

import org.junit.Test;

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.j2h.prelude.data.HInt;
import pt.up.fe.specs.j2h.prelude.data.HInteger;

public class HIntTest {

    @Test
    public void testHInt() {
	test(3, H.i(1).plus(2));
	test(3, H.i(1).plus(2).getNumber().intValue());
	test(3, H.i(1).plus(H.i(2)));
	test(-1, H.i(2).minus(3));
	test(35, H.i(5).times(7));

	test(-5, H.i(5).negate());
	test(7, H.i(-7).negate());
	test(0, H.i(0).negate());

	test(4, H.i(4).abs());
	test(4, H.i(-4).abs());
	test(0, H.i(0).abs());

	test(1, H.i(11).signum());
	test(-1, H.i(-13).signum());
	test(0, H.i(0).signum());

	test(10, HInt.STATIC.fromInteger(BigInteger.TEN));
	test(10, HInt.STATIC.fromInteger(new HInteger(BigInteger.TEN)));

	test(1, H.i(10).compare(9));
	test(-1, H.i(10).compare(11));
	test(0, H.i(10).compare(10));

	test(true, H.i(10).lt(11));
	test(false, H.i(10).lt(10));

	test(true, H.i(10).leq(10));
	test(false, H.i(10).leq(9));

	test(true, H.i(10).gt(9));
	test(false, H.i(10).gt(10));

	test(true, H.i(10).geq(10));
	test(false, H.i(10).geq(11));

	test(true, H.i(10).eq(10));
	test(false, H.i(10).eq(9));

	test(10, H.i(10).maximum(H.i(9)));
	test(10, H.i(10).maximum(9));
	test(10, H.i(10).maximum(10));
	test(11, H.i(10).maximum(11));

	test(9, H.i(10).minimum(9));
	test(10, H.i(10).minimum(10));
	test(10, H.i(10).minimum(11));

	test(Long.MAX_VALUE, HInt.STATIC.maxBound());
	test(Long.MIN_VALUE, HInt.STATIC.minBound());

	// (x `quot` y)*y + (x `rem` y) == x
	test(3, quotRemTest(H.i(3), H.i(5)));
	test(3, quotRemTest(H.i(3), H.i(-5)));
	test(-3, quotRemTest(H.i(-3), H.i(5)));
	test(-3, quotRemTest(H.i(-3), H.i(-5)));

	// (x `div` y)*y + (x `mod` y) == x
	test(3, divModTest(H.i(3), H.i(5)));
	test(-3, divModTest(H.i(-3), H.i(5)));

    }

    /**
     * (x `quot` y)*y + (x `rem` y)
     * 
     * @param x
     * @param y
     */
    static <T extends Integral<?, T>> T quotRemTest(T x, T y) {
	return x.quot(y).times(y).plus(x.rem(y));
    }

    /**
     * (x `div` y)*y + (x `mod` y)
     * 
     * @param x
     * @param y
     */
    static <T extends Integral<?, T>> T divModTest(T x, T y) {
	return x.div(y).times(y).plus(x.mod(y));
    }

}
