# credit institute

dummy credit institute for the payment service of the t2 store.

takes credit card info and the total amount of money and pretends to perform payment. 


## endpoints

* ``/timeout/{timeout}`` GET to set the request delay to ``timeout`` (in ms)
* ``/failurerate/{rate}`` GET to set failurerate to ``rate``
* ``/timeoutrate/{rate}`` GET to set timeoutrate to ``rate``
* ``/pay`` POST to execute some payment


	