package com.veridu.idos.test.functional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Profiles extends MainTestSetup {
    private com.veridu.idos.endpoints.Profiles profile;

    @Before
    public void setUp() throws Exception {
        this.profile = factory.getProfile();
    }

}
