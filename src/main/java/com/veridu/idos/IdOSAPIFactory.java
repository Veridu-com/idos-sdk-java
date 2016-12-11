package com.veridu.idos;

import com.veridu.idos.endpoints.*;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.settings.Config;

import java.io.Serializable;
import java.util.HashMap;

/**
 * CredentialFactory Endpoint creates all Endpoints
 *
 */
public class IdOSAPIFactory implements Serializable {

    /**
     * base API URL
     */
    private String baseURL = Config.BASE_URL;

    /**
     * ssl checking flag
     */
    private boolean doNotCheckSSL = false;

    /**
     * Credentials data
     */
    private HashMap<String, String> credentials;

    /**
     * ProfileFeaturesTest Endpoint object
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
     * ProfileCandidatesTest Endpoint object
     */
    private ProfileCandidates candidates;

    /**
     * ProfileAttributes Endpoint object
     */
    private ProfileAttributes attributes;

    /**
     * ProfileScores Endpoint object
     */
    private ProfileScores score;
    /**
     * ProfileFlagsTest Endpoint object
     */
    private ProfileFlags flags;

    /**
     * ProfileRawTest Endpoint object
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
     * ProfileRecommmendation object
     */
    private ProfileRecommendation recommendation;

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
     * Class constructor
     *
     * @param credentials
     *            HashMap<String, String>
     * @param baseURL API URL
     */
    public IdOSAPIFactory(HashMap<String, String> credentials, String baseURL) {
        this.credentials = credentials;
        this.baseURL = baseURL;
    }

    /**
     * Class constructor
     *
     * @param credentials
     *            HashMap<String, String>
     * @param doNotCheckSSLCertificate whether to disable ssl verification for API requests
     */
    public IdOSAPIFactory(HashMap<String, String> credentials, boolean doNotCheckSSLCertificate) {
        this.credentials = credentials;
        this.doNotCheckSSL = doNotCheckSSLCertificate;
    }

    /**
     * Class constructor
     *
     * @param credentials
     *            HashMap<String, String>
     * @param baseURL API URL
     * @param doNotCheckSSLCertificate whether to disable ssl verification for API requests
     */
    public IdOSAPIFactory(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSLCertificate) {
        this.credentials = credentials;
        this.baseURL = baseURL;
        this.doNotCheckSSL = doNotCheckSSLCertificate;
    }

    /**
     * Class constructor
     *
     */
    public IdOSAPIFactory() {
        this.credentials = new HashMap<>();
    }

    /**
     * Instantiates Candidate endpoint.
     *
     * @return ProfileCandidate instance
     */
    public ProfileCandidates getCandidate() {
        if (!(this.candidates instanceof ProfileCandidates))
            this.candidates = new ProfileCandidates(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.candidates;
    }

    /**
     * Instantiates Attribute endpoint.
     *
     * @return ProfileAttribute instance
     */
    public ProfileAttributes getAttribute() {
        if (!(this.attributes instanceof ProfileAttributes))
            this.attributes = new ProfileAttributes(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.attributes;
    }

    /**
     * Instantiates Feature endpoint.
     *
     * @return ProfileFeaturesTest instance
     */
    public ProfileFeatures getFeature() {
        if (!(this.feature instanceof ProfileFeatures))
            this.feature = new ProfileFeatures(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.feature;
    }

    /**
     * Instantiates Gate endpoint
     *
     * @return ProfileGates instance
     */
    public ProfileGates getGate() {
        if (!(this.gate instanceof ProfileGates))
            this.gate = new ProfileGates(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.gate;
    }

    /**
     * Instantiates Reference endpoint
     *
     * @return ProfileReferences instance
     */
    public ProfileReferences getReference() {
        if (!(this.reference instanceof ProfileReferences))
            this.reference = new ProfileReferences(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.reference;
    }

    /**
     * Instantiates Task endpoint
     *
     * @return ProfileTasks instance
     */
    public ProfileTasks getTask() {
        if (!(this.task instanceof ProfileTasks))
            this.task = new ProfileTasks(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.task;
    }

    /**
     * Instantiates Score endpoint
     *
     * @return ProfileScores instance
     */
    public ProfileScores getScore() {
        if (!(this.score instanceof ProfileScores))
            this.score = new ProfileScores(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.score;
    }

    /**
     * Instantiates Flag endpoint
     *
     * @return ProfileFlagsTest instance
     */
    public ProfileFlags getFlag() {
        if (!(this.flags instanceof ProfileFlags))
            this.flags = new ProfileFlags(this.credentials, this.baseURL, this.doNotCheckSSL);
        return this.flags;
    }

    /**
     * Instantiates Source endpoint
     *
     * @return Source instance
     */
    public ProfileSources getSource() {
        if (!(this.source instanceof ProfileSources)) {
            this.source = new ProfileSources(this.credentials, this.baseURL, this.doNotCheckSSL);
        }
        return this.source;
    }

    /**
     * Instantiates Raw endpoint
     *
     * @return Raw instance
     */
    public ProfileRaw getRaw() {
        if (!(this.raw instanceof ProfileRaw)) {
            this.raw = new ProfileRaw(this.credentials, this.baseURL, this.doNotCheckSSL);
        }
        return this.raw;
    }

    /**
     * Instantiates SSO endpoint
     *
     * @return SSO instance
     */
    public SSO getSSO() {
        if (!(this.sso instanceof SSO)) {
            this.sso = new SSO(this.baseURL, this.doNotCheckSSL);
        }
        return this.sso;
    }

    /**
     * Instantiates ProfileProcesses endpoint
     *
     * @return Profile Processes instance
     */
    public ProfileProcesses getProcess() {
        if (!(this.process instanceof ProfileProcesses)) {
            this.process = new ProfileProcesses(this.credentials, this.baseURL, this.doNotCheckSSL);
        }
        return this.process;
    }

    /**
     * Instantiates ProfileRecommendation endpoint
     *
     * @return Profile Recommendation instance
     */
    public ProfileRecommendation getRecommendation() {
        if (!(this.recommendation instanceof ProfileRecommendation)) {
            this.recommendation = new ProfileRecommendation(this.credentials, this.baseURL, this.doNotCheckSSL);
        }

        return this.recommendation;
    }

    /**
     * Instantiates Profiles endpoint
     *
     * @return Profiles intance
     */
    public Profiles getProfile() {
        if (!(this.profile instanceof Profiles)) {
            this.profile = new Profiles(this.credentials, this.baseURL, this.doNotCheckSSL);
        }
        return this.profile;
    }

}
