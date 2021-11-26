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

import java.util.function.Function;

import pt.up.fe.specs.j2h.list.HList;

public class HListWrapper<T, R> extends HList<R> {

    private final HList<T> originalList;
    private final Class<T> originalClass;
    private final Function<T, R> converter;

    public HListWrapper(Class<T> originalClass, HList<T> originalList, Function<T, R> converter) {
	super(originalList.getSizeType());

	this.originalClass = originalClass;
	this.originalList = originalList;
	this.converter = converter;
    }

    @Override
    public R get(int index) {
	return converter.apply(originalList.get(index));
    }

    @Override
    public int size() {
	return originalList.size();
    }

    public T getOriginal(int index) {
	return originalList.get(index);
    }

    public Class<T> getOriginalClass() {
	return originalClass;
    }

}
