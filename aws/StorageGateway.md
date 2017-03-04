# StorageGateway
- Its an application which you install on your hypervisor to replicate or transfer your data to cloud.

# Types
- File Gateway (NFS) Flat file structure directly stores files on s3
- Volume Gateway (iSCSI) Block storage (takes virtual harddisk on premise and replicate them to EBS, supports
snapshots and stores snapshots as diffs and compressed, and are saved on cloud as ebs snapshots)
-- Stored Volumes (Store entire copy of data set on premise)
-- Cached Volume (Only store latest data on premise and rest is backuped on aws)
- Tape Gateway (TPL used VirtualTapeLibrary) used for backups 