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

Start the Fact client by building your config and calling `Fact.boot` in a static block, so hte fact client is being started before your function executes 
```
static {
        FactConfigurationBuilder builder= new FactConfigurationBuilder();
        TCPSender sender = new TCPSender();
        FactConfiguration conf= builder.setIo(sender).setSendOnUpdate(true).createFactConfiguration();
        Fact.boot(conf);
    }
```

After the fact has been started, it can be used to start, update and close traces 
using `Fact.start`, `Fact.update` and `Fact.done`. Each of these functions take a context object to identify the trace.
An example implementation can be found [here](https://github.com/faas-facts/fact/tree/main/examples/java-aws).

## Config

To configure the Client use the FactConfigurationBuilder class. 
+ setIo --> set fact IO to either ConsoleLogging or TCPSender using the provided classes
+ setSendOnUpdate --> bool to decide if traces should be sent everytime `Fact.update` is called or only when `Fact.done` is called
+ setLazyLoading --> turn lazy loading on or off
+ setToIncludeEnvironment --> if this method is called the execution environment of the function will be included in the trace

### TCP config
Port and address for the TCP sender are set through environment variables this needs to be done in the runtime
environment of the function. Set `fact_tcp_address` and `fact_tcp_port` to your match your log server's. 