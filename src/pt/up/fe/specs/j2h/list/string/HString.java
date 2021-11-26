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

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.ListFactory;

/**
 * TODO: Transform into an abstract class that extends from HList. Then implement HStringJava nd HStringStream, that
 * extend from HListJava and HListStream.
 * 
 * @author João Bispo
 *
 */
public abstract class HString extends HList<Character> implements CharSequence {

    public static HString create(List<Character> chars) {
	return new HStringJava(chars);
    }

    public static HString create(Supplier<Iterator<Character>> chars, boolean isInf) {
	return new HStringIterator(chars, isInf);
    }

    protected HString(SizeType sizeType) {
	super(sizeType);
    }

    /**
     * Helper method which accepts a String.
     */
    public HString conc(String string) {
	return conc(ListFactory.createString(string));
    }

    @Override
    public HString conc(HList<Character> list) {
	return ListFactory.createString(super.conc(list));
    }

    /**
     * Helper method which accepts a char.
     * 
     * @param c
     * @return
     */
    @Override
    public HString cons(Character c) {
	return ListFactory.createString(super.cons(c));
    }

    @Override
    public HString subList(int fromIndex, int toIndex) {
	return ListFactory.createString(super.subList(fromIndex, toIndex));
    }

    @Override
    public HString tail() {
	return ListFactory.createString(subList(1, size()));
    }

    @Override
    public HString init() {
	return ListFactory.createString(subList(0, size() - 1));
    }

    @Override
    public HString reverse() {
	return ListFactory.createString(super.reverse());
    }

    @Override
    public HString take(int amount) {
	return ListFactory.createString(super.take(amount));
    }

    @Override
    public HString drop(int amount) {
	return ListFactory.createString(super.drop(amount));
    }

    @Override
    public HString cycle() {
	// TODO Auto-generated method stub
	return ListFactory.createString(super.cycle());

    }

    @Override
    public String toString() {
	Iterator<Character> it = (Iterator<Character>) iterator();
	StringBuilder sb = new StringBuilder();
	// sb.append("\"");
	while (it.hasNext()) {
	    sb.append(it.next());
	}
	// sb.append("\"");

	return sb.toString();

	// "Convert" to what Haskell would return
	// return string.replace("\\", "\\\\");

    }

    @Override
    public String toHaskellString() {
	Iterator<Character> it = (Iterator<Character>) iterator();
	StringBuilder sb = new StringBuilder();
	sb.append("\"");
	while (it.hasNext()) {
	    sb.append(it.next());
	}
	sb.append("\"");

	String string = sb.toString();

	// "Convert" to what Haskell would return
	return string.replace("\\", "\\\\");

    }

    /// CharSequence Implementation

    @Override
    public char charAt(int index) {
	return get(index);
    }

    @Override
    public int length() {
	return size();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
	return subList(start, end);
    }
}
