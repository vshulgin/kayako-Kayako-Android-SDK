package com.kayako.sdk.android.k5.kre.base.user;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

import org.junit.Test;

import javax.annotation.Generated;

@Generated("GeneralPatterns")
public class KreUserSubscriptionPojoTest {

    @Test
    public void test_validate_KreUserSubscription_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(KreUserSubscription.class));
    }
}
