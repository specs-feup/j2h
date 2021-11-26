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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BabyTest {

    @Test
    public void test() {
        // doubleMe
        assertEquals(4, Baby.I.doubleMe(2));

        // doubleUs
        assertEquals(2 ^ 2 + 3 ^ 2, Baby.I.doubleUs(2, 3));

        // doubleSmallNumber
        assertEquals(10, Baby.I.doubleSmallNumber(5));
        assertEquals(200, Baby.I.doubleSmallNumber(100));
        assertEquals(101, Baby.I.doubleSmallNumber(101));

    }

}
