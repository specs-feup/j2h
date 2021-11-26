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

package pt.up.fe.specs.j2h;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import pt.up.fe.specs.j2h.utils.ListOp;
import pt.up.fe.specs.util.SpecsLogs;

public class Main {

    public static void main(String[] args) {
        /// Pause
        try {
            System.out.println("WAITING...");
            System.in.read();
        } catch (IOException e) {
            SpecsLogs.msgWarn("Error message:\n", e);
        }

        // (new HeapWindow()).run();
        int amount = 100000000;

        /// Sum with infinite list (Long objects)
        long objectStart = System.nanoTime();
        long result = ListOp.sum(H.listInfNative(1l).take(amount).toJavaList(), Long.class);
        // Integer result = ListOp.sum(H.listInf(0).take(amount), Integer.class);
        long objectEnd = System.nanoTime();

        // System.out.println("Time:" + ParseUtils.parseTime(objectEnd - objectStart));
        long elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
        System.out.println("InfList Time (Long):" + elapsedTime);
        System.out.println("Result:" + result);

        /// Sum with infinite list (HLong objects)
        objectStart = System.nanoTime();
        result = ListOp.sum(H.listInf(1l).take(amount).toJavaList(), Long.class);
        objectEnd = System.nanoTime();

        elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
        System.out.println("InfList Timeb(HLong):" + elapsedTime);
        System.out.println("Result:" + result);

        /// Sum with cycle - much faster and much less memory
        objectStart = System.nanoTime();
        result = ListOp.sum(H.listT(10000l).cycle().take(amount), Long.class);
        objectEnd = System.nanoTime();

        elapsedTime = TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS);
        System.out.println("Cycle Time:" + elapsedTime);
        System.out.println("Result:" + result);

        /// Native time
        /*
        objectStart = System.nanoTime();
        
        result = 0l;
        for (int i = 1; i <= amount; i++) {
        result += i;
        }
        
        // Integer result = ListOp.sum(H.listInf(0).take(amount), Integer.class);
        objectEnd = System.nanoTime();
        System.out.println(
        	"Time native:" + TimeUnit.MILLISECONDS.convert((objectEnd - objectStart), TimeUnit.NANOSECONDS));
        System.out.println("Result:" + result);
        */

        /// Pause
        try {
            System.out.println("FINISHED");
            System.in.read();
        } catch (IOException e) {
            SpecsLogs.msgWarn("Error message:\n", e);
        }
    }
}
