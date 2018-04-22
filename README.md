# InnTell
Effective Hotel Management Using Prospective Customer Insights

## 1. About InnTell
InnTell uses publicly available visitor data from [data.gov.sg](https://data.gov.sg/) and provides extensive analytics by combining insights about visitors, hotels and climate to provide price and visitor food preference recommendations which will help hotels to differentiate themselves from their competitors.

## 2. Tech Stack
InnTell is a SaaS application deployed in IBM Bluemix. The service is up and running at [http://inntell.mybluemix.net](http://inntell.mybluemix.net). InnTell is basically a web service running a REST server and with a HTML5 CSS3 user interface. The REST server talks to a SQL database hosted in the cloud. The technical stack of InnTell is given below

### 2.1 User Interface
- [Bootstrap](https://getbootstrap.com/)
- [ChartJS](https://www.chartjs.org/)
- [JQuery](https://jquery.com/)

### 2.2 REST Server
-[Spring MVC](https://spring.io/guides/gs/serving-web-content/)
-[Jackson Object Mapper](https://github.com/FasterXML/jackson)
-[Maven](https://maven.apache.org/)

### 2.3 IBM Bluemix DB2 Database
-[DB2 Warehouse](https://www.ibm.com/sg-en/marketplace/db2-warehouse)

## 3. Usage
Inntell is deployed in a Liberty PaaS environment in IBM Bluemix. The PaaS environment has a IBM Websphere Application Server that accepts a *war* package. Below are the steps to build a *war* package using Maven build tool.

> Please setup Maven in the build environment by following ["Maven setup"](https://maven.apache.org/install.html)
> [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) is required.

```
mvn clean install
```
The above command will build the project and generate a *war* file. After the *war* file is generated, following commands are used to deploy it in Bluemix.

```
bluemix login -u <bluemix_id> -o <organisation> -s <space>
bluemix app push InnTell
```
Alternatively, you can run the application locally using,
```
mvn clean install liberty:run-server
```
