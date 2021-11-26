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

package pt.up.fe.specs.j2h.list.string;

import java.util.List;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;

class HStringJava extends HString {

    private final HList<Character> list;

    HStringJava(List<Character> chars) {
	super(SizeType.FAST);
	list = new HListJava<>(chars);
    }

    HStringJava(HList<Character> chars) {
	super(SizeType.FAST);
	throw new JavaHaskellException("This class should not be initialized with HLists");
    }

    @Override
    public Character get(int index) {
	return list.get(index);
    }

    @Override
    public int size() {
	return list.size();
    }

    /*
    @Override
    public List<Character> toList() {
    return list.toList();
    }
    */
}
