/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Data class that maintains the API and EWP profiles that are in memory.
 * @author PayPal DTS
 */
public final class Profiles implements Serializable
{
    /**
     * Collection of APIProfile objects
     */
    private List apiProfiles;

    /**
     * Collection of EWPProfile objects
     */
    private List ewpProfiles;

    /**
     * Initialize the object with empty data.
     */
    public Profiles()
    {
        apiProfiles = new ArrayList();
        ewpProfiles = new ArrayList();
    }

    /**
     * Initialize the object with provided data.
     * @param api APIProfiles
     * @param ewp EWPProfiles
     */
    public Profiles(List api, List ewp)
    {
        this.apiProfiles = api;
        this.ewpProfiles = ewp;
    }

    /**
     * Retrieve API Profiles in memory
     * @return collection of APIProfile objects
     */
    public List getAPIProfiles()
    {
        return this.apiProfiles;
    }

    /**
     * Retrieve API Profiles in memory
     * @return collection of APIProfile objects
     */
    public List getEWPProfiles()
    {
        return this.ewpProfiles;
    }

    /**
     * Clear out all profiles from memory.
     */
    public void clear()
    {
        apiProfiles.clear();
        ewpProfiles.clear();
    }

    /**
     * Checks to see if there are any profiles of any type in memory.
     * @return true if no profiles are in memory, false otherwise
     */
    public boolean isEmpty()
    {
        return apiProfiles.isEmpty() && ewpProfiles.isEmpty();
    }
} // Profiles