package com.as.restapi.suite;


import com.as.restapi.tests.UseCase1Test;
import com.as.restapi.tests.UseCase2Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        UseCase1Test.class,
        UseCase2Test.class,
})
public class AllApiTest {
}
