package com.kyle.junit;

import org.junit.*;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

// ...

public class AssertTest {

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @Before
    public void createAccount() {
        account = new Account("an account name");
    }

    //AsertTrue  참인지 여부. 값이 있는지 없는지. 조건을 만족하는 지 등을 테스트
    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        Assert.assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        Assert.assertTrue(account.getBalance() > initialBalance);
    }


    //assertThat 명확한 값을 비
    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue() {
        account.deposit(50);
        assertThat(account.getBalance() > 0, is(true));
        assertThat(account.getBalance(),equalTo(50));
    }

    //햄크레스트 매처  (hamcrest는 jMock 라이브러리 저자들이 참여해서 만든 Macher 라이브러리)
    @Ignore
    @Test
    public void comparesArraysFailing() {
        assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
    }

    @Test
    public void comparesArraysPassing() {
        assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
    }

    @Ignore
    @Test
    public void comparesCollectionsFailing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a", "ab"})));
    }

    @Test
    public void comparesCollectionsPassing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a"})));
    }
    @Test
    @Ignore
    public void assertDoublesEqual() {
        assertThat(2.32 * 3, equalTo(6.96));
    }

    @Test
    public void assertWithTolerance() {
        Assert.assertTrue(Math.abs((2.32 * 3) - 6.96) < 0.0005);
    }

    @Test
    public void assertDoublesCloseTo() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }

    //예외 기대 InsufficientFundsException 관련 에러가 발생하면 통
    @Test(expected=InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch() {
        account.withdraw(50);
    }
    
    //룰을 생성. 예상메세지가 일치하면 통과
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionRule() {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");

        account.withdraw(100);
    }


    @Test
    public void readsFromTestFile() throws IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
        // ...
    }

    @After
    public void deleteForReadsFromTestFile() {
        new File("test.txt").delete();
    }

}
