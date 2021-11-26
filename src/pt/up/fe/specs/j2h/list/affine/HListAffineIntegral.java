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

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.prelude.classes.Integral;

public class HListAffineIntegral<N extends Number, T extends Integral<N, T>> extends HListAffine<T> {

    public HListAffineIntegral(T base, T step) {
	this(base, step, -1);
    }

    public HListAffineIntegral(T base, T step, int size) {
	super(base, step, size);
    }

    @Override
    protected T nextIndex(int index) {
	// base + (step*index)
	return getBase().plus(getStep().times(index));
    }

    @Override
    public HList<T> subList(int fromIndex, int toIndex) {
	// newBase = base + (step*fromIndex)
	T newBase = getBase().plus(getStep().times(fromIndex));
	int newSize = toIndex - fromIndex;
	return new HListAffineIntegral<>(newBase, getStep(), newSize);
    }

}
