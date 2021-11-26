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

package pt.up.fe.specs.j2h.tuple;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;

class Tuple2ListIterator<T1, T2> extends Tuple2List<T1, T2> {

    private final HList<T1> list1;
    private final HList<T2> list2;

    Tuple2ListIterator(HList<T1> list1, HList<T2> list2) {
	super(SizeType.combine(list1, list2));

	this.list1 = list1;
	this.list2 = list2;
    }

    @Override
    public Tuple2<T1, T2> get(int index) {
	return new Tuple2<>(list1.get(index), list2.get(index));
    }

    @Override
    public int size() {

	// TODO: This is wrong, only if both lists are infinite, is the size of the tuple list infinite
	// if (list1.isInf() || list2.isInf()) {
	if (list1.isInf() && list2.isInf()) {
	    return ListUtils.infListSize();
	}

	// If both lists are of type fast, just return the size
	if (list1.getSizeType() == SizeType.FAST && list2.getSizeType() == SizeType.FAST) {
	    return Math.min(list1.size(), list2.size());
	}

	int sizeUpperBound = Integer.MAX_VALUE;
	if (list1.getSizeType() == SizeType.FAST) {
	    sizeUpperBound = list1.size();
	} else if (list2.getSizeType() == SizeType.FAST) {
	    sizeUpperBound = list2.size();
	}

	for (int i = 1; i < sizeUpperBound; ++i) {
	    if (!list1.isSizeAtLeast(i) && !list2.isSizeAtLeast(i)) {
		return i - 1;
	    }
	}

	return sizeUpperBound;
    }

}
