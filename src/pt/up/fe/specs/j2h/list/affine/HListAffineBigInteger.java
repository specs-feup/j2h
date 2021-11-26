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

package pt.up.fe.specs.j2h.list.affine;

import java.math.BigInteger;

import pt.up.fe.specs.j2h.list.HList;

public class HListAffineBigInteger extends HListAffine<BigInteger> {

    HListAffineBigInteger(BigInteger base, BigInteger step) {
	this(base, step, -1);
    }

    HListAffineBigInteger(BigInteger base, BigInteger step, int size) {
	super(base, step, size);
    }

    @Override
    protected BigInteger nextIndex(int index) {
	// base + (step*index)
	return getBase().add(getStep().multiply(BigInteger.valueOf(index)));
    }

    @Override
    public HList<BigInteger> subList(int fromIndex, int toIndex) {
	// newBase = base + (step*fromIndex)
	BigInteger newBase = getBase().add(getStep().multiply(BigInteger.valueOf(fromIndex)));
	int newSize = toIndex - fromIndex;
	return new HListAffineBigInteger(newBase, getStep(), newSize);
    }

    // @Override
    // protected Class<BigInteger> getElementClass() {
    // return BigInteger.class;
    // }
}
