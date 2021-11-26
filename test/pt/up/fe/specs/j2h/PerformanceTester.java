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
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.junit.Test;

import pt.up.fe.specs.j2h.functions.Sum;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.data.HInteger;
import pt.up.fe.specs.j2h.tutorial.SyntaxInFunctions;
import pt.up.fe.specs.j2h.utils.ListOp;
import pt.up.fe.specs.j2h.utils.NumOp;
import pt.up.fe.specs.util.SpecsStrings;
import pt.up.fe.specs.util.utilities.heapwindow.HeapWindow;

public class PerformanceTester {

    // @Test
    public void concat() {
	HList<?> rightLazy = H.range(1, 50000000).conc(H.range(1, 50000000)).zip(H.range(1, 5));
	System.out.println("List size:" + rightLazy.size());
	System.out.println("List type:" + rightLazy.getSizeType());

	HList<?> leftLazy = H.range(1, 5).zip(H.range(1, 50000000).conc(H.range(1, 50000000)));
	System.out.println("List size:" + leftLazy.size());
	System.out.println("List type:" + leftLazy.getSizeType());
	// length(zip ([1.. 50000000] ++ [1.. 50000000]) [1.. 50000000])
    }

    @Test
    public void testObjectVsNative() {
	// int bound = 100000000;
	int bound = 100000;
	Integer[] values = new Integer[bound];
	for (int i = 0; i < bound; i++) {
	    values[i] = i;
	}

	HList<Integer> list = H.listT(values);

	long nativeStart = System.nanoTime();
	Integer nativeResult = null;
	for (int i = 0; i < 1000; i++) {
	    // nativeResult = ListOp.sum(list, Integer.class);
	    Integer[] values2 = new Integer[bound];
	    for (int index = 0; index < bound; index++) {
		values2[index] = index + 1;
	    }
	    nativeResult = Arrays.stream(values2)
		    .mapToInt(element -> ((Integer) element).intValue())
		    .sum();
	}

	long nativeEnd = System.nanoTime();

	long objectStart = System.nanoTime();
	Integer objectResult = null;
	for (int i = 0; i < 1000; i++) {
	    objectResult = list.stream().reduce(Integer.valueOf(0), NumOp::add);
	}
	long objectEnd = System.nanoTime();

	assert nativeResult != null;

	System.out.println("Results are equal: " + nativeResult.equals(objectResult));
	System.out.println("Native time:" + SpecsStrings.parseTime(nativeEnd - nativeStart));
	System.out.println("Recursive time:" + SpecsStrings.parseTime(objectEnd - objectStart));

	long listStart = System.nanoTime();
	Integer listResult = null;
	for (int i = 0; i < 1000; i++) {
	    listResult = H.i32(1).enumFrom().take(bound).sum().getNumber();
	}
	long listEnd = System.nanoTime();
	System.out.println("Results are equal: " + nativeResult.equals(listResult));
	System.out.println("List time:" + SpecsStrings.parseTime(listEnd - listStart));
    }

    // @Test
    public void testSum() {
	/// Pause
	// try {
	// System.out.println("WAITING...");
	// System.in.read();
	// } catch (IOException e) {
	// LoggingUtils.msgWarn("Error message:\n", e);
	// }

	(new HeapWindow()).run();
	int amount = 100000000;

	/// Sum with infinite list (Long objects)
	long objectStart = System.nanoTime();
	// long result = H.listInfNative(1l).take(amount).sum().getNumber();
	long result = ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class);
	long objectEnd = System.nanoTime();

	// System.out.println("Time:" + ParseUtils.parseTime(objectEnd - objectStart));
	long elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Time (Long):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with infinite list, (sumL)
	objectStart = System.nanoTime();
	result = NumOp.sumL(H.listInf(1l).take(amount).toJavaList());
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Time (sumL):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with infinite list (HLong objects)
	objectStart = System.nanoTime();
	// result = H.listInf(1l).take(amount).sum().getNumber();
	result = ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class);
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Timeb(HLong):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with infinite list, take 2(Long objects)
	objectStart = System.nanoTime();
	result = ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class);
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Time (Long, take 2):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with infinite list, (ListLong)
	objectStart = System.nanoTime();
	result = H.listInf2(1l).take(amount).sum();
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Time (ListLong):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with infinite list, (ListLong, take 2)
	objectStart = System.nanoTime();
	result = H.listInf2(1l).take(amount).sum();
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("InfList Time (ListLong, take 2):" + elapsedTime);
	System.out.println("Result:" + result);

	/// Sum with cycle - much faster and much less memory
	objectStart = System.nanoTime();
	result = ListOp.sum(H.listT(10000l).cycle().take(amount), Long.class);
	objectEnd = System.nanoTime();

	elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println("Cycle Time:" + elapsedTime);
	System.out.println("Result:" + result);

	/// Native time
	/*
	objectStart = System.nanoTime();
	
	result = 0l;
	for (int i = 1; i <= amount; i++) {
	    result += i;
	}
	
	// Integer result = ListOp.sum(H.listInf(0).take(amount), Integer.class);
	objectEnd = System.nanoTime();
	System.out.println(
		"Time native:" + TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS));
	System.out.println("Result:" + result);
	*/

	/// Pause
	// try {
	// System.out.println("FINISHED");
	// System.in.read();
	// } catch (IOException e) {
	// LoggingUtils.msgWarn("Error message:\n", e);
	// }
    }

    @Test
    public void testSumV2() {

	int amount = 10000000; // Tested in Haskell
	// int amount = 100000000;

	test(() -> ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class), "Sum(Number)");
	test(() -> NumOp.sumL(H.listInf(1l).take(amount).toJavaList()), "Sum(Long)");
	test(() -> H.listInf2(1l).take(amount).sum(), "ListLong.sum()");
	test(() -> Sum.jlong(H.listInf(1l).take(amount).toJavaList()), "Sum.jlong()");

	test(() -> ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class), "Sum(Number)");
	test(() -> NumOp.sumL(H.listInf(1l).take(amount).toJavaList()), "Sum(Long)");
	test(() -> H.listInf2(1l).take(amount).sum(), "ListLong.sum()");
	test(() -> Sum.jlong(H.listInf(1l).take(amount).toJavaList()), "Sum.jlong()");

	// H.listInfEnum(H.integer(1)).take(100000000);
	// test(() -> Sum.real(H.listInfEnum(H.integer(1)).take(10000)), "Sum.real() on HInt");
	test(() -> H.i(1).enumFrom().take(amount).sum(), "HListInt.sum()");

    }

    // @Test
    public void testBigInteger() {
	// int value = 100000;
	int value = 50000;
	// Warm-up
	// test(() -> factorial(BigInteger.valueOf(value)).remainder(BigInteger.valueOf(7)), "BigInteger");
	test(() -> factorial(H.integer(value)).mod(7), "HInteger");

	// Testing
	// test(() -> factorial(BigInteger.valueOf(value)).remainder(BigInteger.valueOf(7)), "BigInteger");
	test(() -> factorial(H.integer(value)).mod(7), "HInteger");

    }

    // @Test
    public void testHFunction() {
	int value = 50000;

	// Warm-up
	test(() -> SyntaxInFunctions.factorialHInteger().apply(new HInteger(value)).mod(7), "HInteger");

	// Testing
	test(() -> SyntaxInFunctions.factorialHInteger().apply(new HInteger(value)).mod(7), "HInteger");
    }

    static <R> void test(Supplier<R> function, String message) {
	long objectStart = System.nanoTime();
	R result = function.get();
	long objectEnd = System.nanoTime();

	long elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
	System.out.println(message + " time:" + elapsedTime + "ms");
	System.out.println("Result:" + result.toString());

    }

    static BigInteger factorial(BigInteger integer) {
	// return Product.jbigInt(H.range(BigInteger.ONE, integer));
	return H.range(BigInteger.ONE, integer).product().getNumber();
    }

    static HInteger factorial(HInteger integer) {
	// return Product.real(H.range(HInteger.ONE, integer));
	return H.range(HInteger.ONE, integer).product();
    }

}
