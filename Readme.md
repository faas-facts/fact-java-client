# java-fact-client

Java client for the [fact](https://github.com/faas-facts/fact) library.
### Setup
First add the fact dependency to the pom
 ``` 
      <dependency>
        <groupId>io.github.fact</groupId>
        <artifactId>client</artifactId>
        <version>0.1.1</version>
      </dependency> 
```
Make sure Maven is configured to use Github packages if you haven't follow [this guide](https://docs.github.com/en/free-pro-team@latest/packages/using-github-packages-with-your-projects-ecosystem/configuring-apache-maven-for-use-with-github-packages).
Use `mvn package` to generate the jar for your function.

## Usage
Import the fact package into your FaaS-function code and use the provided methods to generate
insights for your function execution.
The Fact object should be initiated in a static block to be started before your function code executes, using your config in `Fact.boot`. After the fact object is initiated it can be used to start, update and close traces 
using `Fact.start`, `Fact.update` and `Fact.done`.
An example implementation can be found [here](https://github.com/faas-facts/fact/tree/main/examples/java-aws).

## Config

To configure the Client use the FactConfigurationBuilder class. 

### TCP config
Port and address for the TCP sender are set through environment variables this needs to be done in the runtime
environment of the function. Set `fact_tcp_address` and `fact_tcp_port` to your match your log server's. 