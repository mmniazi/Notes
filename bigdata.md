# Big Data
- Defined by volume, velocity, variety, veracity, varaibility, and value of data

## Clustered Computing
- For obvious benifits, different cluster managers are used. Two common options can be Apache Mesos and Hadoop YARN both of these can abstract the underlying hardware

## Data Ingestion
There are differnet data ingestion tools which can take data from data sources and put that in big data system:
- Sqoop -> relational database
- Flume & Chukwa -> Application and server logs
- Queing system like Kafka can be used as a buffer between differnt data generators and big data system
- Ingestion framworks like gobblin can help in aggregating and normalizing data from different data sources
- During the ingestion process, some level of analysis, sorting, and labelling usually takes place. This process is sometimes called ETL, which stands for extract, transform, and load

## Persistence
- For persistence we need a distributed file system like HDFS, Ceph or GlusterFs
- For databases normally nosql are prefered because they are designed with same fault tolerance in mind

## Compute

### Batch processing
