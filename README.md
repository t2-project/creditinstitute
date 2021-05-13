# Creditinstitute Service

This service is not a part of the actual T2 Store. 

It is a dummy service to provide a payment functionality for the [Payment Service](https://github.com/t2-project/payment).

The reply behaviour at runtime of the service's ``/pay`` endpoint may be changed at runtime. 


## Build and Run

Confer the [Documentation](https://t2-documentation.readthedocs.io/en/latest/guides/kube.html) on how to build, run or deploy the T2 Store services.


## HTTP Endpoints

* ``/timeout/{timeout}`` GET to set the request delay to ``timeout`` (in ms)
* ``/failurerate/{rate}`` GET to set failurerate to ``rate`` (as decimal)
* ``/timeoutrate/{rate}`` GET to set timeoutrate to ``rate`` (as decimal)
* ``/pay`` POST to execute fake payment. 


## Usage

Assuming the service runs at ``http://localhost:8087`` you can interact with it like this:

### Request Payment

The service answers request to this enpoint either with an Internal Server Error or delays the answer up to a specified timeout duration.

```
 curl -i -X POST -H "Content-Type:application/json" -d '{"cardNumber":"num","cardOwner":"own","checksum":"checksum","total":42}' http://localhost87/pay
```

### Set the Failure Probability 

The failure probability defines how frequently the service responds with an Internal Server Error. 

Set it to 0 to never answers (intentionally) with an Internal Server Error:

```
curl http://localhost:8087/failurerate/0
```

Set it to 1 (or greater) to always answers with an Internal Server Error. 

```
curl http://localhost:8087/failurerate/1
```


### Set the Timeout Probability (as decimals)

The timeout probability defines how frequently the service waits for the full timeout duration until it responds.

Set it to 1 (or greater) to delay all answers for the full timeout duration.

```
curl http://localhost:8087/timeoutrate/1
```

Set it to 0 to not delay answers full timeout duration.
In this case the answer's delay is random but less than half of the specified timeout duration.

```
curl http://localhost:8087/timeoutrate/0
```

### Set the Timeout Duration (in ms)

The timeout duration is how long an answer is delayed in case of an intentional timeout.
In case of a 'normal' answer ('normal' as in 'neither an intentional failure, nor an intentional timeout'), an answer's delay is random but alwasy less than half of the timeout duration.


request :
```
curl http://localhost:8087/timeout/5000
```