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

import static org.junit.Assert.fail;
import static pt.up.fe.specs.j2h.TestUtils.test;

import java.math.BigInteger;

import org.junit.Test;

import pt.up.fe.specs.j2h.H;
import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.list.string.HString;
import pt.up.fe.specs.j2h.prelude.data.HInt;
import pt.up.fe.specs.j2h.prelude.data.HInteger;
import pt.up.fe.specs.j2h.tuple.Tuple2;

public class TypesAndTypeClasses {

    @Test
    public void typesAndTypeClasses() {

        test("SXGEFA", removeNonUppercase(H.string("aSv32XdGnsEFA")));
        test(6, addThree(1, 2, 3));

        test("30414093201713378043612608166064768844377641568960512000000000000", factorial(BigInteger.valueOf(50)));
        test("30414093201713378043612608166064768844377641568960512000000000000", factorial(H.integer(50)));

        test("5050", sumInt(HInt.create(100)));
        test("5050", sumInt2(HInteger.create(100)));

        test("(9223372036854775807,9223372036854775807)", Tuple2.create(H.i(3), H.i(4)).maxBound());
        test("(-9223372036854775808,-9223372036854775808)", Tuple2.create(H.i(3), H.i(4)).minBound());
        // It also works with Java Numbers
        test("(-2147483648,-9223372036854775808)", Tuple2.create(3, H.i(4)).minBound());

        // String does not implement Bounded, so it should throw exception
        try {
            Tuple2.create("String is not Bounded", H.i(4)).minBound();
            fail();
        } catch (JavaHaskellException e) {
            test("Value of type 'String' of 'Tuple2' does not implement Bounded", e.getMessage());
        }

    }

    static HString removeNonUppercase(HString st) {
        return H.string(H.lcomp(c -> c, st, c -> H.range('A', 'Z').elem(c)));
    }

    static Integer addThree(Integer x, Integer y, Integer z) {
        return x + y + z;
    }

    static BigInteger factorial(BigInteger integer) {
        // return Product.jbigInt(H.range(BigInteger.ONE, integer)); // Without specialized Hlists
        return H.range(BigInteger.ONE, integer).product().getNumber(); // With HListInteger
    }

    static HInteger factorial(HInteger integer) {
        // return Product.real(H.range(HInteger.ONE, integer)); // Without specialized Hlists
        return H.range(HInteger.ONE, integer).product(); // With HListInteger
    }

    static HInteger sumInt(HInt amount) {
        return H.listInf(HInteger.ONE).take(amount).sum();
    }

    static HInteger sumInt2(HInteger amount) {
        return H.range(BigInteger.ONE, amount.getNumber()).sum();
    }

}
