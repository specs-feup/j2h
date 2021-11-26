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
import java.util.Collections;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListFactory;
import pt.up.fe.specs.j2h.list.affine.HListAffine;
import pt.up.fe.specs.j2h.list.affine.HListAffineEnum;
import pt.up.fe.specs.j2h.list.jtypes.ListLong;
import pt.up.fe.specs.j2h.list.numbers.HListInt;
import pt.up.fe.specs.j2h.list.numbers.HListInt32;
import pt.up.fe.specs.j2h.list.numbers.HListInteger;
import pt.up.fe.specs.j2h.list.string.HString;
import pt.up.fe.specs.j2h.list.util.HListWrapper;
import pt.up.fe.specs.j2h.prelude.classes.HEnum;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.j2h.prelude.data.HInt;
import pt.up.fe.specs.j2h.prelude.data.HInt32;
import pt.up.fe.specs.j2h.prelude.data.HInteger;
import pt.up.fe.specs.j2h.tuple.Tuple2;
import pt.up.fe.specs.j2h.utils.TriFunction;

/**
 * Utility methods.
 * 
 * @author João Bispo
 *
 */
public interface H {

    static <T> HList<T> list() {
	return ListFactory.empty();
    }

    /**
     * Creates a new list from the given elements.
     * 
     * @param elements
     * @return
     */
    @SafeVarargs
    static <T> HList<T> listT(T... elements) {
	// Check for strings
	// if (elements.length > 0 && String.class.isInstance(elements[0])) {
	// throw new JavaHaskellException(
	// "This method does not support the class Strings as inputs. Please use .stringList()");
	// }

	return ListFactory.create(elements);

    }

    // static HListInt listHInt(Integer... elements) {
    // return HListInt.create(elements);
    // }

    static HListInt listHInt(Number... elements) {
	return HListInt.STATIC.nativeList(elements);
    }

    static HList<Integer> rangeN(int start, int end) {
	return range(start, end).toNative();
    }

    static HListInt32 range(int start, int end) {
	// If negative, return empty range
	if (end - start < 0) {
	    return HListInt32.create(Collections.emptyList());
	}

	return range(start, start + 1, end);
    }

    static HListInt32 range(int first, int second, int end) {
	int step = second - first;

	return ListFactory.createInt(first, step, J2HUtils.getLimit(first, end, step));
    }

    static HListInteger range(HInteger start, HInteger end) {
	return range(start.getNumber(), end.getNumber());
    }

    // static HList<BigInteger> range(BigInteger start, BigInteger end) {
    static HListInteger range(BigInteger start, BigInteger end) {
	// If negative, return empty range
	// end - start < 0
	if (end.subtract(start).compareTo(BigInteger.ZERO) < 0) {
	    return HListInteger.create();
	}

	return range(start, start.add(BigInteger.ONE), end);
    }

    // static HList<BigInteger> range(BigInteger first, BigInteger second, BigInteger end) {
    static HListInteger range(BigInteger first, BigInteger second, BigInteger end) {
	BigInteger step = second.subtract(first);

	return HListInteger.createNative(HListAffine.New(first, step, J2HUtils.getLimit(first, end, step).intValue()));
    }

    static <N extends Number, T extends Integral<N, T>> HList<T> range(T start, T end) {
	// If negative, return empty range
	// end - start < 0

	if (end.minus(start).lt(start.zero())) {
	    return ListFactory.empty();
	}

	return range(start, start.plus(start.one()), end);
    }

    static <N extends Number, T extends Integral<N, T>> HList<T> range(T first, T second, T end) {
	T step = second.minus(first);

	return HListAffine.New(first, step, J2HUtils.getLimit(first, end, step).getNumber().intValue());
    }

    static HList<Double> range(double start, double end) {
	// If negative, return empty range
	if (end - start < 0) {
	    return ListFactory.empty();
	}

	return range(start, start + 1, end);
	// return ListFactory.create(IntStream.rangeClosed(start, end).iterator());
    }

    static HList<Double> range(double first, double second, double end) {
	double step = second - first;

	return ListFactory.createDouble(first, step, (int) J2HUtils.getLimit(first, end, step));
	// Supplier<Iterator<Double>> supplier = () -> Stream
	// .iterate(first, anInt -> Double.valueOf(anInt + step))
	// .limit((int) J2HUtils.getLimit(first, end, step))
	// .iterator();
	//
	// return ListFactory.create(supplier);
    }

    static HString range(char start, char end) {
	// If negative, return empty range
	if (end - start < 0) {
	    return ListFactory.createString("");
	}

	return range(start, (char) (start + 1), end);
	// return ListFactory.createString(Stream.iterate(start, aChar -> Character.valueOf((char) (aChar + 1)))
	// .limit(J2HUtils.getLimit(start, end))
	// .iterator());
    }

    static HString range(char first, char second, char end) {
	int step = second - first;

	Supplier<Iterator<Character>> supplier = () -> Stream
		.iterate(first, aChar -> Character.valueOf((char) (aChar + step)))
		.limit(J2HUtils.getLimit(first, end, step))
		.iterator();

	return ListFactory.createString(supplier);
    }

    static <T extends HEnum<T>> HList<T> listInfEnum(T start) {
	return HListAffineEnum.inf(start);
    }

    static <T extends HEnum<T>> HList<T> listInfEnum(T first, T second) {
	return HListAffineEnum.inf(first, second);
    }

    static HListInteger listInf(HInteger start) {
	// TODO: This could potentially be more efficient if we create a listInf for BigIntegers. Would have to measure
	HList<BigInteger> bInt = new HListWrapper<>(HInteger.class, listInfEnum(start), hint -> hint.getNumber());
	return HListInteger.createNative(bInt);
    }

    static HListInt32 listInf(int start) {
	return listInf(start, start + 1);
    }

    static HListInt32 listInf(int first, int second) {
	int step = second - first;
	return ListFactory.createInfInt(first, step);
    }

    static HListInt listInfNative(long start) {
	return listInfNative(start, start + 1);
    }

    static HListInt listInfNative(long first, long second) {
	long step = second - first;
	return ListFactory.createInfLong(first, step);
    }

    static HListInt listInf(long start) {
	return listInf(start, start + 1);
    }

    static HListInt listInf(long first, long second) {
	long step = second - first;
	return ListFactory.createInfLong(first, step);
    }

    static ListLong listInf2(long start) {
	return listInf2(start, start + 1);
    }

    static ListLong listInf2(long first, long second) {
	long step = second - first;
	return ListLong.create(ListFactory.createInfLong(first, step).toJavaList());
    }

    static HString listInf(char first, char second) {
	// If negative, return empty range
	int step = second - first;
	if (step < 0) {
	    return ListFactory.createString("");
	}

	Supplier<Iterator<Character>> supplier = () -> Stream
		.iterate(first, aChar -> Character.valueOf((char) (aChar + step)))
		.iterator();

	return ListFactory.createStringInf(supplier);
    }

    static HString listInf(char start) {
	return listInf(start, (char) (start + 1));
	// return ListFactory
	// .createString(Stream.iterate(start, aChar -> Character.valueOf((char) (aChar + 1))).iterator());
    }

    static HString string(String string) {
	return ListFactory.createString(string);
    }

    static HString string(HList<Character> chars) {
	return ListFactory.createString(chars);
    }

    static HString string(char... chars) {
	String[] strings = new String[chars.length];
	for (int i = 0; i < chars.length; i++) {
	    strings[i] = Character.toString(chars[i]);
	}

	return stringJoin(strings);
    }

    static HString stringJoin(String... strings) {
	return Arrays.stream(strings)
		.map(string -> ListFactory.createString(string))
		.reduce(ListFactory.createString(""), (acc, string) -> ListFactory.createString(acc.conc(string)));
    }

    static HList<HString> stringList(String... string) {
	// Convert to HString
	HString[] hstring = new HString[string.length];
	for (int i = 0; i < string.length; i++) {
	    hstring[i] = string(string[i]);
	}
	return listT(hstring);
    }

    @SafeVarargs
    static <T, R> HList<R> lcomp(Function<T, R> function, HList<T> list,
	    Function<T, Boolean>... predicates) {

	return ListFactory.comp(function, list, Arrays.asList(predicates));
    }

    @SafeVarargs
    static <T, U, R> HList<R> lcomp(
	    BiFunction<T, U, R> function, HList<T> listT, HList<U> listU, BiFunction<T, U, Boolean>... predicates) {

	return ListFactory.comp(function, listT, listU, Arrays.asList(predicates));
    }

    @SafeVarargs
    static <T1, T2, T3, R> HList<R> lcomp(
	    TriFunction<T1, T2, T3, R> function, HList<T1> list1, HList<T2> list2, HList<T3> list3,
	    TriFunction<T1, T2, T3, Boolean>... predicates) {

	return ListFactory.comp(function, list1, list2, list3, Arrays.asList(predicates));
    }

    static HInt32 i32(Number number) {
	return new HInt32(number.intValue());
    }

    static HInt i(Number number) {
	return new HInt(number.longValue());
    }

    static HInteger integer(Number number) {
	return HInteger.ZERO.fromNumber(number);
    }

    /**
     * Creates a Tuple of two values.
     * 
     * @param value1
     * @param value2
     * @return
     */
    static <T1, T2> Tuple2<T1, T2> tuple(T1 value1, T2 value2) {
	return new Tuple2<>(value1, value2);
    }
}
