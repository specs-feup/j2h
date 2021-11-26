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

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;

/**
 * Java List implementation of HList.
 */
public class HListJava<T> extends HList<T> {

    private final List<T> internalList;

    public HListJava(List<T> elements) {
	super(SizeType.FAST);
	internalList = elements;
    }

    private HListJava(HList<T> elements) {
	super(SizeType.FAST);
	throw new JavaHaskellException("This class should not be initialized with HLists");
    }

    @Override
    public T get(int index) {
	return internalList.get(index);
    }

    @Override
    public int size() {
	return internalList.size();
    }

    /*
    @Override
    public List<T> toList() {
    return internalList;
    }
    */

}
