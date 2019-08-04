package net.kumbhar.dx;

import java.util.Map;

public class HacksTest {

    @Hack(description = "This should really have the correct type but we dont know what it is.")
    private Object fieldUsage;


    @Hack(description = "This is a hack that will make my life miserable in 1 month")
    @Hack(description = "This is a hack that will make my life miserable in 2 months")
    public void repeatedUsage() {
        // Ignore
    }

    @Hack(description = "This hack needed to be fixed yesterday", fixBeforeDate = "2021-05-14")
    public void buildFailureUsage() {
        // Ignore
    }

    public void methodParameterUsage(@Hack(description = "This should be its own type") Map<String, String> data) {
        // Ignore
    }

    public void localvariableUsage() {
        @Hack(description = "We have decided to not follow best practices for now")
        boolean followBestPractices = false;
    }

}
