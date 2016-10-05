Veridu idOS JAVA SDK
===================

Installation
------------
Is recommended to install this SDK by using [maven](https://maven.apache.org).

If you are using maven, please edit your `pom.xml` and add the following:


To continue with the installation please add this under the `<dependencies>` tag :

```mvn
<dependency>
	<groupId>com.veridu</groupId>
	<artifactId>idos-sdk-java</artifactId>
	<version>0.1</version>
</dependency>
```
Examples
--------
You can find examples of basic usage on `src/main/java/com/veridu/idos/samples/` directory.

Tests
-----
To run the tests, just run in the command line inside the root of this project: ```mvn test```.

How To Use The New SDK
----------------------

The majority of the endpoints require Authentication Tokens when making requests to the API.
There are three kinds of tokens: UserToken, CredentialToken or IdentityToken.

### First Step

The first step is to instantiate the IdOSAPIFactory Class passing a HashMap<String, String> of the credentials to be used.

```java
/**
 * Creates a HashMap and adds the credentials necessary to make the requests
 */
HashMap<String, String> credentials = new HashMap<>();
credentials.put("credentialPublicKey", "credentialPublicKey");
credentials.put("handlerPublicKey", "handlerPublicKey");
credentials.put("handlerPublicKey", "handlerPublicKey");

/**
 * Instantiates the IdOSAPIFactory Class
 */
IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(credentials);
```

### Second Step

The second step is to call the endpoint desired to make the request. As an example we are going to use the /profiles and the listAll() method to list all profiles related to the credential

```java
/**
 * Gets the response from the API listing all profiles
 */
JsonObject json = idOSAPIFactory.getProfile().listAll();
```
To see more examples of how to use the SDK and how to call the methods and endpoints, go to ```src/codeSamples``` directory.
