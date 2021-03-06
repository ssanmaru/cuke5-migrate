package migrate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dataobject.FullName;
import dataobject.Money;
import dataobject.ProfLevels;
import dataobject.ProfessorNoArg;
import dataobject.User;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;

public class ParameterTypeStepDefinition {

	// ---DELMITER
	@ParameterType(".*?")
	public List<String> names(String name) {
		return Arrays.asList(name.split(","));
	}

	@Given("the user names are {names}")
	public void givenUser(List<String> names) {
		System.out.println(names);
		System.out.println("");
	}

	@ParameterType(".*?")
	public List<User> users(String name) {
		return Arrays.asList(name.split(",")).stream().map(User::new).collect(Collectors.toList());
	}

	@Given("the users are {users}")
	public void givenProf(List<User> names) {
		System.out.println(names);
		System.out.println("");
	}

	// ---FORMAT
	@ParameterType("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")
	public LocalDateTime date_iso_local_date_time(String dt) {
		return LocalDateTime.parse(dt);
	}

	// (\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})
	@Given("the date is {date_iso_local_date_time}")
	public void the_date_is(LocalDateTime date) {
		System.out.format("Day %d Month %d Year %d Hour %d Minute %d Second %d \n", date.getDayOfMonth(),
				date.getMonthValue(), date.getYear(), date.getHour(), date.getMinute(), date.getSecond());
		System.out.println("");
	}

	// ---TRANSFORM
	@ParameterType(".*?")
	public FullName fullname(String name) {
		return FullName.parseNameDetails(name);
	}

	@Given("the name is {fullname}")
	public void theFullNameIs(FullName fullName) {
		System.out.println(fullName);
		System.out.println("");
	}

	@ParameterType(".*?")
	public Money amount(String money) {
		return Money.parseMoneyDetails(money);
	}

	@Given("the amount is {amount}")
	public void theAmountIs(Money money) {
		System.out.println(money);
		System.out.println("");
	}

	// ---XSTREAM
	@Given("the user name is {}")
	public void theUserNameIs(User user) {
		System.out.println(user);
		System.out.println("");
	}

	@ParameterType(".*?")
	public ProfessorNoArg professor(String prof) {
		return ProfessorNoArg.parseProfessor(prof);
	}

	@Given("the professor is {professor}")
	public void theProfessorNameIs(ProfessorNoArg prof) {
		System.out.println(prof);
		System.out.println("");
	}

	@Given("the professor level is {}")
	public void theProfessorLevelIs(ProfLevels level) {
		System.out.println(level);
		System.out.println("");
	}

	@Given("the biginteger value is {}")
	public void theBigIntegerValueIs(BigInteger bigint) {
		System.out.println(bigint);
		System.out.println("");
	}

	@Given("the bigdecimal value is {}")
	public void theBigDecimalValueIs(BigDecimal bigdec) {
		System.out.println(bigdec);
		System.out.println("");

	}

	@ParameterType("(.) main currency ([\\d]+) fractional currency ([\\d]+)")
	public Money currency(String symbol, String main, String frac) {
		return new Money(symbol, Integer.parseInt(main), Integer.parseInt(frac));
	}

	@Given("the total payment is in {currency}")
	public void the_total_payment_is_in(Money money) {
		System.out.println(money);
		System.out.println("");
	}
}
