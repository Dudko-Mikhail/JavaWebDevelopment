package by.bsu.xml.parse.sax;

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
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class SaxBuilderTest {
    private SaxBuilder builder;

    @BeforeMethod
    public void initBuilder() {
        builder = new SaxBuilder();
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
