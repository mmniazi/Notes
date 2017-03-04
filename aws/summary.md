# Compute

## EC2
Vitrual server instances

## EC2 Container Service (ECS)
Container management service for deploying, scheduling and monitoring docker containers on ec2 
instances.

## LightSail
Lightsail is simplified aws. It alows deploying common stacks and managing them in an easy to use 
console with limited features.

## Elastic Beanstalk
EBS is a full featured deployment service where you can deploy, monitor and scale your ec2 instances.

## Lambda
For running server less code that run in response to events without any need to infrastructre 
management, but does not has file system or other low level access intead have to use aws 
services for logging storeage etc

# Storage

## S3
Object storage service

## Glacier
Archive storage service

## Elastic File System (EFS) 
EFS is file storage service which provide file system interface to connect to multiple ec2 instances
at a time. EFS provide file system services such as strong consistency and file locking while being
conntected to multiple ec2 instances.

## Elastic Block Storage (EBS)
EBS is block level storage service which is attached to single ec2 instance like its hard drive, 
but its a service you can deattach from one instance and attach to other without any loss. It 
supports replication, snapshots etc.

## Storage Gateway **Needs explaination**
A VM which you install on premise to connect your premise db with s3.

# Databases

## Relational Database Service (RDS)
RDS provides standard relational databases as a service, allowing launching, managing and monitoring dbs.

## DynamoDb
NoSql db as a service.

## Aurora
Mysql compatible low cost, highly scalable db service.

## ElastiCache
In memory db (memcached and redis) as a service.

## Redshift
Dataware house as a service. Replicate your production db data in redshift and run etl queries on it
to generate reports.

# Networking & Content Delivery

## VPC 
Vpc is virtual private cloud used to isolate resource. Within vpc you can create your own public 
or private subnets.

## CloudFront
Aws CDN

## Direct Connect
Dedicated connection from your premise to AWS datacenters

## Route53
Aws DNS provider service

# Migration

## DMS
Database migration service. supports migration already present oracle and other dbs to aws based dbs like aurora, rds and dynamodb.

## SMS
Server migration service, supports migrating Vmware image to ec2.

## SnowBall
A briefcase size harware which allows you to transfer your petabytes of data from your premises to aws cloud.

# Management

## CloudWatch
Measure metrics of your resources such as cpu usage, memory or disk usage. Create alarms and use this
information to launch new resources when required to drop existing one.

## CloudFormation
Infrastructre as a code, allows defining whole infrastructre in a file and deploying it using 
single command.

## Config
Record changes in your aws console ,define policies and be notified when some one creates security
rules etc which does not match those policies. (Cloud Trail also records this data but does not 
have alarms and policies)

## OpsWorks
Automating deployments using Chef.

## Trusted Advisor
Analyzes your envoirnment and give constructive suggestions.

# Analytics

## Athena
Run large scale SQL queries on data stored in S3.

## EMR
Elastic Map reduce

## Elastic Search
Elastic search as a service.

## CloudSearch
Similar as elastic search, but aws only service.

## Kinesis
Realtime data processing service

# Application Services

## API Gateway
Manage your api through a single gateway which takes care of authorization, versioning etc

## Step Functions
Step function allows creating distributed system using visual console. It acts as a state machine
which have states and tasks which you can monitor. Each tasks gives results which results in change 
of state. On state you can have conditional branching, start or end, do some task, do some task in
parallel or have some delay.

## Simple Workflow Service (SWF)
Way of cordinating both both automated tasks and human tasks.

## App Stream
Streaming desktop application to clients

## Elastic Transcoder
Video encoder service on scale, like KKTV api. most probably will be running ffmpeg.

# Security, Identity & Compliance

## IAM
Authentication, users, roles, permissions, and groups management service.

## Inspector
Agent which runs on your ec2 to monitor things relted to security.

## Cretificate Manager
Free ssl certificates for your domain names

## Directory Service
Using microsoft active directory with aws.

## WAF
Web application firewall is firewall at application layer instead of network layer, it protects
against SQL Injection, CSS etc attacks

## Compliance reports
For getting random shitty ISO etc certifications.

# Developer Tools

## Code Commit
Github

## CodeBuild
Build servers

## Code Deploy
Continious deployment

## Code Pipeline
Continious Integration

# Mobile Services

## Mobile Hub
Console for mobile developers which provide all mobile related sevices.

## Cognito
AWS Auth0

## Device Farm
Bulk mobile testing service

## Mobile Analytics
Mobile analytics sdk

## Pinpoint
Google analytics for mobile with targeted marketing

# Bussiness Productivity

## WorkDocs
Secure documents service

## WorkMail
secure mail service

# IOT

## iOT
Hub for iot related functionality aws provide

# Desktop and App Streaming

## WorkSpaces
Remote desktop in cloud which you can use from table or light devices

## App Stream 2.0
Streaming Desktop applications to users