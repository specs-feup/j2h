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

package pt.up.fe.specs.j2h.lib;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.up.fe.specs.j2h.H;

public class HStringTest {

    @Test
    public void test() {
	// Simple Concatenation
	assertEquals(H.string("hello"), H.string("h").conc("ello"));
	assertEquals(H.string("hello"), H.string("h").conc("el").conc("lo"));

	// Concatenation with variadic inputs
	assertEquals(H.string("h"), H.stringJoin("h"));
	assertEquals(H.string("he"), H.stringJoin("h", "e"));
	assertEquals(H.string("hel"), H.stringJoin("h", "e", "l"));
	assertEquals(H.string("hello"), H.stringJoin("h", "el", "lo"));

	// Cons
	assertEquals(H.string("ah"), H.string("h").cons('a'));
    }

}
