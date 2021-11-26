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

package pt.up.fe.specs.j2h.prelude.classes;

import pt.up.fe.specs.j2h.prelude.data.Rational;

/**
 * "Static" methods related with Real class.
 * 
 * @author João Bispo
 *
 */
public interface FractionalStatic<T extends Fractional<T>> extends NumStatic<T> {

    /**
     * Conversion from a Rational (that is Ratio<HInteger>). A floating literal stands for an application of
     * fromRational to a value of type Rational, so such literals have type (Fractional a) => a.
     * 
     * @param a
     * @return
     */
    T fromRational(Rational a);
}
