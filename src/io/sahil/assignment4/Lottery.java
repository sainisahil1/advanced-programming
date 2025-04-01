package io.sahil.assignment4;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents lottery problem
 *
 * @author Sahil Saini
 */
public class Lottery extends ConsoleProgram {

    private RandomGenerator rand = new RandomGenerator();

    public static void main(String[] args) {
        new Lottery().start();
    }

    @Override
    public void run() {
        Set<Integer> lotteryNumbers = generateLotteryNumbers();
        println(lotteryNumbers);
    }

    private Set<Integer> generateLotteryNumbers() {
        Set<Integer> lotteryNumbers = new HashSet<>();
        while (lotteryNumbers.size() < 6) {
            int r = rand.nextInt(1, 49);
            lotteryNumbers.add(r);
        }
        return lotteryNumbers;
    }
}
