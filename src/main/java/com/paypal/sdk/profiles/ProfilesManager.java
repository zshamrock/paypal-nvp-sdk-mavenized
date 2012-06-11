/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import com.paypal.sdk.exceptions.FatalException;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.util.MessageResources;
import com.paypal.sdk.util.SDKResources;

import java.text.MessageFormat;
import java.util.List;
import java.util.Iterator;

/**
 * Singleton class that acts as central access point for profile management.
 * @author PayPal DTS
 */
public class ProfilesManager
{
    /**
     * Singleton reference
     */
    private static ProfilesManager profilesManager;

    /**
     * Name of ProfileHandler to use
     */
    private final static String handlerName = "com.paypal.sdk.profiles.DefaultProfileHandler";

    /**
     * Handler which controls storage and retrieval of profile data.
     */
    private ProfileHandler profileHandler;

    /**
     * All profiles in memory. Key is API username, value is Profile object.
     */
    private Profiles profiles;

    /**
     * The constructor loads the ProfileHandler
     */
    protected ProfilesManager() throws PayPalException
    {
        profiles = new Profiles();
        profileHandler = loadHandler(handlerName);
    } // constructor

    /**
     * Provides an access point for the singleton instance of profilesManager.
     * @return reference to the instance of profilesManager
     * @throws PayPalException if an error occurs creating the instance
     */
    public synchronized static ProfilesManager getInstance() throws PayPalException
    {
        if (profilesManager == null)
        {
            profilesManager = new ProfilesManager();
        }
        return profilesManager;
    } // getInstance

    /**
     * Asks the ProfileHandler to load profiles from memory. The profiles are stored
     * in an internal Map. 
     */
    public void retrieve() throws PayPalException
    {
        profiles = profileHandler.retrieve();
    }

    /**
     * Asks the ProfileHandler to save profiles to memory. 
     */
    public void store() throws PayPalException
    {
        profileHandler.store(profiles);
    }

    /**
     * Asks the ProfileHandler to save profiles to memory. 
     */
    public void store(Profiles profiles) throws PayPalException
    {
        profileHandler.store(profiles);
    }

    /**
     * Retrieves the profile for a given index
     * @param index index of the profile to retrieve 
     * @return Profile associated with the given index
     */
    public APIProfile getAPIProfile(int index) throws PayPalException
    {
        if ((index < 0) || (index >= this.profiles.getAPIProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getAPIProfiles().size() }));
        }
        return (APIProfile) this.profiles.getAPIProfiles().get(index);
    }

    /**
     * Retrieves the profile for a given API username
     * @param apiUsername username to lookup
     * @return Profile associated with the given username
     */
    public APIProfile getAPIProfile(String apiUsername) throws PayPalException
    {
        Iterator i = profiles.getAPIProfiles().iterator();
        while (i.hasNext())
        {
            APIProfile profile = (APIProfile) i.next();
            if (profile.getAPIUsername().equalsIgnoreCase(apiUsername))
            {
                return profile;
            }
        }
        throw new WarningException(MessageFormat.format(MessageResources
                .getMessage("PROFILE_DOES_NOT_EXIST"), new Object[] { apiUsername }));
    } // getAPIProfile

    /**
     * Retrieves the profile for a given index
     * @param index index of the profile to retrieve 
     * @return Profile associated with the given index
     */
    public EWPProfile getEWPProfile(int index) throws PayPalException
    {
        if ((index < 0) || (index >= this.profiles.getEWPProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getEWPProfiles().size() }));
        }
        return (EWPProfile) this.profiles.getEWPProfiles().get(index);
    }

    /**
     * Places the specified profile into the memory of the profilesManager.
     * Note: this does not store the profile into external memory, for this you need to use
     * the store() method.
     * @param profile The profile to store
     */
    public void addAPIProfile(APIProfile profile) throws PayPalException
    {
        this.profiles.getAPIProfiles().add(profile);
    }

    /**
     * Places the specified profile into the memory of the profilesManager.
     * Note: this does not store the profile into external memory, for this you need to use
     * the store() method.
     * @param index where to store the profile
     * @param profile The profile to store
     */
    public void addAPIProfile(int index, APIProfile profile) throws PayPalException
    {
        if ((index < 0) || (index > this.profiles.getAPIProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getAPIProfiles().size() }));
        }
        this.profiles.getAPIProfiles().set(index, profile);
    }

    /**
     * Places the specified profile into the memory of the profilesManager.
     * Note: this does not store the profile into external memory, for this you need to use
     * the store() method.
     * @param profile The profile to store
     */
    public void addEWPProfile(EWPProfile profile) throws PayPalException
    {
        this.profiles.getEWPProfiles().add(profile);
    }

    /**
     * Places the specified profile into the memory of the profilesManager.
     * Note: this does not store the profile into external memory, for this you need to use
     * the store() method.
     * @param index where to store the profile
     * @param profile The profile to store
     */
    public void addEWPProfile(int index, EWPProfile profile) throws PayPalException
    {
        if ((index < 0) || (index > this.profiles.getAPIProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getEWPProfiles().size() }));
        }
        this.profiles.getEWPProfiles().set(index, profile);
    }

    /**
     * Removes a profile from memory
     * @param index index of the profile to delete
     */
    public void deleteAPIProfile(int index) throws PayPalException
    {
        if ((index < 0) || (index >= this.profiles.getAPIProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getAPIProfiles().size() }));
        }
        this.profiles.getAPIProfiles().remove(index);
    }

    /**
     * Removes a profile from memory
     * @param index index of the profile to delete
     */
    public void deleteEWPProfile(int index) throws PayPalException
    {
        if ((index < 0) || (index >= this.profiles.getEWPProfiles().size()))
        {
            throw new WarningException(MessageFormat.format(MessageResources
                    .getMessage("INDEX_OUT_OF_RANGE"), new Object[] { "" + index,
                    "" + this.profiles.getEWPProfiles().size() }));
        }
        this.profiles.getEWPProfiles().remove(index);
    }

    /**
     * Checks to see if this manager contains any profiles or not
     * @return true if the manager contains no profiles; false otherwise
     */
    public boolean isEmpty()
    {
        return this.profiles.isEmpty();
    }

    /**
     * Removes all profiles from memory
     */
    public void clean()
    {
        this.profiles.clear();
    }

    /**
     * Retrieve all API Profiles in memory
     * @return collection of Profile objects 
     */
    public List getAPIProfiles()
    {
        return this.profiles.getAPIProfiles();
    }

    /**
     * Retrieve all EWP Profiles in memory
     * @return collection of Profile objects 
     */
    public List getEWPProfiles()
    {
        return this.profiles.getEWPProfiles();
    }

    /**
     * Loads the specified ProfileHandler into memory, and instaniates an instance for use
     * @param className class name of the ProfileHandler to use
     * @return new instance of the specified ProfileHandler
     * @throws PayPalException if an error occurs loading or instantiating the specified ProfileHandler
     */
    private ProfileHandler loadHandler(String className) throws PayPalException
    {
        try
        {
            Class handlerClass = Class.forName(className);
            return (ProfileHandler) handlerClass.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            throw new FatalException(MessageResources.getMessage("PROFILE_HANDLER_CLASS_MISSING")
                    + ": " + className, e);
        }
        catch (IllegalAccessException e)
        {
            throw new FatalException(MessageResources.getMessage("PROFILE_HANDLER_CLASS_LOADER")
                    + ": " + className, e);
        }
        catch (InstantiationException e)
        {
            throw new FatalException(MessageResources.getMessage("PROFILE_HANDLER_CLASS_CREATE")
                    + ": " + className, e);
        }
        catch (ClassCastException e)
        {
            throw new FatalException(MessageResources.getMessage("PROFILE_HANDLER_CLASS_MISSING")
                    + ": " + className, e);
        }
    } // loadHandler
} // ProfilesManager