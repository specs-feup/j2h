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

package pt.up.fe.specs.j2h.tutorial;

import static pt.up.fe.specs.j2h.TestUtils.*;

import java.util.function.Predicate;

import org.junit.Test;

import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.j2h.prelude.data.HInt;
import pt.up.fe.specs.j2h.prelude.data.HInteger;
import pt.up.fe.specs.j2h.utils.HFunction;

public class SyntaxInFunctions {

    @Test
    public void testFunctions() {

	test("LUCKY NUMBER SEVEN", lucky().apply(7));

	// Factorial for Java Long
	test("3628800", factorialLong().apply(10l));
	// Factorial for Haskell Integer
	test("3628800", factorialHInteger().apply(new HInteger(10)));
	// Generic factorial for Haskell Integral, called with Haskell Integer
	test("3628800", factorialIntegral(HInteger.class).apply(new HInteger(10)));
	// Generic factorial for Haskell Integral, called with Haskell Int
	test("3628800", factorialIntegral(HInt.class).apply(HInt.create(10)));

    }

    public static HFunction<Integer, String> lucky() {
	return new HFunction<Integer, String>()
		.add(7, x -> "LUCKY NUMBER SEVEN")
		.add(x -> "Sorry, you are out of luck");
    }

    public static HFunction<Long, Long> factorialLong() {
	HFunction<Long, Long> factorial = HFunction.create();
	factorial.add(0l, x -> 1l)
		.add(x -> x * factorial.apply(x - 1));

	return factorial;
    }

    public static HFunction<HInteger, HInteger> factorialHInteger() {
	HFunction<HInteger, HInteger> factorial = HFunction.create();
	factorial.add(HInteger.ZERO, x -> HInteger.ONE)
		.add(x -> x.times(factorial.apply(x.minus(HInteger.ONE))));

	return factorial;
    }

    public static <N extends Number, I extends Integral<N, I>> HFunction<I, I> factorialIntegral(Class<I> aClass) {
	return factorialIntegral();
    }

    private static <N extends Number, I extends Integral<N, I>> HFunction<I, I> factorialIntegral() {
	HFunction<I, I> factorial = HFunction.create();
	// x == 0, return 1
	factorial.add((Predicate<I>) x -> x.zero().equals(x), x -> x.one())
		// return x * factorial(x-1)
		.add(x -> x.times(factorial.apply(x.minus(x.one()))));

	return factorial;
    }

}
