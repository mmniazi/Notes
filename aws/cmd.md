- You cant assign roles once ec2 instance is created
- Roles are universal
- You can change permission assigned to role and it will take effect on all alraedy ec2 instance immediatly
- To get metadata of ec2 instace do sudo curl `http://169.254.169.254/latest/meta-data` it will give list of
variables which you can append in url to get details like `http://169.254.169.254/latest/meta-data/public-ipv4`
- Now using meta data you can upload info on s3 and then use lambda to do complex things like adding ec2 to route53
