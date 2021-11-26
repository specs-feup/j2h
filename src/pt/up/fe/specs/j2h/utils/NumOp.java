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

package pt.up.fe.specs.j2h.utils;

import java.util.List;

import pt.up.fe.specs.util.exceptions.NotImplementedException;

/**
 * Functions over numeric types.
 * 
 * <p>
 * This class was an experiment that accepts a generic T that extends Number.
 * 
 * @author João Bispo
 *
 */
public interface NumOp {

    /*
    static Number addGeneric(Number op1, Number op2) {
    // Get "winning" class
    throw new NotImplementedException("Not implementede yet");
    }
    */

    @SuppressWarnings("unchecked")
    static <T extends Number> T add(T op1, T op2) {
	// Both instances are of the same class
	if (op1.getClass().equals(Integer.class)) {
	    return (T) (Integer) ((Integer) op1 + (Integer) op2);
	}

	if (op1.getClass().equals(Double.class)) {
	    return (T) (Double) ((Double) op1 + (Double) op2);
	}

	if (op1.getClass().equals(Long.class)) {
	    return (T) (Long) ((Long) op1 + (Long) op2);
	}

	throw new NotImplementedException(op1.getClass());
    }

    @SuppressWarnings("unchecked")
    static <T extends Number> T mult(T op1, T op2) {
	// Both instances are of the same class
	if (op1.getClass().equals(Integer.class)) {
	    return (T) (Integer) ((Integer) op1 * (Integer) op2);
	}

	if (op1.getClass().equals(Double.class)) {
	    return (T) (Double) ((Double) op1 * (Double) op2);
	}

	if (op1.getClass().equals(Long.class)) {
	    return (T) (Long) ((Long) op1 * (Long) op2);
	}

	throw new NotImplementedException(op1.getClass());
    }

    /**
     * Creates a number '0' for the given Number class.
     * 
     * @param elementClass
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T extends Number> T zero(Class<T> elementClass) {
	if (elementClass.equals(Integer.class)) {
	    return (T) Integer.valueOf(0);
	}

	if (elementClass.equals(Double.class)) {
	    return (T) Double.valueOf(0);
	}

	if (elementClass.equals(Long.class)) {
	    return (T) Long.valueOf(0);
	}

	throw new RuntimeException("Not implemented for type '" + elementClass + "'");
    }

    /**
     * Creates a number '0' for the given Number class.
     * 
     * @param elementClass
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T extends Number> T one(Class<T> elementClass) {
	if (elementClass.equals(Integer.class)) {
	    return (T) Integer.valueOf(1);
	}

	if (elementClass.equals(Double.class)) {
	    return (T) Double.valueOf(1);
	}

	if (elementClass.equals(Long.class)) {
	    return (T) Long.valueOf(1);
	}

	throw new RuntimeException("Not implemented for type '" + elementClass + "'");
    }

    static Long sumL(List<Long> list) {
        return list.stream()
        	.mapToLong(element -> element.longValue())
        	.sum();
    }
}
