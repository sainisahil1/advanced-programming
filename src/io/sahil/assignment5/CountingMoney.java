package io.sahil.assignment5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sahil Saini
 */
public class CountingMoney {

    private TreeMap<Integer, Integer> coins;

    public static void main(String[] args) {
        CountingMoney countingMoney = new CountingMoney();
        countingMoney.initMap();
        TreeMap<Integer, Integer> result = countingMoney.coinChange(173);
        System.out.println(result);
    }

    private void initMap(){
        coins = new TreeMap<>(Collections.reverseOrder());
        coins.put(1, 7);
        coins.put(5,4);
        coins.put(10,15);
        coins.put(20,2);
        coins.put(50,1);
        coins.put(100,2);
        coins.put(200,3);
    }

    private TreeMap<Integer, Integer> coinChange(int amount){
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (Integer coin: coins.keySet()){
            if(amount == 0) break;

            int usableCoins = Math.min(amount / coin, coins.get(coin));
            if (usableCoins > 0) {
                result.put(coin, usableCoins);
                amount -= coin * usableCoins;
            }
        }
        return result;
    }

}
