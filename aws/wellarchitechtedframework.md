- Security
- Reliablity
- Performance Efficency
- Cost Optimization

# Security
- Data protection
-- Classify your data around who should be able to access what
-- Implement principle of least privilege
-- Encrypt data where possible
- Privilege management
-- AWS Customer maintains full control of data
-- AWS makes it easy to Encrypt data and manage keys with regular key rotation
-- Detailed logging is available using cloudtrail
-- AWS is designed for extreme durabilty
- Infrastructure protection
-- Versioning to protection against accidental data loss
-- AWS never moves data between regions itself until unless customer asks to do it
- Detective controls

## Best Practices
- Data protection
-- How are encrypting data at rest (AES 256) and how are you encrypting at transit (SSL/TLS)
- Privilege management
-- ACL
-- Role based access control
-- Password management
- Detective controls
-- cloudtrail
-- cloudwatch
-- aws config
-- aws s3
-- aws glacier

## Services
- Data protection
-- ELB, EBS, S3, RDS
- Privilege management
-- IAM and MFS
- Infrastructure protection
-- VPC
- Detective controls
-- cloudtrail, cloudwatch, aws config

# Reliablity

## Design principle
- test recovery principles
- automate recovery from failure
- Scale horizontally to increase aggregate system availablilty
- Stop guessing capacity

## Definations
- Foundations
-- How you are managing aws service limits for your account
-- How are managing your network topology on aws
-- Do you have an escalation path to deal with technical issues
- Change management
-- How does your system adapt to changes in demand
-- How are you monitoring aws resources
-- How are you executing change managemenet
- Failure management
-- How are you backing up your data
-- How does your system withstand component failure
-- How are you planning for recovery

## Services
- Foundations
-- IAM, VPC
- Change management
-- AWS cloudtrail
- Failure management
-- AWS Cloudformation

# Performance Efficency

## Design principles
- Democratize new technologies
- Go global in minutes
- Use serverless architecture

# Definations
- Compute
-- How do select appropriate instance type
-- How do ensure that you continue to have the most appropriate instance type as new instance type come
-- How do you monitor you instance post launch to ensure they are performing as expected
- Storage
-- Optimal storage solution depends on no of factors:
-- access methods: block file or object
-- pattern of access: seq or random
-- throughput req
-- freq of access: online, offlien or achrival
-- freq of update: worm, dynamic
-- availablilty constrains
-- durabilty constrains
-- How do you select appropriate storage sol.
-- How do you ensure that you continue to have most appropriate sol.
-- How do you monitor your storage sol to ensure its behaving as expected
-- How do you ensure that capacity and throughput matches demand
- Database
-- How do you select appropriate db sol
-- How do you ensure you continue to have most appropriate sol
-- how do you monitor dbs to ensure performance is as expected
-- how do you ensure that your capacity and throughput matches demand
- Space-time tradeoff
-- How do you select appropriate proximity and caching sol
-- How do you ensure you continue to have appropriate sol
-- How do you monitor your proximity and caching sol to ensure they are behaving as expected
-- How do you make sure that proximity and caching sol matches demand

## Services
- Compute: Autoscaling
- Storage: S3, EBS, glacier
- Database: RDS, DynamoDb, Redshift
- CloudFront, Elasticache, direct connect, RDS read replicas

# Cost Optimization
- Match supply and demand
-- How do you make sure that your capacity matches but does not exceed
-- How are you optimizing your aws services usage
- cost effective resources
-- Have you selected appropriate resources type to meet your cost targets
-- Have you selected appropriate pricing model to meet your cost targets
-- Are there managed services that can improve your return on investment
- expenditure awareness
-- What access control and procedure have you in place to gover aws costs
-- How are you monitoring usage and spending
-- How do you decommision resources you no longer need
-- How do consider data transfer charges when designing your system
- optimizing over time
-- How do you manage and consider adoption of new services

# Services
- Match supply and demand: Autoscaling
- cost effective sol: ec2 reserved instances, aws trusted advisor
- expenditure awareness: cloudwatch alarms, sns
- optimizing over time: aws blog and aws trusted advisor
