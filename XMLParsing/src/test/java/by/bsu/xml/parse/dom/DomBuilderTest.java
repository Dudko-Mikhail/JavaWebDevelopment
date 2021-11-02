package by.bsu.xml.parse.dom;

import by.bsu.xml.constant.DepositType;
import by.bsu.xml.entity.Bank;
import by.bsu.xml.entity.Deposit;
import by.bsu.xml.entity.Depositor;
import by.bsu.xml.exception.CustomXmlParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class DomBuilderTest {
    private DomBuilder builder;

    @BeforeMethod
    public void initBuilder() {
        builder = new DomBuilder();
    }

    @Test
    public void buildBanksTest() throws CustomXmlParseException {
        Set<Depositor> depositors = new HashSet<>();
        Set<Deposit> deposits = new HashSet<>();
        deposits.add(new Deposit(new BigDecimal("34.21"), Currency.getInstance("USD"), 1, 45.0,
                LocalDate.of(2021, 10, 14), DepositType.SETTLEMENT_DEPOSIT));
        depositors.add(new Depositor("Misha", deposits));
        Bank expected = new Bank("b1", "BSB", "Belarus", depositors);

        builder.buildBanks("src/test/resources/data/correctBanks.xml");
        Bank actual = builder.getBanks().get(0);
        Assert.assertEquals(actual, expected);
    }
}
