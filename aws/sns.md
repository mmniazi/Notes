# Simple Notification Service

- Sending messages or Notifications from cloud
- its simple pub sub model
- everytime you have an autoscaling event it will trigger sns message which will send you email or text etc
- allows to group multiple recipents using topics
- a topic is an access point for allowing recipents to dynamically sub for notifications
- a topic can support to deliveries to multiple endpoints types
- redudency is builtin


# SNS can send message to (endpoints types)
- mobile devices as push notifications
- sms messages
- emails and email json
- messages to sqs queues
- or to any http/https end point
- also can trigger lambda functions

# Benifits
- push based delivery no polling unlinke sqs which is pull
- simple integration with applications
- multiple transport protocols
- inexpensive

# SQS VS SNS
- both are messaging services
- SNS is push SQS is poll