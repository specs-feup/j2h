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

package pt.up.fe.specs.j2h.prelude.data;

import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.j2h.prelude.classes.RealFrac;

public abstract class Ratio<N extends Number, I extends Integral<N, I>, T extends Ratio<N, I, T>>
	implements RatioStatic<N, I, T>, RealFrac<N, N, I, Ratio<N, I, T>> {

    protected final I numerator;
    protected final I denominator;

    public Ratio(I numerator, I denominator) {
	this.numerator = numerator;
	this.denominator = denominator;
    }

    /**
     * 
     * @return Extract the numerator of the ratio in reduced form: the numerator and denominator have no common factor
     *         and the denominator is positive.
     * 
     * 
     */
    public I numerator() {
	return numerator;
    }

    /**
     * 
     * @return Extract the denominator of the ratio in reduced form: the numerator and denominator have no common factor
     *         and the denominator is positive.
     * 
     * 
     */
    public I denominator() {
	return denominator;
    }
}
