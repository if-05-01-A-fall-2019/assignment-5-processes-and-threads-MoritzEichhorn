// Eichhorn Moritz

/*
 * Copyright ©2015. Created by P. Bauer <p.bauer@htl-leonding.ac.at>, Department
 * of Informatics and Media Technique and Design, HTBLA Leonding, Limesstr. 12 - 14,
 * 4060 Leonding, AUSTRIA. All Rights Reserved. Permission to use, copy, modify,
 * and distribute this software and its documentation for educational,
 * research, and not-for-profit purposes, without fee and without a signed
 * licensing agreement, is hereby granted, provided that the above copyright
 * notice, this paragraph and the following two paragraphs appear in all
 * copies, modifications, and distributions. Contact the Head of Informatics,
 * Media Technique and Design, HTBLA Leonding, Limesstr. 12 - 14, 4060 Leonding,
 * Austria, for commercial licensing opportunities.
 * 
 * IN NO EVENT SHALL HTBLA LEONDING BE  LIABLE TO ANY PARTY FOR DIRECT,
 * INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST
 * PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 * EVEN IF HTBLA LEONDING HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * HTBLA LEONDING SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 * PROVIDED HEREUNDER IS PROVIDED "AS IS". HTBLA LEONDING HAS NO OBLIGATION
 * TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */
package at.htlleonding.fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author P. Bauer <p.bauer@htl-leonding.ac.at>
 */
class Fibonacci{

    private int n;
    public Fibonacci(int n) {
        this.n = n;
    }

    static int getNumberSingle(int n) {
        if (n < 2) {
            return 1;
        } else {
            return getNumberSingle(n - 1) + getNumberSingle(n - 2);
        }
    }

    static int getNumberParallel(int n)  {
        /*
        On my PC, when I only execute the tests and use getNumberSingle(), it takes 4.646 Seconds to finish.
        But when I run the tests using getNumberParallel it only takes 2.894 Seconds. So getNumberParallel
        is a lot faster.
        It's faster, because two threads are working at the same time. And together they are faster than 
        a single thread.
        */
        if(n < 2) {
            return 1;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        Future<Integer> int1 = executorService.submit(new FibonacciCalculator(n-1));
        Future<Integer> int2 = executorService.submit(new FibonacciCalculator(n-2));
        int fib;
        try {
            fib =  int1.get() + int2.get();
        } catch (Exception e) {
            throw new RuntimeException("Ups something went wrong!");
        }
        
        executorService.shutdown();
        return fib;
    }
}
