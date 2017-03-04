# Amazon Machine Images
Used for launching multiple similar ec2 instances, It contains:
- template for root volume of instances
- launch permission defining which aws accounts can use this ami
- a block device mapping that specifies which volume to attach when launching instance

# Creation process
- Create snapshot
- Create Image from it
- Ami are regional but you can copy them to other regions
- All ami are categorized by either backed by ebs or backed by instance store. Instance store templates are 
stored in s3 while ebs instance are made from ebs snapshots
- Instance stores are some time called ephermal storage
- Instance store are old technology which cant be stopped and if underlying host fails your data is lost
- On ebs you can ask aws to keep root devices on termination
