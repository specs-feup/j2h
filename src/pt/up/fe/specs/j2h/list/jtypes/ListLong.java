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

package pt.up.fe.specs.j2h.list.jtypes;

import java.util.Arrays;
import java.util.List;

import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListJava;

/**
 * A list of ints.
 * 
 * @author João Bispo
 *
 */
public class ListLong extends HList<Long> {

    private final HList<Long> list;

    public ListLong(HList<Long> ints) {
	super(ints.getSizeType());
	list = ints;
    }

    public ListLong(Long... ints) {
	this(Arrays.asList(ints));
    }

    public ListLong(List<Long> ints) {
	this(new HListJava<>(ints));
    }

    @Override
    public Long get(int index) {
	return list.get(index);
    }

    @Override
    public int size() {
	return list.size();
    }

    /*
     * Constructors
     */
    public static ListLong create(HList<Long> list) {
	return new ListLong(list);
    }

    public static ListLong create(List<Long> elements) {
	return new ListLong(elements);
    }

    public static ListLong create(Long... elements) {
	return new ListLong(elements);
    }

    /*
     * Overriden methods
     */

    @Override
    public ListLong conc(HList<Long> list) {
	return create(list.conc(list));
    }

    /**
     * Helper method which accepts an Long.
     */
    public ListLong conc(Long integer) {
	return conc(create(integer));
    }

    /**
     * Helper method which accepts a char.
     * 
     * @param c
     * @return
     */
    @Override
    public ListLong cons(Long c) {
	return create(list.cons(c));
    }

    @Override
    public ListLong subList(int fromIndex, int toIndex) {
	return create(list.subList(fromIndex, toIndex));
    }

    @Override
    public ListLong tail() {
	return create(list.tail());
    }

    @Override
    public ListLong init() {
	return create(list.init());
    }

    @Override
    public ListLong reverse() {
	return create(list.reverse());
    }

    @Override
    public ListLong take(int amount) {
	return create(list.take(amount));
    }

    @Override
    public ListLong drop(int amount) {
	return create(list.drop(amount));
    }

    @Override
    public ListLong cycle() {
	return create(list.cycle());
    }

    @Override
    public boolean elem(Long elem) {
	return contains(elem);
    }

    /*
     * Number methods.
     */
    public Long sum() {
	return stream()
		.mapToLong(element -> element)
		.sum();
    }

    public Long product() {
	return stream().reduce(1l, (acc, element) -> acc * element);
    }

}
