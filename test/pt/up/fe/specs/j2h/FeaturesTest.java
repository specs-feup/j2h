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

import static pt.up.fe.specs.j2h.TestUtils.*;

import org.junit.Test;

public class FeaturesTest {

    @Test
    public void testStrings() {
	// HString is a CharSequence
	test("a string", H.string("a string"));
	test("a string in builder", new StringBuilder().append(H.string("a string in builder")));

    }

    @Test
    public void testAffineEnum() {

	// H constructor
	test("[26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49]",
		H.listInfEnum(H.i(26)).take(24));

	test("[28,26,24,22,20,18,16,14,12,10,8,6,4,2,0,-2,-4,-6]", H.listInfEnum(H.i(28), H.i(26)).take(18));

	test("[4,2,3]", H.listT(H.i(4)).conc(H.listInfEnum(H.i(2))).take(3));
	test("1002", H.listInfEnum(H.i(2)).get(1000));
	test("1000002", H.listInfEnum(H.i(2)).get(1000000));

	// Instance constructor
	test("[26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49]", H.i(26).enumFrom().take(24));

	test("[28,26,24,22,20,18,16,14,12,10,8,6,4,2,0,-2,-4,-6]", H.i(28).enumFromThen(26).take(18));

	test("[4,2,3]", H.i(4).list().conc(H.i(2).enumFrom()).take(3));
	test("1002", H.i(2).enumFrom().get(1000));
	test("1000002", H.i(2).enumFrom().get(1000000));

	// Ranges
	// Streams
	test("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]", H.i(1).enumFromTo(20));

	// test("\"abcdefghijklmnopqrstuvwxyz\"", H.range('a', 'z').toHaskellString());
	// test("\"KLMNOPQRSTUVWXYZ\"", H.range('K', 'Z').toHaskellString());
	// test("\"Z[\\\\]^_`a\"", H.range('Z', 'a').toHaskellString());
	// test("\"\"", H.range('z', 'A').toHaskellString());

	test("[2,4,6,8,10,12,14,16,18,20]", H.i(2).enumFromThenTo(4, 20));
	test("[3,6,9,12,15,18]", H.i(3).enumFromThenTo(6, 20));
	test("[2,5,8,11,14]", H.i(2).enumFromThenTo(5, 15));
	test("[20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1]", H.i(20).enumFromThenTo(19, 1));
    }
}
