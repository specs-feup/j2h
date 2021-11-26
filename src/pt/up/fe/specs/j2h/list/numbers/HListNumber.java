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

package pt.up.fe.specs.j2h.list.numbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import pt.up.fe.specs.j2h.exceptions.JavaHaskellException;
import pt.up.fe.specs.j2h.functions.Product;
import pt.up.fe.specs.j2h.functions.Sum;
import pt.up.fe.specs.j2h.interfaces.SizeType;
import pt.up.fe.specs.j2h.interfaces.ThisProvider;
import pt.up.fe.specs.j2h.list.HList;
import pt.up.fe.specs.j2h.list.util.HListWrapper;
import pt.up.fe.specs.j2h.prelude.classes.Integral;
import pt.up.fe.specs.j2h.prelude.classes.Real;

public abstract class HListNumber<N extends Number, T extends Real<N, T>, L extends HListNumber<N, T, L>>
	extends HList<T> implements HListNumberStatic<N, T, L>, ThisProvider<L> {

    // private static int WRAPPERS = 0;

    public HListNumber(SizeType sizeType) {
	super(sizeType);
    }

    /*
     * Constructors
     */

    /*
     * Custom methods
     */
    public List<N> toJavaList() {
	if (isInf()) {
	    throw new JavaHaskellException("'toNativeList()' does not support infinite lists");
	}

	return stream()
		.map(hint -> hint.getNumber())
		.collect(Collectors.toList());
    }

    abstract protected Class<T> getElementClass();

    /**
     * Converts to an HList of the Java number.
     * 
     * @return
     */
    public HList<N> toNative() {
	return new HListWrapper<>(getElementClass(), this, htype -> htype.getNumber());
    }

    /**
     * Adapts a list to the current list format.
     * 
     * @param list
     * @return
     */
    @SuppressWarnings("unchecked")
    private L adapt(HList<T> list) {
	if (getThis().getClass().isInstance(list)) {
	    return (L) list;
	}

	// HListNumber.WRAPPERS += 1;
	// System.out.println("WRAPPERS:" + HListNumber.WRAPPERS + " (" + list.getClass() + ")");
	return wrapperList(list);
    }

    /*
     * Overriden methods
     */

    @Override
    public L conc(HList<T> list) {
	return adapt(super.conc(list));
	// return createList(super.conc(list));
    }

    /**
     * Helper method which accepts a Number.
     */
    public L conc(Number number) {
	return conc(nativeList(number));
    }

    /**
     * Helper method which accepts a single element.
     * 
     * @param c
     * @return
     */
    @Override
    public L cons(T c) {
	L elementList = nativeList((Arrays.asList(c.getNumber())));
	return elementList.conc(this);
    }

    public L cons(Number c) {
	return cons(createElement(c));
	// return create(super.cons(create(c)));
    }

    @Override
    public L subList(int fromIndex, int toIndex) {
	return nativeList(toJavaList().subList(fromIndex, toIndex));
    }

    @Override
    public L tail() {
	return subList(1, size());
    }

    @Override
    public L init() {
	return subList(0, size() - 1);
    }

    @Override
    public L reverse() {
	return nativeList(Lists.reverse(toJavaList()));
    }

    @Override
    public L take(int amount) {
	return adapt(super.take(amount));
    }

    public L take(Integral<?, ?> amount) {
	return take(amount.getNumber().intValue());
    }

    @Override
    public L drop(int amount) {
	return adapt(super.drop(amount));
    }

    @Override
    public L cycle() {
	return adapt(super.cycle());
    }

    public boolean elem(Number elem) {
	return contains(createElement(elem));
    }

    /*
     * Methods for Real
     */
    public T sum() {
	return Sum.real(this);
    }

    public T product() {
	return Product.real(this);
    }

    /*
     * Overload Eq
     */

}