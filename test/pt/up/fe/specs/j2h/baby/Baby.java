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

package pt.up.fe.specs.j2h.baby;

import static pt.up.fe.specs.j2h.utils.ListOp.*;

import java.util.Arrays;
import java.util.List;

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.numbers.HListInt;
import pt.up.fe.specs.j2h.list.string.HString;

public interface Baby {

    static Baby I = new Baby() {
    };

    // static Baby me() {
    // return Baby.INSTANCE;
    // }

    /**
     * Haskell Rule 1: Functions can't begin with capital letters.
     * 
     * @param a
     * @return
     */
    default int doubleMe(int a) {
	return a + a;
    }

    default int doubleUs(int a, int b) {
	return a ^ 2 + b ^ 2;
    }

    /**
     * Haskell Rule 2: if statements must always have an else. All functions must return something.
     * 
     * @param a
     * @return
     */
    default int doubleSmallNumber(int a) {
	if (a > 100) {
	    return a;
	}
	// If an 'if' does not have an else, everything after it should be put inside an else
	return doubleMe(a);
    }

    /**
     * Haskell Rule 3: Functions that do not take parameters are "definitions"
     */
    default List<Integer> lostNumbers() {
	return Arrays.asList(4, 8, 15, 16, 23, 42);
    }

    default List<Long> listExample1() {
	return H.listHInt(1, 2, 3, 4).conc(H.listHInt(9, 10, 11, 12)).toJavaList();
    }

    default HString listExample2() {
	return H.string("hello").conc(" ").conc("world");
    }

    default List<Character> listExample3() {
	return H.listT('w', 'o').conc(H.listT('o', 't'));
    }

    default List<Character> consExample1() {
	return H.string(" SMALL CAT").cons('A');
    }

    default List<Long> consExample2() {
	return H.listHInt(1, 2, 3, 4, 5).cons(5).toJavaList();
    }

    default List<List<Object>> emptyLists() {
	return Arrays.asList(Arrays.asList());
    }

    default Character getExample1() {
	return H.stringJoin("Steve Buscemi").get(6);
    }

    default Double getExample2() {
	return H.listT(9.4, 33.2, 96.2, 11.2, 23.25).get(1);
    }

    default HList<HListInt> b() {
	return H.listT(H.listHInt(1, 2, 3, 4), H.listHInt(5, 3, 3, 3), H.listHInt(1, 2, 2, 3, 4),
		H.listHInt(1, 2, 3));
    }

    default HList<HListInt> listsExample1() {
	return b().conc(H.listT(H.listHInt(1, 1, 1, 1)));
    }

    default HList<HListInt> listsExample2() {
	return b().cons(H.listHInt(6, 6, 6));
    }

    default HListInt listsExample3() {
	return b().get(2);
    }

    default boolean listComp1() {
	return gt(H.listHInt(3, 2, 1), H.listHInt(2, 1, 0));
    }

    default boolean listComp2() {
	return gt(H.listHInt(3, 4, 2), H.listHInt(2, 10, 100));
    }

    default boolean listComp3() {
	return gt(H.listHInt(3, 4, 2), H.listHInt(3, 4));
    }

    default boolean listComp4() {
	return gt(H.listHInt(3, 4, 2), H.listHInt(2, 4));
    }

    default boolean listComp5() {
	return eq(H.listHInt(3, 4, 2), H.listHInt(3, 4, 2));
    }

    default boolean listComp6() {
	return lt(H.listHInt(3, 4, 2), H.listHInt(4, 4));
    }

    default Long listOp1() {
	return H.listHInt(5, 4, 3, 2, 1).head().getNumber();
    }

    default List<Long> listOp2() {
	return H.listHInt(5, 4, 3, 2, 1).tail().toJavaList();
    }

    default Long listOp3() {
	return H.listHInt(5, 4, 3, 2, 1).last().getNumber();
    }

    default List<Long> listOp4() {
	return H.listHInt(5, 4, 3, 2, 1).init().toJavaList();
    }

}
