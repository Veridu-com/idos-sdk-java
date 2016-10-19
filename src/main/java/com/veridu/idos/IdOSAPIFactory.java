package com.veridu.idos;

import java.io.Serializable;
import java.util.HashMap;

import com.veridu.idos.endpoints.ProfileCandidates;
import com.veridu.idos.endpoints.ProfileFeatures;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.endpoints.ProfileProcesses;
import com.veridu.idos.endpoints.ProfileRaw;
import com.veridu.idos.endpoints.ProfileReferences;
import com.veridu.idos.endpoints.ProfileScores;
import com.veridu.idos.endpoints.ProfileSources;
import com.veridu.idos.endpoints.ProfileTasks;
import com.veridu.idos.endpoints.ProfileWarnings;
import com.veridu.idos.endpoints.Profiles;
import com.veridu.idos.endpoints.SSO;
import com.veridu.idos.exceptions.InvalidToken;

/**
 * CredentialFactory Endpoint creates all Endpoints
 *
 */
public class IdOSAPIFactory implements Serializable {

    /**
     * Credentials data
     */
    private HashMap<String, String> credentials;

    /**
     * ProfileFeatures Endpoint object
     */
    private ProfileFeatures feature;

    /**
     * ProfileGates Endpoint object
     */
    private ProfileGates gate;

    /**
     * ProfileReferences Endpoint object
     */
    private ProfileReferences reference;

    /**
     * ProfileSources Endpoint object
     */
    private ProfileSources source;

    /**
     * ProfileTasks Endpoint object
     */
    private ProfileTasks task;

    /**
     * ProfileCandidates Endpoint object
     */
    private ProfileCandidates attribute;

    /**
     * ProfileScores Endpoint object
     */
    private ProfileScores score;
    /**
     * ProfileWarnings Endpoint object
     */
    private ProfileWarnings warning;

    /**
     * ProfileRaw Endpoint object
     */
    private ProfileRaw raw;

    /**
     * SSO object
     */
    private SSO sso;

    /**
     * ProfileProcesses object
     */
    private ProfileProcesses process;

    /**
     * Profiles object
     */
    private Profiles profile;

    /**
     * Class constructor
     *
     * @param credentials
     *            HashMap<String, String>
     */
    public IdOSAPIFactory(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    /**
     * Instantiates Attribute endpoint
     *
     * @return ProfileAttribute instance
     * @throws InvalidToken
     */
    public ProfileCandidates getAttribute() throws InvalidToken {
        if (!(this.attribute instanceof ProfileCandidates))
            this.attribute = new ProfileCandidates(this.credentials);
        return this.attribute;
    }

    /**
     * Instantiates Feature endpoint
     *
     * @return ProfileFeatures instance
     * @throws InvalidToken
     */
    public ProfileFeatures getFeature() throws InvalidToken {
        if (!(this.feature instanceof ProfileFeatures))
            this.feature = new ProfileFeatures(this.credentials);
        return this.feature;
    }

    /**
     * Instantiates Gate endpoint
     *
     * @return ProfileGates instance
     * @throws InvalidToken
     */
    public ProfileGates getGate() throws InvalidToken {
        if (!(this.gate instanceof ProfileGates))
            this.gate = new ProfileGates(this.credentials);
        return this.gate;
    }

    /**
     * Instantiates Reference endpoint
     *
     * @return ProfileReferences instance
     * @throws InvalidToken
     */
    public ProfileReferences getReference() throws InvalidToken {
        if (!(this.reference instanceof ProfileReferences))
            this.reference = new ProfileReferences(this.credentials);
        return this.reference;
    }

    /**
     * Instantiates Task endpoint
     *
     * @return ProfileTasks instance
     * @throws InvalidToken
     */
    public ProfileTasks getTask() throws InvalidToken {
        if (!(this.task instanceof ProfileTasks))
            this.task = new ProfileTasks(this.credentials);
        return this.task;
    }

    /**
     * Instantiates Score endpoint
     *
     * @return ProfileScores instance
     * @throws InvalidToken
     */
    public ProfileScores getScore() throws InvalidToken {
        if (!(this.score instanceof ProfileScores))
            this.score = new ProfileScores(this.credentials);
        return this.score;
    }

    /**
     * Instantiates Warning endpoint
     *
     * @return ProfileWarnings instance
     * @throws InvalidToken
     */
    public ProfileWarnings getWarning() throws InvalidToken {
        if (!(this.warning instanceof ProfileWarnings))
            this.warning = new ProfileWarnings(this.credentials);
        return this.warning;
    }

    /**
     * Instantiates Source endpoint
     *
     * @return Source instance
     * @throws InvalidToken
     */
    public ProfileSources getSource() throws InvalidToken {
        if (!(this.source instanceof ProfileSources)) {
            this.source = new ProfileSources(this.credentials);
        }
        return this.source;
    }

    /**
     * Instantiates Raw endpoint
     *
     * @return Raw instance
     * @throws InvalidToken
     */
    public ProfileRaw getRaw() throws InvalidToken {
        if (!(this.raw instanceof ProfileRaw)) {
            this.raw = new ProfileRaw(this.credentials);
        }
        return this.raw;
    }

    /**
     * Instantiates SSO endpoint
     *
     * @return SSO instance
     * @throws InvalidToken
     */
    public SSO getSSO() throws InvalidToken {
        if (!(this.sso instanceof SSO)) {
            this.sso = new SSO(this.credentials);
        }
        return this.sso;
    }

    /**
     * Instantiates ProfileProcesses endpoint
     * 
     * @return Profile Processes instance
     * @throws InvalidToken
     */
    public ProfileProcesses getProcess() throws InvalidToken {
        if (!(this.process instanceof ProfileProcesses)) {
            this.process = new ProfileProcesses(this.credentials);
        }
        return this.process;
    }

    /**
     * Instantiates Profiles endpoint
     * 
     * @return Profiles intance
     * @throws InvalidToken
     */
    public Profiles getProfile() throws InvalidToken {
        if (!(this.profile instanceof Profiles)) {
            this.profile = new Profiles(this.credentials);
        }
        return this.profile;
    }
}
