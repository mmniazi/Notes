- Distributed messaging queue
- can contain upto 256kb message
- SQS does not gurantee first in first out
- there is a visibilty timeout which tells how long message will be hidden in queue, so basically when a server
pulls a message and it does not respond back in timeout period it will be added back to queue so atleast once 
processed is ensured.
- you can setup autoscaling around sqs by spanning more application servers if queue size becomes greater than
certain limit
- by default timeout limit is 12 hours
- a single request can have upto 10 message with total payload size of 256 kb
- each 64 kb chunk of payload is billed as one request. so 256kb request is billed as 4 requests
- **Anywhere you see decouple in exam thats sqs they are looking for**
- If you need to priotrize messages use 2 sqs queues and application servers first check high priority queue and
if it is empty it then check low priority queue.


# Example (Image watermark example)
- async pull message from queue
- retrive file name
- process the conversion
- write image back to s3
- write tasks complete to another queue
- delete original task from queue
- check for more messages
