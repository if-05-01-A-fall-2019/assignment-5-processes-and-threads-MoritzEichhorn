/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlleonding.fibonacci;

import java.util.concurrent.Callable;

/**
 *
 * @author moritz
 */
public class FibonacciCalculator implements Callable<Integer>{
    int n;

    public FibonacciCalculator(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        return calcFib(n);
    }   
    
    private int calcFib(int n) {
        if(n < 2) {
            return 1;
        }
        return calcFib(n-1) + calcFib(n-2);
    }
}
