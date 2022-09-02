package me.seohuipark.noblewaybackend.common.service;

import me.seohuipark.noblewaybackend.common.code.OperationCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonServiceTests {

    @Test
    public void operationCodeTest() {
        OperationCode plusOperation = OperationCode.PLUS;
        OperationCode minusOperation = OperationCode.MINUS;
        OperationCode multiplicationOperation = OperationCode.MULTIPLY;
        OperationCode divisionOperation = OperationCode.DIVIDE;

        System.out.println(plusOperation.calculate(31, 28));
        System.out.println(minusOperation.calculate(31, 28));
        System.out.println(multiplicationOperation.calculate(31, 28));
        System.out.println(divisionOperation.calculate(31, 28));
    }
}
