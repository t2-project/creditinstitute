# Creditinstitute Service

This service is not a part of the actual T2 Store. 

It is a dummy service to provide a payment functionality for the [Payment Service](https://github.com/t2-project/payment).

The reply behaviour at runtime of the service's ``/pay`` endpoint may be changed at runtime. 


## Build and Run

Confer the [Documentation](https://t2-documentation.readthedocs.io/en/latest/guides/kube.html) on how to build, run or deploy the T2 Store services.


## HTTP Endpoints

* ``/timeout`` timeout in ms
* ``/failurerate`` probability of failure as decimal
* ``/timeoutrate`` probability of timeout as decimal
* ``/pay`` POST to execute fake payment. 

 @PostMapping("/pay") @RequestBody PaymentData card)

    @PostMapping("/timeout") @RequestBody int timeout) 


    @PostMapping("/failurerate")  @RequestBody double rate) {
    

    
    @PostMapping("/timeoutrate") @RequestBody double rate)
    





## Usage

Assuming the service runs as ``provider-cs`` you can interact with it like this:

### Request Payment

The service answers request to this endpoint either with an Internal Server Error or delays the answer up to a specified timeout duration.

```
 curl -i -X POST -H "Content-Type:application/json" -d '{"cardNumber":"num","cardOwner":"own","checksum":"checksum","total":42}' provider-cs/pay
```

### Set the Failure Probability 

The failure probability defines how frequently the service responds with an Internal Server Error. 

Set it to 0 to never answers (intentionally) with an Internal Server Error:

```
curl -i -X POST -H "Content-Type:application/json" -d 0  provider-cs/failurerate
```

Set it to 1 (or greater) to always answers with an Internal Server Error. 

```
curl -i -X POST -H "Content-Type:application/json" -d 1  provider-cs/failurerate
```


### Set the Timeout Probability (as decimals)

The timeout probability defines how frequently the service waits for the full timeout duration until it responds.

Set it to 1 (or greater) to delay all answers for the full timeout duration.

```
curl -i -X POST -H "Content-Type:application/json" -d 1  provider-cs/timeoutrate
```

Set it to 0 to not delay answers full timeout duration.
In this case the answer's delay is random but less than half of the specified timeout duration.

```
curl -i -X POST -H "Content-Type:application/json" -d 0  provider-cs/timeoutrate
```

### Set the Timeout Duration (in ms)

The timeout duration is how long an answer is delayed in case of an intentional timeout.
In case of a 'normal' answer ('normal' as in 'neither an intentional failure, nor an intentional timeout'), an answer's delay is random but always less than half of the timeout duration.


request :
```
curl -i -X POST -H "Content-Type:application/json" -d 5000  provider-cs/timeout
```