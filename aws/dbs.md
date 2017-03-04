# OLAP
Online Analytics Processing

# OLTP
Online Transaction Processing

# RDS (For OLTP)
- SQL Server
- MySQL
- PostgreSQL
- Oracle
- Aurora (Mysql compatible aws offering)
- MariaDB

# DynamoDB (No SQL)
# Redshift (OLAP)
# Elasticache - In memory cache
- Memcahced
- Redis
# DMS (Migration service)

# RDS
- If webserver and rds are in different security groups then we have to enable inbound traffic from ec2 security
group in rds security group for rds db port no.
- RDS support automated backups and snapshots. 
- Automated backups are done every day at specified time and have
defined life (35 days) after which they are deleted, you can also do point in time recovery up to second accuracy.
- Snapshots are taken manually and are not deleted when rds instances is deleted.
- You can use both for creating new rds instance.
- Encryption can not be enabled after creation, create a new encrypted instance and import data into it.
- Multi AZ DBs: SQL Server, Oracle, MySql, PostgreSQL, MariaDB. Multi AZ is for disaster recovery.
- Read Replica: For improving perforance, MySQL, MariaDB, PostgreSQL only, you can have upto 5 read replicas
and you can have read replicas of read replicas. You must have automated backups turned on for read replicas.
Each read replica has its own dns endpoint. you can not have read replicas which have multiaz turned on but
source db can have both multiaz and read replicas turned on. Read replicas can be promoted to become their own
dbs but it breaks replication. **You cant write to read replica**. For Mysql and Mariadb read replica can be in 
second region.
- RDS scaling is kind of manuall as you have to do it yourself. but DynamoDB autoscales without any downtime.

# DynamoDB
- Fast flexible nosql db
- Always on ssd
- spread across 3 geographically distinct regions so fail safe builtin
- Default consistency model is Eventual Consistent Reads

## consistency models
- Eventual Consistent reads: consistency across all copies of data is reached within one second, repeating a read
after short amount of time should return updated value. Best read performance
- Strongly Consistent reads: returns result that reflects all writes that received successfull response prior to
read. If you dont need strong consistency prefer Eventual consistency.

# Redshift
- Datawarehousing solution
- Single node 160GB
- Multi node:
-- Leader node: manages client and receives queries
-- Compute node: store data and perform query computation. upto 128 compute nodes

# Elasticache
# Aurora
- provides 5 time better performance and one tenth price of commerical dbs
- Autoscales. starts with 10gb and increments in 10gb upto 64tb
- compute resource scales upto 32vcpu and 244gb of memory
- you do face downtime while scaling aurora, but taking only few minutes
- it keeps 2 copies of your data in each az with min of 3 az.
- it can handle loss of upto 2 copies of data without affecting write availabilty and upto 3 copies without
affecting read availabilty.
- aurora storage is self healing and automatically scan for errors and removes them.
- Replica types: upto 15 aurora replicas and 5 mysql read replicas. if primary db fails it failover to aurora replica