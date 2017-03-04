# Route53

- ELB dont have public ipv4 address
- Alias record vs cname: 
-- cname request incur charges, alias is free
-- you cant use cname on naked domain, use alias for that
-- always use alias over cname in exam
- Different routing policies
-- Simple: no intelligence
-- Weighted: Divide users basedd on defined percentage
-- Latency: Prefer low latency
-- Failover: If primary server fails use secondary
-- Geolocation: Redirect to different servers based on geolocation of user
