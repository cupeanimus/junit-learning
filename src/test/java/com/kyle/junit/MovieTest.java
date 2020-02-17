package com.kyle.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieTest {

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
    }

    @Test//Junit 확인 foo는 아무 의미없는 변수를 만들 때 사용되온 이름
    public void foo(){

    }


    @Test//is, AssertThat import 주소 체크
    public void should_return_0_when_junit_created(){
        assertThat(movie.averRating(),is(0));
    }


    @Test//is, AssertThat import 주소 체크
    public void should_return_1_when_1_was_rated(){
        movie.rate(1);
        assertThat(movie.averRating(),is(1));
    }


    @Test//is, AssertThat import 주소 체크
    public void should_return_4_when_3_and__were_rated(){
        movie.rate(3);
        movie.rate(5);
        assertThat(movie.averRating(),is(4));
    }




}
