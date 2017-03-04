# EC2
- Virtual Servers in cloud

# Types
- On demand
- Reserved: make commitment with aws for base capacity for few years for high discounts and you can make upfront
payment for even lower prices
- Spot: Set your bid for compute capacity and when bid becomes greater than your price your instance is shutdown
used for getting lots of compute capacity in timing when people are not using compute much so bid becomes really
low. if instance is terminted by aws you are not charged for partial hour but if you do it you are charged for
complete hour
- Dedicated Hosts: Physical EC2 servers dedicated for your use, you can use your already server bound software
licences

# Instances
DRMCGIFTPX
D => Dense Storage
R => High Memory
M => General purpose
C => Compute optimized
G => Graphics optimized
I => Fast Storage optimized
F => FPGA
T => Low cost general purpose
X => Xtreme Memory optimized

# Subnet
Subnet

# Storage
- When creating instance it asks what to do to ebs on instance termination
- You can only attach volume to ec2 instance in same availablity zone
- RAID 5 is discourged in aws
- If you are going to create snapshot of raid array either freeze filesystem, unmount array or shutdown ec2
before taking snapshot.

# Security Groups
- Any changes in security groups applies immediatly
- security groups are statefull, any traffice you allow in inbound rule is automatically allowed on outbound
- You cant deny traffic or ip address using security rules you have to use network access control list for that
- All inbound traffic is allowed by default

# Autoscaling groups 
- It can add or removes instances based on alarms
- It can add instances if some instance go down to match the specified no of instances

# Placement Zones
- It is logical grouping of instances within single availablity zone
- It cant span on multiple availablity zones
- name of Placement group must be unique in your aws account
- Only certain type of instances can be launched in placement group
- AWS recommend you launch homogeneous instances in placement group
- You cant merge placement groups
- You cant move exsisting instance into placement group, you have to create ami and launch new instance