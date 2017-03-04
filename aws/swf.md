- Simple workflow service = SWF

# SQS vs SWF
- SQS has retention period of 14 days while SWF has retention period of 1 year
- SWF presents tasks oriented api
- SWF ensures that task is assigned only once and is never duplicated, with sqs messages can be delivered multiple time
- SWF tracks all tasks and events in application.
- SWF workers does not needs to be code

# SWF Actors
- workflow starters: application that initates a workflow like website or mobile app.
- Deciders: Control flow of tasks if something is finished or fails it decides what to do.
- activity workers: carry out activity tasks