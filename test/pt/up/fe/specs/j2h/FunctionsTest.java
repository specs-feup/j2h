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

import pt.up.fe.specs.j2h.functions.Product;
import pt.up.fe.specs.j2h.functions.Sum;

public class FunctionsTest {

    @Test
    public void testFunctions() {

	// Sum
	test("49", Sum.jint(H.range(4, 10).toJavaList()));
	test("49.0", Sum.jdouble(H.range(4d, 10d)));
	test("6", Sum.jlong(H.listT(1l, 2l, 3l)));
	test("6", Sum.real(H.listHInt(1, 2, 3)));

	// Product
	test("604800", Product.jint(H.range(4, 10).toJavaList()));
	test("604800.0", Product.jdouble(H.range(4d, 10d)));
	test("24", Product.jlong(H.listT(1l, 2l, 3l, 4l)));
	test("24", Product.real(H.listHInt(1, 2, 3, 4)));
    }

}
