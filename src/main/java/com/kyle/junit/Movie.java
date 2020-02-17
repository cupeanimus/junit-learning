package com.kyle.junit;

public class Movie {
    private int sumOfRate = 0;
    private int countOfRate =0;

    public Integer averRating() {
        return countOfRate ==0 ? 0 : sumOfRate / countOfRate;
    }

    public void rate(int rate) {
        this.sumOfRate += rate;
        countOfRate++;
    }
}
