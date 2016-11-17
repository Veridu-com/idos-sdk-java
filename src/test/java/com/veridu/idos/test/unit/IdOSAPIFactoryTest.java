package com.veridu.idos.test.unit;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.endpoints.ProfileAttributes;
import com.veridu.idos.endpoints.ProfileCandidates;
import com.veridu.idos.endpoints.ProfileFeatures;
import com.veridu.idos.endpoints.ProfileFlags;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.endpoints.ProfileProcesses;
import com.veridu.idos.endpoints.ProfileRaw;
import com.veridu.idos.endpoints.ProfileReferences;
import com.veridu.idos.endpoints.ProfileScores;
import com.veridu.idos.endpoints.ProfileSources;
import com.veridu.idos.endpoints.ProfileTasks;
import com.veridu.idos.endpoints.Profiles;
import com.veridu.idos.endpoints.SSO;

public class IdOSAPIFactoryTest extends AbstractUnit {

    HashMap<String, String> credentials;
    IdOSAPIFactory factory;

    @Before
    public void setUp() {
        this.credentials = this.getCredentials();
        this.factory = new IdOSAPIFactory(this.credentials);
    }

    @Test
    public void testConstructorWithArgs() {
        IdOSAPIFactory factory = new IdOSAPIFactory(this.credentials);
        assertThat(factory, isA(IdOSAPIFactory.class));
    }

    @Test
    public void testConstructorEmpty() {
        IdOSAPIFactory factory = new IdOSAPIFactory();
        assertThat(factory, isA(IdOSAPIFactory.class));
    }

    @Test
    public void testGetEndpoints() {
        assertThat(this.factory.getAttribute(), isA(ProfileAttributes.class));
        assertThat(this.factory.getCandidate(), isA(ProfileCandidates.class));
        assertThat(this.factory.getFeature(), isA(ProfileFeatures.class));
        assertThat(this.factory.getFlag(), isA(ProfileFlags.class));
        assertThat(this.factory.getGate(), isA(ProfileGates.class));
        assertThat(this.factory.getProcess(), isA(ProfileProcesses.class));
        assertThat(this.factory.getRaw(), isA(ProfileRaw.class));
        assertThat(this.factory.getReference(), isA(ProfileReferences.class));
        assertThat(this.factory.getProfile(), isA(Profiles.class));
        assertThat(this.factory.getScore(), isA(ProfileScores.class));
        assertThat(this.factory.getSource(), isA(ProfileSources.class));
        assertThat(this.factory.getTask(), isA(ProfileTasks.class));
        assertThat(this.factory.getSSO(), isA(SSO.class));
    }
}
