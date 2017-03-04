# VPC
- Virtual datacenter conists of gateways, route tables, network access control lists, subnets, and security groups
- Can span az but cant span regions.
- Using VPC:
-- You can launch instances into subnet of your choosing
-- assign custom ip address ranges in each subnet
-- configure route tables between subnets
-- create internet gateway and attach it to your vpc
-- You can have only one internet gateway per vpc
-- better security control
-- instance security groups are stateful
-- subnet network acl are stateless, inbound are not allowed to outbound automatically
-- in default vpc all instances have public ip by default and there is not private subnet by default
- Vpc peering: allows you to connect one vpc to other vpc on same region. it can be on same account or any other
account. instances behave as they are on same vpc. vpc peering is not transtive (daisy chained) so A peer B and B peer C means
A and C are not peered you have to peer them seperatly.

# Subnet
- One subnet cant span multiple az.
- In a subnet aws reserves 3 address by default, .1, .2, and .3
- by default all subnet are linked to main route table so dont edit main route table create new one
- to make public subnet create a new route table that allows traffic from 0.0.0.0 and then assign subnet to that
route table after that enable auto assign public ip on that subnet

# Route table


# NAT Instance
- Old way, its and ec2 instance which is used to access internet by instances in private subnet
- Create an ec2 instance from any nat ami
- Disable source destination check in console
- In main routing table add 0.0.0.0 destination with target to nat instance
- Nat instance is single point of failure and you have to do load balancing and redudency your self
- Nat instance must be in public subnet
- amount of traffic nat instance support depends on instance size

# Nat gateway
- Not behind any security groups
- Auto scales and auto redudency
- Only set 0.0.0.0 dest to nat gateway target

# ACL VS security groups
- security groups operate at instanace layer so its first layer of defense
- acl operate at subnet level so its second layer of defense
- acl allows blacklisting specific ip address so you have fine grain control
- security groups supports allows rules only while acl also supports deny rules 
- security group is stateful so outbound traffic is allowed based on inbound traffic while acl does not its stateless
- security group eval all rules before deciding if to allow traffic or not. while acl process in number order
- security group applies to instance only when associated with it while acl applies to all instances in subnet its
associated with

# ACL
- VPC comes by default with acl which allows all inbound and outbound traffic
- by default custom acl blocks all traffic
- a subnet can be associated with one acl at a time, if not mentioned its with default acl, it deassociate with
old acl when its associated with new subnet
- lower no rule always override
- ip can only be blocked using acl not security group

# Bastion vs NAT
- Bastion instance is for administration and routes ssh or rdp traffic to private subnets
- while nat is for internet traffice routing to private subnet

# VPC Flow logs
- send logs of all traffic which interacts with your vpc to cloudwatch. you have to create log group and then log
stream inside that group to actually see logs in console.

# Resilient architecture
- For resilient architecture you need atleast 2 public subnets and 2 private subnets which are in different az.
- With elb make sure that instances are in two different subnets
- with bastion host, put them behind autoscalling group with min size of 2. use route53 to implement failover.
- nat instances, 1 in each public subnet and write script to failover between 2. instead use nat gateway.
