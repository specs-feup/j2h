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
import static pt.up.fe.specs.j2h.utils.ListOp.*;

import java.util.function.Function;

import org.junit.Test;

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.interfaces.HListFunction;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.numbers.HListInt;
import pt.up.fe.specs.j2h.list.string.HString;
import pt.up.fe.specs.j2h.tuple.Tuple3;
import pt.up.fe.specs.j2h.utils.ListOp;

public class StartingOutTest {

    @Test
    public void startingOutTest() {

	// Create list
	test("[4,8,15,16,23,42]", H.listHInt(4, 8, 15, 16, 23, 42));

	// listExamples
	test("[1,2,3,4,9,10,11,12]", H.listHInt(1, 2, 3, 4).conc(H.listHInt(9, 10, 11, 12)));
	test("\"hello world\"", H.string("hello").conc(" ").conc("world").toHaskellString());

	test("\"woot\"", H.string('w', 'o').conc(H.string('o', 't')).toHaskellString());
	test("[1,2]", H.listHInt(1).conc(H.listHInt(2)).take(2));
	test("[1,3,4,5]", H.listHInt(1).conc(H.listHInt(3)).conc(H.listHInt(4)).conc(H.listHInt(5)).take(100));

	// consExamples
	test("\"A SMALL CAT\"", H.string(" SMALL CAT").cons('A').toHaskellString());
	test("[5,1,2,3,4,5]", H.listHInt(1, 2, 3, 4, 5).cons(5));

	// getExamples
	test('B', H.string("Steve Buscemi").get(6));
	test(33.2, H.listT(9.4, 33.2, 96.2, 11.2, 23.25).get(1));

	// listsExamples
	HList<HListInt> list1 = H.listT(H.listHInt(1, 2, 3, 4), H.listHInt(5, 3, 3, 3), H.listHInt(1, 2, 2, 3, 4),
		H.listHInt(1, 2, 3));
	test("[[1,2,3,4],[5,3,3,3],[1,2,2,3,4],[1,2,3]]", list1);

	test("[[1,2,3,4],[5,3,3,3],[1,2,2,3,4],[1,2,3],[1,1,1,1]]", list1.conc(H.listT(H.listHInt(1, 1, 1, 1))));
	test("[[6,6,6],[1,2,3,4],[5,3,3,3],[1,2,2,3,4],[1,2,3]]", list1.cons(H.listHInt(6, 6, 6)));
	test("[1,2,2,3,4]", list1.get(2));

	// listComp
	test(true, gt(H.listHInt(3, 2, 1), (H.listHInt(2, 1, 0))));
	test(true, gt(H.listHInt(3, 4, 2), (H.listHInt(2, 10, 100))));
	test(true, gt(H.listHInt(3, 4, 2), H.listHInt(3, 4)));
	test(true, gt(H.listHInt(3, 4, 2), H.listHInt(2, 4)));
	test(true, eq(H.listHInt(3, 4, 2), H.listHInt(3, 4, 2)));
	test(true, lt(H.listHInt(3, 4, 2), H.listHInt(4, 4)));

	// ListOp
	test("5", H.listHInt(5, 4, 3, 2, 1).head());
	test("[4,3,2,1]", H.listHInt(5, 4, 3, 2, 1).tail());
	test("1", H.listHInt(5, 4, 3, 2, 1).last());
	test("[5,4,3,2]", H.listHInt(5, 4, 3, 2, 1).init());

	test(5, H.listHInt(5, 4, 3, 2, 1).length());
	test(false, H.listHInt(1, 2, 3).Null());
	test(true, H.list().Null());
	test(H.listHInt(1, 2, 3, 4, 5), H.listHInt(5, 4, 3, 2, 1).reverse());

	test(H.listHInt(5, 4, 3), H.listHInt(5, 4, 3, 2, 1).take(3));
	test(H.listHInt(3), H.listHInt(3, 9, 3).take(1));
	test(H.listHInt(1, 2), H.listHInt(1, 2).take(5));
	test(H.list(), H.listHInt(6, 6, 6).take(0));

	test(H.listHInt(1, 5, 6), H.listHInt(8, 4, 2, 1, 5, 6).drop(3));
	test(H.listHInt(1, 2, 3, 4), H.listHInt(1, 2, 3, 4).drop(0));
	test(H.list(), H.listHInt(1, 2, 3, 4).drop(100));

	test(1, minimum(H.listHInt(8, 4, 2, 1, 5, 6)));
	test(9, maximum(H.listHInt(1, 9, 2, 3, 4)));

	/// List operations

	// Empty list
	test(0, ListOp.sum(H.list(), Integer.class));
	test(31, H.listHInt(5, 2, 1, 6, 3, 2, 5, 7).sum());
	// Number a = HList.sum(H.list()); // Compilation error, does not support statically defined empty list
	// assertEquals(Integer.valueOf(31), ListOp.sum(H.list(5, 2, 1, 6, 3, 2, 5, 7))); // Removed, this
	// implementation of sum was a compilation error in javac

	test(24, H.listHInt(6, 2, 1, 2).product());
	test(0, H.listHInt(1, 2, 5, 6, 7, 9, 2, 0).product());

	test(true, H.listHInt(3, 4, 5, 6).elem(4));
	test(false, H.listHInt(3, 4, 5, 6).elem(10));

	// Streams
	test("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]", H.range(1, 20));

	test("\"abcdefghijklmnopqrstuvwxyz\"", H.range('a', 'z').toHaskellString());
	test("\"KLMNOPQRSTUVWXYZ\"", H.range('K', 'Z').toHaskellString());
	test("\"Z[\\\\]^_`a\"", H.range('Z', 'a').toHaskellString());
	test("\"\"", H.range('z', 'A').toHaskellString());

	test("[2,4,6,8,10,12,14,16,18,20]", H.range(2, 4, 20));
	test("[3,6,9,12,15,18]", H.range(3, 6, 20));
	test("[2,5,8,11,14]", H.range(2, 5, 15));
	test("[20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1]", H.range(20, 19, 1));

	test("[26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49]", H.listInf(26).take(24));

	test("[28,26,24,22,20,18,16,14,12,10,8,6,4,2,0,-2,-4,-6]", H.listInf(28, 26).take(18));

	test("[4,2,3]", H.i32(4).list().conc(H.listInf(2)).take(3));
	test("1002", H.listInf(2).get(1000));

	test("1", H.listHInt(1, 2).cycle().get(0));
	test("2", H.listHInt(1, 2).cycle().get(1));
	test("1", H.listHInt(1, 2).cycle().get(1000));
	test("[1,2,3,1,2,3,1,2,3,1]", H.listHInt(1, 2, 3).cycle().take(10));
	test("\"LOL LOL LOL \"", H.string("LOL ").cycle().take(12).toHaskellString());

	test("[5,5,5,5,5,5,5,5,5,5]", ListOp.repeat(5).take(10));

	test("[10,10,10]", ListOp.replicate(3, 10));

	/*
	 * List Comprehension
	 */

	test("[2,4,6,8,10,12,14,16,18,20]", H.lcomp(x -> x * 2, H.rangeN(1, 10)));
	test("[12,14,16,18,20]", H.lcomp(x -> x * 2, H.rangeN(1, 10), x -> x * 2 >= 12));
	test("[52,59,66,73,80,87,94]", H.lcomp(x -> x, H.rangeN(50, 100), x -> x % 7 == 3));
	test("[73,80,87,94]", H.lcomp(x -> x, H.rangeN(50, 100), x -> x % 7 == 3, x -> x > 70));

	HListFunction<Integer, HString> boomBangs = list -> H.lcomp(StartingOutTest::boomBangsFunc, list,
		x -> x % 2 == 1);

	test("[\"BOOM!\",\"BOOM!\",\"BANG!\",\"BANG!\"]", boomBangs.apply(H.rangeN(7, 13)).toHaskellString());

	test("[10,11,12,14,16,17,18,20]", H.lcomp(x -> x, H.rangeN(10, 20), x -> x != 13,
		x -> x != 15, x -> x != 19));

	test("[16,20,22,40,50,55,80,100,110]", H.lcomp((x, y) -> x * y, H.listT(2, 5, 10), H.listT(8, 10, 11)));
	test("[16,20,22,40,50,55,80,100,110]",
		H.lcomp((x, y) -> x.times(y), H.listHInt(2, 5, 10), H.listHInt(8, 10, 11)));
	test("[55,80,100,110]", H.lcomp((x, y) -> x * y, H.listT(2, 5, 10), H.listT(8, 10, 11), (x, y) -> x * y > 50));
	test("[55,80,100,110]",
		H.lcomp((x, y) -> x.times(y), H.listHInt(2, 5, 10), H.listHInt(8, 10, 11),
			(x, y) -> x.times(y).gt(50)));

	HList<HString> nouns = H.stringList("hobo", "frog", "pope");
	HList<HString> adjectives = H.stringList("lazy", "grouchy", "scheming");
	test("[\"lazy hobo\",\"lazy frog\",\"lazy pope\",\"grouchy hobo\",\"grouchy frog\",\"grouchy pope\",\"scheming hobo\",\"scheming frog\",\"scheming pope\"]",
		H.lcomp((x, y) -> x.conc(" ").conc(y), adjectives, nouns).toHaskellString());

	test(H.range(3, 27).length(), lengthV1().apply(H.range(3, 27)));
	test(H.range(3, 27).length(), lengthV2(H.range(3, 27)));

	test("HA", removeNonUppercase(H.string("Hahaha! Ahahaha!")));
	test("ILIKEFROGS", removeNonUppercase(H.string("IdontLIKEFROGS")));

	HList<HListInt> xxs = H.listT(H.listHInt(1, 3, 5, 2, 3, 1, 2, 4, 5), H.listHInt(1, 2, 3, 4, 5, 6, 7, 8, 9),
		H.listHInt(1, 2, 4, 2, 1, 6, 3, 1, 3, 2, 3, 6));
	test("[[2,2,4],[2,4,6,8],[2,4,2,6,2,6]]", H.lcomp(xs -> H.lcomp(x -> x, xs, x -> x.even()), xxs));

	/*
	 * Tuples
	 */
	test(8, H.tuple(8, 11).fst());
	test("Wow", H.tuple("Wow", false).fst());
	test(11, H.tuple(8, 11).snd());
	test(false, H.tuple("Wow", false).snd());

	test("[(1,5),(2,5),(3,5),(4,5),(5,5)]", H.listHInt(1, 2, 3, 4, 5).zip(H.listHInt(5, 5, 5, 5, 5)));

	test("[(1,\"one\"),(2,\"two\"),(3,\"three\"),(4,\"four\"),(5,\"five\")]",
		H.range(1, 5).zip(H.stringList("one", "two", "three", "four", "five")).toHaskellString());

	test("[(5,\"im\"),(3,\"a\"),(2,\"turtle\")]",
		H.listHInt(5, 3, 2, 6, 2, 7, 2, 5, 4, 6, 6).zip(H.stringList("im", "a", "turtle")).toHaskellString());

	test("[(1,\"apple\"),(2,\"orange\"),(3,\"cherry\"),(4,\"mango\")]",
		H.listInf(1).zip(H.stringList("apple", "orange", "cherry", "mango")).toHaskellString());

	// let triangles = [ (a,b,c) | c <- [1..10], b <- [1..10], a <- [1..10] ]
	test("[(1,1,1),(1,1,2),(1,2,1),(1,2,2),(2,1,1),(2,1,2),(2,2,1),(2,2,2)]",
		H.lcomp((a, b, c) -> new Tuple3<>(a, b, c), H.range(1, 2), H.range(1, 2), H.range(1, 2)));

	HList<Tuple3<Integer, Integer, Integer>> rightTriangles = H.lcomp((a, b, c) -> new Tuple3<>(a, b, c),
		H.rangeN(1, 10), H.rangeN(1, 10), H.rangeN(1, 10),
		(a, b, c) -> a <= b, (a, b, c) -> b < c, (a, b, c) -> a * a + b * b == c * c);
	test("[(3,4,5),(6,8,10)]", rightTriangles);

	HList<Tuple3<Integer, Integer, Integer>> rightTriangles2 = H.lcomp((a, b, c) -> new Tuple3<>(a, b, c),
		H.rangeN(1, 10), H.rangeN(1, 10), H.rangeN(1, 10),
		(a, b, c) -> a <= b, (a, b, c) -> b < c, (a, b, c) -> a * a + b * b == c * c,
		(a, b, c) -> a + b + c == 24);
	test("[(6,8,10)]", rightTriangles2);

    }

    private static HString boomBangsFunc(Integer x) {
	if (x < 10) {
	    return H.string("BOOM!");
	}
	return H.string("BANG!");
    }

    private static Function<HList<?>, Integer> lengthV1() {
	return xs -> sum(H.lcomp(x -> 1, xs), Integer.class);
    }

    private static Integer lengthV2(HList<?> xs) {
	return sum(H.lcomp(x -> 1, xs), Integer.class);
    }

    // removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z']]
    private static HList<Character> removeNonUppercase(HString st) {
	return H.string(H.lcomp(c -> c, st, c -> H.range('A', 'Z').elem(c)));
    }
}
