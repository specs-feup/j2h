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

package pt.up.fe.specs.j2h.list.util;

import java.util.List;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListUtils;

/**
 * Cycles the given list.
 * 
 * @author JoaoBispo
 *
 */
public class HListCycle<T> extends HList<T> {

    private final List<T> list;

    public HListCycle(List<T> list) {
	super(SizeType.INFINITE);
	this.list = list;
    }

    @Override
    public T get(int index) {
	// Mod index by list size
	int realIndex = index % list.size();

	return list.get(realIndex);
    }

    @Override
    public int size() {
	// Infinite list
	return ListUtils.infListSize();
    }

}
